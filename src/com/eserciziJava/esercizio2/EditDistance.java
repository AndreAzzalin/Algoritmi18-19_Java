package com.eserciziJava.esercizio2;


import javafx.util.Pair;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;


public class EditDistance {

    /*
    https://www.baeldung.com/java-levenshtein-distance
    https://www.geeksforgeeks.org/edit-distance-dp-using-memoization/
    https://dzone.com/articles/java-8-automatic-memoization
    */

	//senza l'annotation ritorn null pointer exception, così facendo sappiamo quale parametro è stato impostato a null
	public static int calculate(@NotNull String s1, @NotNull String s2) {

		if (s1.isEmpty()) {
			return s2.length();
		}

		if (s2.isEmpty()) {
			return s1.length();
		}

		//slide 18
		int replace = calculate(s1.substring(1), s2.substring(1)) + costOfSubstitution(s1.charAt(0), s2.charAt(0));
		int insert = calculate(s1, s2.substring(1)) + 1;
		int remove = calculate(s1.substring(1), s2) + 1;

		return min(replace, insert, remove);
	}

	public static int calculateDyn(@NotNull String s1, @NotNull String s2) {
		HashMap<Pair<String, String>, Integer> problemSolved = new HashMap<>();
		return calculateDynRecursive(s1, s2, problemSolved);
	}

	private static int calculateDynRecursive(String s1, String s2, HashMap<Pair<String, String>, Integer> problemSolved) {
		int no_op, min;

		if (problemSolved.containsKey(new Pair<>(s1, s2)))
			return problemSolved.get(new Pair<>(s1, s2));

		if (s1.length() == 0) {
			problemSolved.put(new Pair<>(s1, s2), s2.length());
			return s2.length();
		}

		if (s2.length() == 0) {
			problemSolved.put(new Pair<>(s1, s2), s1.length());
			return s1.length();
		}

		if (s1.charAt(0) == s2.charAt(0))
			no_op = calculateDynRecursive(s1.substring(1), s2.substring(1), problemSolved);
		else no_op = -1;

		int remove = 1 + calculateDynRecursive(s1, s2.substring(1), problemSolved);
		int insert = 1 + calculateDynRecursive(s1.substring(1), s2, problemSolved);
		int replace = calculateDynRecursive(s1.substring(1), s2.substring(1), problemSolved) + costOfSubstitution(s1.charAt(0), s2.charAt(0));

		if (no_op == -1) {
			problemSolved.put(new Pair<>(s1, s2), min(remove, insert));
			return min(remove, insert, replace);
		} else
			min = min(remove, insert, no_op, replace);
		problemSolved.put(new Pair<>(s1, s2), min);

		return min;
	}


	@Contract(pure = true)
	private static int costOfSubstitution(char a, char b) {
		return a == b ? 0 : 1;
	}

	//get min from stream of numbers
	private static int min(int... numbers) {
		return Arrays.stream(numbers)
						.min().orElse(Integer.MAX_VALUE);
	}
}
