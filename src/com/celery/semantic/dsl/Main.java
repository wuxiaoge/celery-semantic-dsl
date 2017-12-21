package com.celery.semantic.dsl;

import com.celery.semantic.dsl.bnf.BnfBuilder;
import com.celery.semantic.dsl.domain.Result;

public class Main {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("#BNF 0.1 utf8;");
		sb.append("service common;");
		sb.append("callable @call1(arg), @call2(arg1, arg2);");
		sb.append("root $main;");
		sb.append("$main = 我想听刘德华的歌;");
		BnfBuilder ybnfBuilder = BnfBuilder.build();
		long start = System.currentTimeMillis();
		Result rs = ybnfBuilder.compile(sb.toString());
		System.out.println(rs.getResults());
		System.out.println(rs.getRemaining());
		System.out.println(System.currentTimeMillis() - start);
	}

}
