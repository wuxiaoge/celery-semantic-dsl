package com.celery.semantic.dsl.parser.impl;

import com.celery.semantic.dsl.domain.Result;
import com.celery.semantic.dsl.parser.Parser;

public class OR implements Parser {
	private Parser first;
	private Parser second;

	public OR(Parser first, Parser second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public Result parse(String target) {
		Result rs = first.parse(target);
		return rs.isSucceeded() ? rs : second.parse(target);
	}

}
