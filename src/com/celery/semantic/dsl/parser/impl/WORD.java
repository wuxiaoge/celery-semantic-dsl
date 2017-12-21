package com.celery.semantic.dsl.parser.impl;

import com.celery.semantic.dsl.predicate.impl.IsWord;

public class WORD extends SAT {

	public WORD(String value) {
		super(new IsWord(value), new CHARACTER(value.length()));
	}

}
