package com.eserciziJava.esercizio4.old.graph.tests;

import com.eserciziJava.esercizio4.old.graph.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTests {

	private Graph<Integer> directedGraph, undirectedGraph;
	private int v1, v2, v3, v4;


	public void setUp() {
		v1 = 1;
		v2 = 2;
		v3 = 3;
		v4 = 4;

		directedGraph = new DirectedGraph<>();
		undirectedGraph = new UndirectedGraph<>();
	}

	@BeforeEach
	public void setUpFill() throws GraphException {
		v1 = 1;
		v2 = 2;
		v3 = 3;
		v4 = 4;

		directedGraph = new DirectedGraph<>();
		undirectedGraph = new UndirectedGraph<>();

		directedGraph.addVertex(v1);
		directedGraph.addVertex(v2);
		directedGraph.addVertex(v3);
		directedGraph.addVertex(v4);

		undirectedGraph.addVertex(v1);
		undirectedGraph.addVertex(v2);
		undirectedGraph.addVertex(v3);
		undirectedGraph.addVertex(v4);
	}


	@Test
	public void test_getNumVertices() {
		assertEquals(4, directedGraph.getNumVertices());
		assertEquals(4, undirectedGraph.getNumVertices());
	}

	@Test
	public void test_addEdgeDirectedGraph() throws GraphException {
		directedGraph.addEdge(v1, v2, (double) 2);
		List<Edge<Integer>> expectedList = new ArrayList<>();
		expectedList.add(directedGraph.getEdge(v1, v2));

		assertEquals(expectedList, directedGraph.getAllEdges());
	}

	@Test
	public void test_addEdgeUndirectedGraph() throws GraphException {
		undirectedGraph.addEdge(v1, v2, (double) 2);

		assertTrue(undirectedGraph.getAllEdges().contains(undirectedGraph.getEdge(v1, v2)));
	}

	@Test
	public void test_getEdge() throws GraphException {


		undirectedGraph.addEdge(v1, v2, (double) 200);

		Edge<Integer> expectedEdge = new Edge<>(undirectedGraph.getVertex(v1), undirectedGraph.getVertex(v2), (double) 200);

		assertTrue(expectedEdge.getVertex1() == undirectedGraph.getEdge(v1, v2).getVertex1() && expectedEdge.getVertex2() == undirectedGraph.getEdge(v1, v2).getVertex2());

	}

	@Test
	public void test_getAdj() throws GraphException {

		undirectedGraph.addEdge(v1, v2, (double) 2);
		undirectedGraph.addEdge(v1, v3, (double) 2);
		List<Vertex<Integer>> list = new ArrayList<>();

		list.add(undirectedGraph.getVertex(v2));
		list.add(undirectedGraph.getVertex(v3));

		assertEquals(list, undirectedGraph.getAdj(v1));
	}

	@Test
	public void test_isDirected() {
		assertTrue(directedGraph.isDirected());
		assertFalse(undirectedGraph.isDirected());
	}

	@Test
	public void test_getAllVertex() throws GraphException {
		List<Vertex<Integer>> expectedVertexList = new ArrayList<>();

		expectedVertexList.add(undirectedGraph.getVertex(v1));
		expectedVertexList.add(undirectedGraph.getVertex(v2));
		expectedVertexList.add(undirectedGraph.getVertex(v3));
		expectedVertexList.add(undirectedGraph.getVertex(v4));

		assertEquals(expectedVertexList, undirectedGraph.getAllVertices());
	}

	@Test
	public void test_getAllEdgesUndirectedGraph() throws GraphException {


		undirectedGraph.addEdge(v1, v2, (double) 100);
		undirectedGraph.addEdge(v1, v3, (double) 100);
		undirectedGraph.addEdge(v1, v4, (double) 100);


		List<Edge<Integer>> edgesList = undirectedGraph.getAllEdges();

		assertTrue(edgesList.contains(undirectedGraph.getEdge(v1, v2)) && edgesList.contains(undirectedGraph.getEdge(v2, v1)));

	}

	@Test
	public void test_getAllEdgesDirectedGraph() throws GraphException {
		List<Edge<Integer>> expectedEdgesList = new ArrayList<>();

		directedGraph.addEdge(v1, v2, (double) 100);
		directedGraph.addEdge(v1, v3, (double) 100);
		directedGraph.addEdge(v1, v4, (double) 100);

		expectedEdgesList.add(directedGraph.getEdge(v1, v2));
		expectedEdgesList.add(directedGraph.getEdge(v1, v3));
		expectedEdgesList.add(directedGraph.getEdge(v1, v4));


		List<Edge<Integer>> List = directedGraph.getAllEdges();
		assertEquals(expectedEdgesList, List);

	}

	@Test
	public void test_deleteVertexUndirectedGraph() throws GraphException {
		undirectedGraph.addEdge(v1, v2, (double) 100);
		undirectedGraph.addEdge(v1, v3, (double) 100);
		undirectedGraph.addEdge(v3, v4, (double) 100);


		assertTrue(undirectedGraph.vertexExists(v1));
		assertTrue(undirectedGraph.edgeExists(v1, v2));
		assertTrue(undirectedGraph.edgeExists(v2, v1));

		undirectedGraph.getEdge(v1,v2);
		undirectedGraph.getEdge(v2,v1);


		List<Vertex<Integer>> vTest = undirectedGraph.getAllVertices();
		List<Edge<Integer>> eTest = undirectedGraph.getAllEdges();

		undirectedGraph.deleteVertex(v1);


		List<Vertex<Integer>> afterV = undirectedGraph.getAllVertices();
		List<Edge<Integer>> afterE = undirectedGraph.getAllEdges();
		assertFalse(undirectedGraph.vertexExists(v1));
		assertFalse(undirectedGraph.edgeExists(v1, v2));
		assertFalse(undirectedGraph.edgeExists(v2, v1));
	}

	@Test
	public void test_deleteVertexDirectedGrapg() throws GraphException {
		directedGraph.addEdge(v1, v2, (double) 100);
		directedGraph.addEdge(v1, v3, (double) 100);
		directedGraph.addEdge(v3, v4, (double) 100);
		directedGraph.addEdge(v3, v1, (double) 100);

		assertTrue(directedGraph.vertexExists(v1));
		assertTrue(directedGraph.edgeExists(v1, v2));


		List<Vertex<Integer>> vTest = directedGraph.getAllVertices();
		List<Edge<Integer>> eTest = directedGraph.getAllEdges();

		directedGraph.deleteVertex(v1);


		List<Vertex<Integer>> afterV = directedGraph.getAllVertices();
		List<Edge<Integer>> afterE = directedGraph.getAllEdges();
		assertFalse(directedGraph.vertexExists(v1));
		assertFalse(directedGraph.edgeExists(v1, v2));
		assertFalse(directedGraph.edgeExists(v1, v3));
	}

	@Test
	public void test_deleteEdgeUndirectedGraph() throws GraphException {

		undirectedGraph.addEdge(v1, v2, (double) 100);
		undirectedGraph.addEdge(v1, v3, (double) 100);
		undirectedGraph.addEdge(v3, v4, (double) 100);

		List<Edge<Integer>> beforeUn = undirectedGraph.getAllEdges();

		assertTrue(undirectedGraph.edgeExists(v1, v2));
		assertTrue(undirectedGraph.edgeExists(v2, v1));

		undirectedGraph.deleteEdge(v1, v2);

		List<Edge<Integer>> after = undirectedGraph.getAllEdges();

		assertFalse(undirectedGraph.edgeExists(v1, v2));
		assertFalse(undirectedGraph.edgeExists(v2, v1));

	}

	@Test
	public void test_deleteEdgeDirectedGraph() throws GraphException {

		directedGraph.addEdge(v1, v2, (double) 100);
		directedGraph.addEdge(v1, v3, (double) 100);
		directedGraph.addEdge(v3, v4, (double) 100);

		undirectedGraph.deleteEdge(v1, v2);

		assertFalse(undirectedGraph.edgeExists(v1, v2));
	}

}
