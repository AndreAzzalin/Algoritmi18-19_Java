package com.eserciziJava.esercizio2;

import javafx.util.Pair;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.HashMap;


public class EditDistance {

	/**
	 * calculate number of minimum operations for convert string A to string B without memoization
	 *
	 * @param strA first string to be compared
	 * @param strB second string to be compared
	 * @return number of minimum operation for convert string A to string B
	 */
	public static int calculate(@NotNull String strA, @NotNull String strB) {

		if (strA.isEmpty()) {
			return strB.length();
		}

		if (strB.isEmpty()) {
			return strA.length();
		}

		int replace = calculate(strA.substring(1), strB.substring(1)) + costOfSubstitution(strA.charAt(0), strB.charAt(0));
		int insert = calculate(strA, strB.substring(1)) + 1;
		int remove = calculate(strA.substring(1), strB) + 1;

		return min(replace, insert, remove);
	}

	/**
	 * calculate dynamically number of minimum operations for convert string A to string B
	 *
	 * @param strA first string to be compared
	 * @param strB second string to be compared
	 * @return number of minimum operation for convert string A to string B calculated by calculateDynRecursive function
	 */
	public static int calculateDyn(@NotNull String strA, @NotNull String strB) {
		// hashmap used for memoization << string a,string b >, numbers of operation >
		HashMap<Pair<String, String>, Integer> problemSolved = new HashMap<>();
		return calculateDynRecursive(strA, strB, problemSolved);
	}

	/**
	 * wrapped by calculateDyn function for call method with less parameters
	 *
	 * @param strA          first string to be compared
	 * @param strB          second string to be compared
	 * @param problemSolved used to avoid overlapping problems (memoization concept)
	 * @return number of minimum operation for convert string a to string b
	 */
	private static int calculateDynRecursive(String strA, String strB, HashMap<Pair<String, String>, Integer> problemSolved) {
		int no_op;
		int min;

		//if subproblem already calculated return value from memoization map ( memoization )
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

		//if both characters are same call recursively function with substrings start at next char as parameters  (dividi et impera)
		if (strA.charAt(0) == strB.charAt(0)) {
			no_op = calculateDynRecursive(strA.substring(1), strB.substring(1), problemSolved);
		} else {
			no_op = -1;
		}

		// calculate recursively allowed operations for convert substring A to substring B
		int remove = 1 + calculateDynRecursive(strA, strB.substring(1), problemSolved);
		int insert = 1 + calculateDynRecursive(strA.substring(1), strB, problemSolved);
		int replace = calculateDynRecursive(strA.substring(1), strB.substring(1), problemSolved) + costOfSubstitution(strA.charAt(0), strB.charAt(0));

		// is true when characters of substring are not same
		if (no_op == -1) {
			//put result of sub problem to memoization map
			problemSolved.put(new Pair<>(strA, strB), min(remove, insert, replace));
			return min(remove, insert, replace);
		} else {
			//update min value
			min = min(remove, insert, no_op, replace);
		}

		//put result of sub problem to memoization map
		problemSolved.put(new Pair<>(strA, strB), min);

		return min;
	}

	/**
	 * used as support function for calculateDynRecursive (replace operation)
	 *
	 * @param a first char to be compared
	 * @param b second chat to be compared
	 * @return 0 if both characters are same, if not return 1
	 */
	@Contract(pure = true)
	private static int costOfSubstitution(char a, char b) {
		return a == b ? 0 : 1;
	}

	/**
	 * * used as support function for calculateDynRecursive
	 *
	 * @param numbers to get compared
	 * @return the minimum of stream numbers
	 */
	private static int min(int... numbers) {
		return Arrays.stream(numbers)
						.min().orElse(Integer.MAX_VALUE);
	}


}
