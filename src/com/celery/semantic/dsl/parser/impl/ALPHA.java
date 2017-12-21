package com.celery.semantic.dsl.parser.impl;

import com.celery.semantic.dsl.predicate.impl.IsAlpha;

public class ALPHA extends SAT {

	public ALPHA() {
		super(new IsAlpha(), new CHARACTER());
	}

}
