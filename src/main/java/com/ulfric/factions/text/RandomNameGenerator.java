package com.ulfric.factions.text;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.ulfric.commons.nio.ResourceHelper;

import java.util.List;

public class RandomNameGenerator {

	private static final List<String> ADJECTIVES;
	private static final List<String> NOUNS;

	static {
		ADJECTIVES = ResourceHelper.getResourceLines("adjectives.txt");
		NOUNS = ResourceHelper.getResourceLines("nouns.txt");
	}

	public static String createName() {
		String adjective = randomAdjective();
		String noun = randomNoun();
		return StringUtils.capitalize(adjective) + StringUtils.capitalize(noun);
	}

	private static String randomAdjective() {
		return randomWord(ADJECTIVES);
	}

	private static String randomNoun() {
		return randomWord(NOUNS);
	}

	private static String randomWord(List<String> dictionary) {
		return dictionary.isEmpty() ? "" : dictionary.get(RandomUtils.nextInt(0, dictionary.size()));
	}

	private RandomNameGenerator() {
	}

}