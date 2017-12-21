package com.celery.semantic.dsl.parser.impl;

import java.util.LinkedList;
import java.util.List;

import com.celery.semantic.dsl.domain.Result;
import com.celery.semantic.dsl.parser.Parser;

public class NamedParser implements Parser {
	private String name;
	private List<Result> value;
	private Parser parser;

	public NamedParser(String name, Parser parser) {
		this.name = name;
		this.parser = parser;
		this.value = new LinkedList<>();
	}

	@Override
	public Result parse(String target) {
		Result rs = parser.parse(target);
		rs.setName(name);
		value.add(rs);
		return rs;
	}

	public String getName() {
		return name;
	}

	public List<Result> getValue() {
		return value;
	}
}
