package com.eserciziJava.esercizio4.kruskal_union_find.tests;

import com.eserciziJava.esercizio4.graph.DirectedGraph;
import com.eserciziJava.esercizio4.graph.Graph;
import com.eserciziJava.esercizio4.graph.UndirectedGraph;
import com.eserciziJava.esercizio4.graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.eserciziJava.esercizio4.kruskal_union_find.Kruskal.mstKruskal;
import static org.junit.jupiter.api.Assertions.*;

public class TestKruskal {

	Graph<Double> undirectedGraph;
	Graph<Double> directedGraph;
	Vertex v1;
	Vertex v2;
	Vertex v3;
	Vertex v4;
	Vertex v5;

	@BeforeEach
	public void createGraph() {

		undirectedGraph = new UndirectedGraph<>();
		directedGraph = new DirectedGraph<>();

		v1 = new Vertex("alpha");
		v2 = new Vertex("bravo");
		v3 = new Vertex("delta");
		v4 = new Vertex("charlie");
		v5 = new Vertex("echo");

		undirectedGraph.addEdge(v1, v2, (double) 20);
		undirectedGraph.addEdge(v1, v3, (double) 25);
		undirectedGraph.addEdge(v3, v4, (double) 15);
		undirectedGraph.addEdge(v3, v5, (double) 10);
		undirectedGraph.addEdge(v2, v4, (double) 40);
		undirectedGraph.addEdge(v4, v5, (double) 50);

		directedGraph.addEdge(v1, v2, (double) 20);
		directedGraph.addEdge(v1, v3, (double) 25);
		directedGraph.addEdge(v3, v4, (double) 15);
		directedGraph.addEdge(v3, v5, (double) 10);
		directedGraph.addEdge(v2, v4, (double) 40);
		directedGraph.addEdge(v4, v5, (double) 50);


		//undirectedGraph.printGraph();
		// directedGraph.printGraph();
	}

	@Test
	public void test_getMSTKruskalDirectedGraph(){
		Graph minTree = mstKruskal(directedGraph);
		//mi aspoetto che l'albero minimo non contenga più l'arco tra 2 --- 3 e 3 --- 4
		assertFalse(minTree.containEdge(v2,v4));
		assertFalse(minTree.containEdge(v4,v5));
	}

	@Test
	public void test_getMSTKruskalUndirectedGraph(){
		Graph minTree = mstKruskal(undirectedGraph);
		//mi aspoetto che l'albero minimo non contenga più l'arco tra 2 --- 3 e 3 --- 4
		assertFalse(minTree.containEdge(v2,v4));
		assertFalse(minTree.containEdge(v4,v5));
	}



}
