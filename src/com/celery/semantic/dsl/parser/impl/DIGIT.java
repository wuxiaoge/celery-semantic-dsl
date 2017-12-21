package com.celery.semantic.dsl.parser.impl;

import com.celery.semantic.dsl.predicate.impl.IsDigit;

public class DIGIT extends SAT {

	public DIGIT() {
		super(new IsDigit(), new CHARACTER());
	}

}
