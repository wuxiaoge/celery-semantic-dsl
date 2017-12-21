package com.celery.semantic.dsl.predicate.impl;

import com.celery.semantic.dsl.predicate.Predicate;

public class IsDigit implements Predicate {

	@Override
	public boolean satisfy(String value) {
		char ch = value.charAt(0);
		return Character.isDigit(ch);
	}

}
