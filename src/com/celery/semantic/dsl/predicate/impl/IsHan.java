package com.celery.semantic.dsl.predicate.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.celery.semantic.dsl.predicate.Predicate;

public class IsHan implements Predicate {
	private Pattern pattern;
	
	public IsHan() {
		pattern = Pattern.compile("^\\p{script=Han}$");
	}

	@Override
	public boolean satisfy(String value) {
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}

}
