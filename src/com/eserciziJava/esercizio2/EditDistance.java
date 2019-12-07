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
	public static int calculate(@NotNull String strA, @NotNull String strB) {

		if (strA.isEmpty()) {
			return strB.length();
		}

		if (strB.isEmpty()) {
			return strA.length();
		}

		//slide 18
		int replace = calculate(strA.substring(1), strB.substring(1)) + costOfSubstitution(strA.charAt(0), strB.charAt(0));
		int insert = calculate(strA, strB.substring(1)) + 1;
		int remove = calculate(strA.substring(1), strB) + 1;

		return min(replace, insert, remove);
	}

	public static int calculateDyn(@NotNull String strA, @NotNull String strB) {
		HashMap<Pair<String, String>, Integer> problemSolved = new HashMap<>();
		return calculateDynRecursive(strA, strB, problemSolved);
	}

	private static int calculateDynRecursive(String strA, String strB, HashMap<Pair<String, String>, Integer> problemSolved) {
		int no_op, min;

		if (problemSolved.containsKey(new Pair<>(strA, strB)))
			return problemSolved.get(new Pair<>(strA, strB));

		if (strA.length() == 0) {
			problemSolved.put(new Pair<>(strA, strB), strB.length());
			return strB.length();
		}

		if (strB.length() == 0) {
			problemSolved.put(new Pair<>(strA, strB), strA.length());
			return strA.length();
		}

		if (strA.charAt(0) == strB.charAt(0))
			no_op = calculateDynRecursive(strA.substring(1), strB.substring(1), problemSolved);
		else no_op = -1;

		int remove = 1 + calculateDynRecursive(strA, strB.substring(1), problemSolved);
		int insert = 1 + calculateDynRecursive(strA.substring(1), strB, problemSolved);
		int replace = calculateDynRecursive(strA.substring(1), strB.substring(1), problemSolved) + costOfSubstitution(strA.charAt(0), strB.charAt(0));

		if (no_op == -1) {
			problemSolved.put(new Pair<>(strA, strB), min(remove, insert));
			return min(remove, insert, replace);
		} else
			min = min(remove, insert, no_op, replace);
		problemSolved.put(new Pair<>(strA, strB), min);

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
