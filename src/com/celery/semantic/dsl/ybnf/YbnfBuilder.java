package com.celery.semantic.dsl.ybnf;

import com.celery.semantic.dsl.domain.ParserQuery;
import com.celery.semantic.dsl.domain.Result;
import com.celery.semantic.dsl.parser.Parser;
import com.celery.semantic.dsl.parser.impl.ALPHA;
import com.celery.semantic.dsl.parser.impl.AND;
import com.celery.semantic.dsl.parser.impl.DIGIT;
import com.celery.semantic.dsl.parser.impl.GROUP;
import com.celery.semantic.dsl.parser.impl.HAN;
import com.celery.semantic.dsl.parser.impl.NamedParser;
import com.celery.semantic.dsl.parser.impl.ORR;
import com.celery.semantic.dsl.parser.impl.OneOrMany;
import com.celery.semantic.dsl.parser.impl.SELECTABLE;
import com.celery.semantic.dsl.parser.impl.SPACE;
import com.celery.semantic.dsl.parser.impl.WORD;
import com.celery.semantic.dsl.parser.impl.ZeroOrMany;

public class YbnfBuilder {
	private static YbnfBuilder instance = null;
	private ParserQuery parserQuery;

	private YbnfBuilder() {
		Parser NUMBER = new OneOrMany(new DIGIT());
		Parser SPACE = new OneOrMany(new SPACE());
		Parser _SPACE = new ZeroOrMany(new SPACE());
		Parser COMMA = new GROUP(_SPACE, new WORD(","), _SPACE);
		Parser SEMICOLON = new GROUP(_SPACE, new WORD(";"), _SPACE);
		Parser VARNAME = new GROUP(new ALPHA(), new ZeroOrMany(new ORR(new ALPHA(), new DIGIT(), new WORD("_"))));
		VARNAME = new NamedParser("VARNAME", VARNAME);
		Parser VARABLE = new GROUP(new WORD("$"), VARNAME);
		Parser TEXT = new OneOrMany(new ORR(new DIGIT(), new ALPHA(), new HAN()));

		// #YBNF 0.1 utf8;
		Parser VERSION = new GROUP(NUMBER, new WORD("."), NUMBER);
		VERSION = new NamedParser("VERSION", VERSION);
		Parser CHARSET = new ORR(new WORD("utf8"), new WORD("utf-8"), new WORD("UTF8"), new WORD("UTF-8"));
		CHARSET = new NamedParser("CHARSET", CHARSET);
		Parser HEAD = new GROUP(new WORD("#YBNF"), SPACE, VERSION, SPACE, CHARSET, SEMICOLON);
		// #include classpath:aaa/bbb/ccc.ybnf;

		// service common;
		Parser SERVICE = new NamedParser("SERVICE", VARNAME);
		Parser MODEARG = new GROUP(new WORD("service"), SPACE, SERVICE, SEMICOLON);
		// callable @call1(arg1, arg2), @call1(arg1, arg2);
		Parser CALLNAME = new NamedParser("CALLNAME", VARNAME);
		Parser ARG = new NamedParser("ARG", VARNAME);
		Parser ARGNAME = new NamedParser("ARGNAME", new ZeroOrMany(new AND(ARG, new ZeroOrMany(new AND(COMMA, ARG)))));
		Parser CALL = new GROUP(new WORD("@"), CALLNAME, new WORD("("), _SPACE, ARGNAME, _SPACE, new WORD(")"));
		CALL = new NamedParser("CALL", CALL);
		Parser CALLABLE = new NamedParser("CALLABLE",
				new GROUP(new WORD("callable"), SPACE, CALL, new ZeroOrMany(new AND(COMMA, CALL)), SEMICOLON));
		// root $main;
		Parser ROOT = new NamedParser("ROOT", VARABLE);
		Parser ROOTARG = new GROUP(new WORD("root"), SPACE, ROOT, SEMICOLON);
		// define
		Parser KEY = new NamedParser("KEY", VARNAME);
		Parser VALUE = new NamedParser("VALUE", VARNAME);
		Parser _VALUE = new SELECTABLE(new GROUP(new WORD("%"), _SPACE, VALUE));
		Parser KEYVALUE = new GROUP(new WORD("{"), _SPACE, KEY, _SPACE, _VALUE, _SPACE, new WORD("}"));
		Parser DEFINE = new GROUP(VARABLE, new SELECTABLE(KEYVALUE));
		// sentence
		Parser SENTENCE = new NamedParser("SENTENCE", TEXT);

		Parser DS = new GROUP(DEFINE, _SPACE, new WORD("="), _SPACE, SENTENCE, SEMICOLON);

		Parser root = new GROUP(HEAD, MODEARG, CALLABLE, ROOTARG, DS);
		parserQuery = new ParserQuery(root);
	}

	public static YbnfBuilder build() {
		if (instance == null) {
			synchronized (YbnfBuilder.class) {
				if (instance == null) {
					instance = new YbnfBuilder();
				}
			}
		}
		return instance;
	}

	public Result compile(String target) {
		return parserQuery.parse(target);
	}
}
