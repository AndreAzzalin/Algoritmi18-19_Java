package com.eserciziJava.esercizio3;

import java.util.HashMap;

public class UnionFindSet<T> {
 
    HashMap<T,T> elements = new HashMap<>();

    /**
     * @param key
     */
    public void makeSet(T key){
        elements.put(key, key);
    }

    /**
     * @param key
     * @return
     */
    public T find(T key) {
        return elements.get(key);
    }

    /**
     * unisce gli insiemi a e b in un unico insieme, di nome a
     *
     * @param a
     * @param b
     */
    public void union(T a, T b){
        a = find(a);
        b = find(b);
        for (T x: elements.keySet()){
            if(elements.get(x).equals(b))
                elements.put(x, a);
        }
    }

    /**
     * @return
     */
    public HashMap<T, T> getElements() {
        return elements;
    }

    /**
     * @param elements
     */
    public void setElements(HashMap<T, T> elements) {
        this.elements = elements;
    }

    /**
     * @return format for print element to terminal
     */
    @Override
    public String toString() {
        return "UnionFindSet{" + "elements=" + elements + '}';
    }
    
    
}
