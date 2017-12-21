package com.celery.semantic.dsl.parser;

import com.celery.semantic.dsl.domain.Result;

public interface Parser {
	public Result parse(String target);
}
