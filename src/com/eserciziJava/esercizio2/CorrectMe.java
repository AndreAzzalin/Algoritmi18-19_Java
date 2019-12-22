package com.eserciziJava.esercizio2;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class CorrectMe {


	public static void main(String[] args) {


/*
    Abbiamo ordinato il dizionario per lunghezza delle parole (sovrascrivendo il criterio di ordinamento
    con la lunghezze delle stringhe) e stampandolo su un nuovo file di testo ("dictionary_sorted.txt").
    Utilizzare questo dizionario conviene perchè una volta trovata l'edit_distance minima,
    il correttore non deve piú calcolare l'edit distance quando la lunghezza della parola del dizionario
    risulta maggiore dell'edit distance minima.

    https://www.baeldung.com/java-levenshtein-distance
    https://www.geeksforgeeks.org/edit-distance-dp-using-memoization/
    https://dzone.com/articles/java-8-automatic-memoization


    MEMOIZATIONN = This algorithm performs significantly better than the recursive implementation. However, it involves significant memory consumption
 */


		String basePath = System.getProperty("user.dir") + getOS() + "src" + getOS() + "com" + getOS() + "eserciziJava" + getOS() + "esercizio2" + getOS() + "datasets" + getOS();
		String pathCorrectMe = basePath + "correctme.txt";
		String pathDictionary = basePath + "dictionary.txt";


		System.out.println("Default path for datasets:");
		System.out.println("Path for correctme.txt -> " + pathCorrectMe);
		System.out.println("Path for dictionary.txt -> " + pathDictionary);

		Scanner sc = new Scanner(System.in);

		System.out.println("Do you want change dafault path? Y / N ");
		String choice = sc.nextLine();
		if (choice.equals("Y") || choice.equals("y")) {
			System.out.println("Insert new correctme.txt path:");
			pathCorrectMe = sc.nextLine();
			System.out.println("Insert new dictionary.txt path:");
			pathDictionary = sc.nextLine();
		}


		assumeCorrections(pathCorrectMe, pathDictionary);

	}


	/**
	 * print foreach word in correctMe file possible corrected word fetched from dictionary file and the minimum number operations for correct it
	 * with dynamically approach and memoization (best way)
	 *
	 * @param pathCorrectMe  path for correctMe.txt file
	 * @param pathDictionary path for dictionary.txt file
	 */
	private static void assumeCorrections(String pathCorrectMe, String pathDictionary) {
		List<String> correctMe = getCorrectMeList(pathCorrectMe);
		List<String> dictionary = getDictionaryList(pathDictionary);
		List<String> possibleStrings = new ArrayList<>();

		long startTime = System.currentTimeMillis();
		int editDistMin = Integer.MAX_VALUE;
		int editDistRes = 0;


		for (String toCorrectString : correctMe) {
			for (String dicString : dictionary) {
				if (dicString.length() - toCorrectString.length() > editDistMin) {
					break;
				}

				editDistRes = EditDistance.calculateDyn(toCorrectString, dicString);

				if (editDistRes < editDistMin) {
					editDistMin = editDistRes;
					possibleStrings.clear();
					possibleStrings.add(dicString);
				} else if (editDistMin == editDistRes) {
					possibleStrings.add(dicString);
				}
			}

			System.out.println("\nWord: " + toCorrectString + " Edit distance: " + editDistMin);
			possibleStrings.forEach(item -> System.out.print(" | " + item));

			//reset var for next correctMe word
			possibleStrings.clear();
			editDistMin = Integer.MAX_VALUE;
		}


		long endTime = System.currentTimeMillis();
		System.out.println("\n\nExecution time: " + ((endTime - startTime) / 1000) + "s");
	}


	/**
	 * @param path for correctMe.txt file
	 * @return list of string fetched form correctMe.txt file
	 */
	private static List<String> getCorrectMeList(String path) {
		List<String> correctMeList = new ArrayList<>();

		try (Stream<String> correctMeFileStream = Files.lines(Paths.get(path))) {
			//dallo stream del file filtra gli spazi e inserisci il contenuto in una lista
			correctMeList = correctMeFileStream
							.flatMap(str -> Arrays.stream(str.split("\\W"))
											.map(String::toLowerCase)
											.filter(not(items -> items.equals(""))))
							.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error: " + e + " | Insert valid .txt in datasets");
		}

		return correctMeList;
	}

	/**
	 * @param path for dictionary.txt file
	 * @return list of string fetched form dictionary.txt file
	 */
	private static List<String> getDictionaryList(String path) {
		List<String> dictionaryList = new ArrayList<>();

		try (Stream<String> dictionaryFileStream = Files.lines(Paths.get(path))) {
			Comparator<String> sortByLength = Comparator.comparingInt(String::length);
			dictionaryList = dictionaryFileStream
							.sorted(sortByLength)
							.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error: " + e + " | Insert valid .txt in datasets");
		}

		return dictionaryList;
	}

	/**
	 * @return \\ or // if system based on Windows or Unix
	 */
	@NotNull
	private static String getOS() {
		String os = System.getProperty("os.name");

		if (os.contains("Windows"))
			return "\\";
		else
			return "//";


	}

}


