package com.celery.semantic.dsl.parser.impl;

import com.celery.semantic.dsl.domain.Result;
import com.celery.semantic.dsl.parser.Parser;

public class AND implements Parser {
	private Parser first;
	private Parser second;

	public AND(Parser first, Parser second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public Result parse(String target) {
		Result rs = first.parse(target);
		if(rs.isSucceeded()) {
			Result rs2 = second.parse(rs.getRemaining());
			if(rs2.isSucceeded()) {
				return Result.concat(rs, rs2);
			}
		}
		return Result.fail();
	}

}
