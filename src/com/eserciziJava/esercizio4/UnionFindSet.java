/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eserciziJava.esercizio4;
import java.util.HashMap;

/**
 *
 * @author n.gilli
 */
public class UnionFindSet<T> {
 
    HashMap<T,T> elements = new HashMap<>();
            
    public void makeSet(T key){
        elements.put(key, key);
    }

    public T find(T key) {
        return elements.get(key);
    }
    
    // unisce gli insiemi a e b in un unico insieme, di nome a
    public void union(T a, T b){
        a = find(a);
        b = find(b);
        for (T x: elements.keySet()){
            if(elements.get(x).equals(b))
                elements.put(x, a);
        }
    }

    public HashMap<T, T> getElements() {
        return elements;
    }

    public void setElements(HashMap<T, T> elements) {
        this.elements = elements;
    }
    
    @Override
    public String toString() {
        return "UnionFindSet{" + "elements=" + elements + '}';
    }
    
    
}
