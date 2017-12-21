package com.celery.semantic.dsl.parser.impl;

import com.celery.semantic.dsl.predicate.impl.IsHan;

public class HAN  extends SAT {

	public HAN() {
		super(new IsHan(), new CHARACTER());
	}

}
