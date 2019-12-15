/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eserciziJava.esercizio4.old;



import com.eserciziJava.esercizio4.old.graph.*;
import com.eserciziJava.esercizio4.old.unionFindSet.UnionFindSet;



import java.util.Collections;

import java.util.List;

/**
 *
 * @author n.gilli
 */
public class Kruskal{
    
    // Kruskal algorithm
    public static  Graph mstKruskal(Graph graph) throws GraphException {
        Graph tree = new UndirectedGraph();


        List vertexList = graph.getAllVertices();
        UnionFindSet set = new UnionFindSet();
        vertexList.forEach((v) -> {
            set.makeSet(v);
        });
        List<Edge>  edgeList = graph.getAllEdges();

        // Sorting edge list
        Collections.sort(edgeList);
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
