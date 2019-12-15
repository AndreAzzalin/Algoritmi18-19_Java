/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eserciziJava.esercizio4.old;




import com.eserciziJava.esercizio4.old.graph.Graph;
import com.eserciziJava.esercizio4.old.graph.GraphException;
import com.eserciziJava.esercizio4.old.graph.UndirectedGraph;
import com.eserciziJava.esercizio4.old.graph.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static com.eserciziJava.esercizio4.old.Kruskal.mstKruskal;


/**
 *
 * @author n.gilli
 */
public class KruskalDistance {
    
    public static void main (String[] args){
        
        //String csvFile = args[0];
        String line = "";
        String cvsSplitBy = ",";
        HashMap<String, Vertex> vertexList = new HashMap();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Andrea\\IdeaProjects\\Java\\src\\com\\eserciziJava\\esercizio4\\datasets\\italian_dist_graph.csv"))) {

            Graph graph = new UndirectedGraph();
            Vertex v1;
            Vertex v2;
            while ((line = br.readLine()) != null) {

                String[] row = line.split(cvsSplitBy);

                if(vertexList.containsKey(row[0]))
                   v1 = vertexList.get(row[0]);
                else {
                    v1 = new Vertex(row[0]);
                    vertexList.put(row[0], v1);
                }

                if(vertexList.containsKey(row[1]))
                   v2 = vertexList.get(row[1]);
                else {
                    v2 = new Vertex(row[1]);
                    vertexList.put(row[1], v2);
                }

                double distance = Double.parseDouble(row[2]);                 
                graph.addEdge(v1, v2, distance);

            }                

            Graph minimumForest = mstKruskal(graph);
            System.out.println("Peso: " + minimumForest.getWeight()/1000);
            System.out.println("Numero vertici: " + minimumForest.getAllVertices().size());
            System.out.println("Numero archi: " + minimumForest.getAllEdges());
        } catch (IOException | GraphException e) {
            e.printStackTrace();
        }
    }
    
}
