package com.celery.semantic.dsl.parser.impl;

import com.celery.semantic.dsl.domain.Result;
import com.celery.semantic.dsl.parser.Parser;
import com.celery.semantic.dsl.predicate.Predicate;

public class SAT implements Parser {
	private Predicate predicate;
	private Parser parser;
	
	public SAT(Predicate predicate, Parser parser) {
		this.predicate = predicate;
		this.parser = parser;
	}

	@Override
	public Result parse(String target) {
		Result rs = parser.parse(target);
		if(rs.isSucceeded() && predicate.satisfy(rs.getRecognized())) {
			return rs;
		}
		return Result.fail();
	}

}
