package com.eserciziJava.esercizio4.graph.tests;

import com.eserciziJava.esercizio4.graph.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGraph {

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

		v1 = new Vertex(1);
		v2 = new Vertex(2);
		v3 = new Vertex(3);
		v4 = new Vertex(4);
		v5 = new Vertex(5);

		undirectedGraph.addEdge(v1, v2, (double) 100);
		undirectedGraph.addEdge(v1, v3, (double) 100);
		undirectedGraph.addEdge(v3, v4, (double) 100);
		undirectedGraph.addEdge(v3, v5, (double) 100);
		undirectedGraph.addEdge(v2, v4, (double) 100);
		undirectedGraph.addEdge(v4, v5, (double) 100);

		directedGraph.addEdge(v1, v2, (double) 100);
		directedGraph.addEdge(v1, v3, (double) 100);
		directedGraph.addEdge(v3, v4, (double) 100);
		directedGraph.addEdge(v3, v5, (double) 100);
		directedGraph.addEdge(v2, v4, (double) 100);
		directedGraph.addEdge(v4, v5, (double) 100);


		//undirectedGraph.printGraph();
//directedGraph.printGraph();
	}


	@Test
	public void test_getAdjacentEdgesUndirectedGraph() {
	 	assertEquals(2, undirectedGraph.getAdjacentEdges(v1).get(0).getVertexA().getKey());
	 	assertEquals(3, undirectedGraph.getAdjacentEdges(v1).get(1).getVertexA().getKey());
	}

	@Test
	public void test_getAdjacentEdgesDirectedGraph() {
			assertEquals(2, directedGraph.getAdjacentEdges(v1).get(0).getVertexB().getKey());
			assertEquals(3, directedGraph.getAdjacentEdges(v1).get(1).getVertexB().getKey());
	}

	@Test
	public void test_isAdjacentUndirectedGraph() {
		assertTrue(undirectedGraph.isAdjacent(v1, v2));
		assertFalse(undirectedGraph.isAdjacent(v1, v5));
	}


	@Test
	public void test_isAdjacentDirectedGraph() {
		assertTrue(directedGraph.isAdjacent(v1, v2));
		assertFalse(directedGraph.isAdjacent(v1, v5));
	}


	@Test
	public void test_removeEdgeDirectedGraph() {

		assertTrue(directedGraph.isAdjacent(v1, v2));
		directedGraph.removeEdge(v1, v2);
		assertFalse(directedGraph.isAdjacent(v1, v2));
	}

	@Test
	public void test_removeEdgeUndirectedGraph() {
		assertTrue(undirectedGraph.isAdjacent(v1, v2));
		undirectedGraph.removeEdge(v1, v2);
		assertFalse(undirectedGraph.isAdjacent(v1, v2));
	}

	@Test
	public void test_getWeightDirectedGraph() {
		assertEquals(600, directedGraph.getWeight());
	}

	@Test
	public void test_getWeightUndirectedGraph() {
		assertEquals(600, undirectedGraph.getWeight());
	}

	@Test
	public void test_getVerticiesList() {
		assertTrue(directedGraph.getVerticiesList().contains(v1));
		assertTrue(directedGraph.getVerticiesList().contains(v2));
		assertTrue(directedGraph.getVerticiesList().contains(v3));
		assertTrue(directedGraph.getVerticiesList().contains(v4));
		assertTrue(directedGraph.getVerticiesList().contains(v5));
		assertEquals(5, directedGraph.getVerticiesList().size());
	}

	@Test
	public void test_getEdgeListDirectedGraph() {
		assertEquals(6, directedGraph.getEdgesList().size());
		directedGraph.removeEdge(v1,v2);
		assertEquals(5, directedGraph.getEdgesList().size());
	}

	@Test
	public void test_getEdgeListUndirectedGraph() {
		assertEquals(6, undirectedGraph.getEdgesList().size());
		directedGraph.removeEdge(v1,v2);
		assertEquals(5, directedGraph.getEdgesList().size());
	}

	@Test
	public void test_containEdgeUndirectedGraph(){
		assertTrue(undirectedGraph.containEdge(v1,v2));
		assertFalse(undirectedGraph.containEdge(v1,v5));

	}

	@Test
	public void test_containEdgeDirectedGraph(){
		assertTrue(directedGraph.containEdge(v1,v2));
		assertFalse(directedGraph.containEdge(v1,v2));
	}

	@Test
	public void test_getEdgesCountUndirectedGraph(){
		assertEquals(6,undirectedGraph.getEdgesCount());
	}

	@Test
	public void test_getEdgesCountDirectedGraph(){
		assertEquals(6,directedGraph.getEdgesCount());
	}


}
