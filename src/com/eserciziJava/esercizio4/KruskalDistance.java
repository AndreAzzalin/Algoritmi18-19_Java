/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eserciziJava.esercizio4;


import com.eserciziJava.esercizio4.graph.Graph;
import com.eserciziJava.esercizio4.graph.UndirectedGraph;
import com.eserciziJava.esercizio4.graph.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.eserciziJava.esercizio4.Kruskal.mstKruskal;


public class KruskalDistance {

	public static void main(String[] args) {

		HashMap<String, Vertex> vertices = new HashMap<>();
		Graph<Double> graph = new UndirectedGraph<>();

		Vertex v1;
		Vertex v2;

		String pathDataset = "C:\\Users\\Andrea\\IdeaProjects\\Java\\src\\com\\eserciziJava\\esercizio4\\datasets\\italian_dist_graph.csv";
		List<String> dataset = getDataset(pathDataset);


		for (String line : dataset) {
			String[] row = line.split("[,]");

			if (vertices.containsKey(row[0])) {
				v1 = vertices.get(row[0]);
			} else {
				v1 = new Vertex(row[0]);
				vertices.put(row[0], v1);
			}

			if (vertices.containsKey(row[1])) {
				v2 = vertices.get(row[1]);
			} else {
				v2 = new Vertex(row[1]);
				vertices.put(row[1], v2);
			}

			double distance = Double.parseDouble(row[2]);
			graph.addEdge(v1, v2, distance);

		}

		Graph minimumForest = mstKruskal(graph);
		minimumForest.printGraph();

		System.out.println("\n\n===================================================\n\n");
		System.out.println("Peso: " + minimumForest.weight() / 1000);

		System.out.println("Numero vertici: " + minimumForest.getVertexList().size());
		System.out.println("Numero archi: " + minimumForest.getEdgeNumber());

	}


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

}
