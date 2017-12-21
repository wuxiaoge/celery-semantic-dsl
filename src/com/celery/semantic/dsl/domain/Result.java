package com.celery.semantic.dsl.domain;

import java.util.ArrayList;
import java.util.List;

public class Result {
	private String recognized;
	private String remaining;
	private boolean succeeded;
	private String name;
	private List<Result> results;

	private Result(String recognized, String remaining, boolean succeeded) {
		this.recognized = recognized;
		this.remaining = remaining;
		this.succeeded = succeeded;
		this.name = null;
		this.results = new ArrayList<>();
	}

	public static Result succeed(String recognized, String remaining) {
		return new Result(recognized, remaining, true);
	}

	public static Result fail() {
		return new Result("", "", false);
	}

	public static Result concat(Result first, Result second) {
		Result result = new Result(first.getRecognized().concat(second.getRecognized()), second.getRemaining(), true);
		result.getResults().add(first);
		result.getResults().add(second);
		return result;
	}

	public String getRecognized() {
		return recognized;
	}

	public String getRemaining() {
		return remaining;
	}

	public boolean isSucceeded() {
		return succeeded;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append(name);
		sb.append(", \"").append(recognized);
		sb.append("\", ").append(results);
		sb.append("}");
		return sb.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Result> getResults() {
		return results;
	}
}
