
package com.eserciziJava.esercizio4.graph;


public class Edge<T>{
    
    Vertex vertex1;
    Vertex vertex2;
    T weight;


    public Edge(Vertex vertex1,Vertex vertex2, T weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public Edge(Vertex vertex1, T weight) {
        this.vertex1 = vertex1;
        this.vertex2 = null;
        this.weight = weight;
    }

    public Vertex getVertex1() {
        return vertex1;
    }

    public void setVertex1(Vertex vertex1) {
        this.vertex1 = vertex1;
    }

    public Vertex getVertex2() {
        return vertex2;
    }

    public void setVertex2(Vertex vertex2) {
        this.vertex2 = vertex2;
    }

    public T getWeight() {
        return weight;
    }

    public void setWeight(T weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "NotDirectedEdge{" + "vertex1=" + vertex1 + ", vertex2=" + vertex2 + ", weight=" + weight + '}';
    }
     
}
