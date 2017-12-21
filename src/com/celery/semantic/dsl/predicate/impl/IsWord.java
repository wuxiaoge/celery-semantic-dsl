package com.celery.semantic.dsl.predicate.impl;

import com.celery.semantic.dsl.predicate.Predicate;

public class IsWord implements Predicate {
	private String word;

	public IsWord(String word) {
		this.word = word;
	}

	@Override
	public boolean satisfy(String value) {
		return word.equalsIgnoreCase(value);
	}

}
