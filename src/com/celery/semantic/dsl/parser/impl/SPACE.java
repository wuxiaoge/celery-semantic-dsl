package com.celery.semantic.dsl.parser.impl;

import com.celery.semantic.dsl.predicate.impl.IsSpace;

public class SPACE extends SAT {

	public SPACE() {
		super(new IsSpace(), new CHARACTER());
	}

}
