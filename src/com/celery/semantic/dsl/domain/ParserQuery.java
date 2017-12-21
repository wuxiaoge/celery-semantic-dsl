package com.celery.semantic.dsl.domain;

import java.util.List;

import com.celery.semantic.dsl.parser.Parser;

public class ParserQuery {
	private Parser parser;
	
	public ParserQuery(Parser parser) {
		this.parser = parser;
	}

	public Result parse(String target) {
		Result rs = parser.parse(target);
		if (rs.isSucceeded()) {
			Result root = Result.succeed(rs.getRecognized(), rs.getRemaining());
			root.setName("ROOT");
			process(root, rs);
			return root;
		}
		return Result.fail();
	}

	public void process(Result root, Result result) {
		List<Result> results = result.getResults();
		Result rootNode = null;
		if (result.getName() == null) {
			rootNode = root;
		} else {
			Result node = Result.succeed(result.getRecognized(), "");
			node.setName(result.getName());
			root.getResults().add(node);
			rootNode = node;
		}
		for (Result rs : results) {
			process(rootNode, rs);
		}
	}
}
