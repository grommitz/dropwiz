package com.grommitz.dropwiz;

import java.util.Random;

public class NameService {

	private static final String[] names = new String[]{
		"Bob", "Sam", "Terry", "Jenny"
	};
	
	private Random rand = new Random();
	
	public String getName() {
		return names[rand.nextInt(names.length)];
	}
	
}
