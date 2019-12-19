package com.eserciziJava.esercizio4;



import com.eserciziJava.esercizio4.graph.Edge;
import com.eserciziJava.esercizio4.graph.Graph;
import com.eserciziJava.esercizio4.graph.UndirectedGraph;
import com.eserciziJava.esercizio4.graph.Vertex;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;


public class Kruskal{
    
    // Kruskal algorithm

    public static Graph mstKruskal(@NotNull Graph graph){
        Graph tree = new UndirectedGraph<>();


        List<Vertex> vertexList = graph.getVertexList();
        UnionFindSet set = new UnionFindSet();
        vertexList.forEach((v) -> {
            set.makeSet(v);
        });
        List<Edge> edgeList = graph.getEdgeList();
        // Sorting edge list
        Collections.sort(edgeList, new Comparator<Edge>() {
            @Override
            public int compare(Edge edge1, Edge edge2){
                return Double.compare((double) edge1.getWeight(), (double) edge2.getWeight());
            }
        });
        int i;
        Vertex v1;
        Vertex v2;


        for(i=0; i<edgeList.size(); i++){
            v1 = edgeList.get(i).getVertex1();
            v2 = edgeList.get(i).getVertex2();
            if(set.find(v1) != set.find(v2)){
                tree.addEdge(v1, v2, (double) edgeList.get(i).getWeight());
                set.union(v1, v2);
            }
        }
        return tree;
    }

}
