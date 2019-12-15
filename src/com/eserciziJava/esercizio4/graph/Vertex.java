
package com.eserciziJava.esercizio4.graph;

public class Vertex<T>{ 
    String name;
    T key;
    
     public Vertex(T key) {
        this.key = key;
        this.name = null;
    }
     
    public Vertex(String name) {
        this.key = null;
        this.name = name;
    }

    public Vertex(String name, T key) {
        this.name = name;
        this.key = key;
    }
     
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Vertex{" + "name=" + name + ", key=" + key + '}';
    }
    
}
