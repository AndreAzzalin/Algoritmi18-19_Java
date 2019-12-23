package com.eserciziJava.esercizio4;

import com.eserciziJava.esercizio4.graph.Graph;
import com.eserciziJava.esercizio4.graph.UndirectedGraph;
import com.eserciziJava.esercizio4.graph.Vertex;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eserciziJava.esercizio4.kruskal_union_find.Kruskal.mstKruskal;


public class KruskalDistance {

	public static void main(String[] args)  {

		HashMap<String, Vertex> vertices = new HashMap<>();
		Graph<Double> graph = new UndirectedGraph<>();

		Vertex vertexA;
		Vertex vertexB;


		String basePath = System.getProperty("user.dir") + getOS() + "src" + getOS() + "com" + getOS() + "eserciziJava" + getOS() + "esercizio4" + getOS() + "datasets" + getOS();
		String pathDataset = basePath + "italian_dist_graph.csv";



		System.out.println("Default path for dataset:");
				System.out.println("Path for dataset .csv -> " + pathDataset);

		Scanner sc = new Scanner(System.in);

		System.out.println("Do you want change dafault path? Y / N ");
		String choice = sc.nextLine();
		if (choice.equals("Y") || choice.equals("y")) {
			System.out.println("Insert new dataset .csv path:");
			pathDataset = sc.nextLine();
		}

		List<String> dataset = getDataset(pathDataset);

		//fetch all lines in dataset
		for (String line : dataset) {
			String[] row = line.split("[,]");

			//first element in line is source
			if (vertices.containsKey(row[0])) {
				vertexA = vertices.get(row[0]);
			} else {
				vertexA = new Vertex(row[0]);
				vertices.put(row[0], vertexA);
			}

			//second element in line is destination
			if (vertices.containsKey(row[1])) {
				vertexB = vertices.get(row[1]);
			} else {
				vertexB = new Vertex(row[1]);
				vertices.put(row[1], vertexB);
			}

			//third element in line is distance between source and destination
			double distance = Double.parseDouble(row[2]);
			graph.addEdge(vertexA, vertexB, distance);

		}

		Graph minimumForest = mstKruskal(graph);
		minimumForest.printGraph();

		System.out.println("\n\n===================================================\n\n");
		System.out.println("Weight: " + minimumForest.getWeight() / 1000);

		System.out.println("Vertices: " + minimumForest.getVerticiesList().size());
		System.out.println("Edges: " + minimumForest.getEdgesCount());

	}


	/**
	 * @param path path for dataset file
	 * @return list of all lines in dataset splitted by \n
	 */
	private static List<String> getDataset(String path) {
		List<String> dataset = new ArrayList<>();

		try (Stream<String> datasetFileStream = Files.lines(Paths.get(path))) {
			dataset = datasetFileStream
							.flatMap(str -> Arrays.stream(str.split("[\\r\\n]")))
							.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error: " + e + " | Insert valid .txt in dataset");
		}

		return dataset;
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
