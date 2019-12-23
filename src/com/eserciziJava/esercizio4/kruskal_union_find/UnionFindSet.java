package com.eserciziJava.esercizio4.kruskal_union_find;
import java.util.HashMap;

public class UnionFindSet<T> {


    HashMap<T,T> elements = new HashMap<>();

    /**
     * makes a new set by creating a new element with a parent pointer to itself.
     * The parent pointer to itself indicates that the element is the representative
     * member of its own set. The makeSet operation has O(1) time complexity.
     *
     * @param key root of new set
     */
    public void makeSet(T key){
        elements.put(key, key);
    }

    /**
     * follows the chain of parent pointers from x upwards through the tree until an element
     * is reached whose parent is itself. This element is the root of the tree and is the representative
     * member of the set to which x belongs, and may be x itself.
     *
     * @param key key element for root search
     * @return the root of key param
     */
    public T find(T key) {
        return elements.get(key);
    }

    /**
     * Uses Find to determine the roots of the trees A and B belong to. If the roots are distinct, the trees
     * are combined by attaching the root of one to the root of the other. If this is done naively,
     * such as by always making B a child of A, the height of the trees can grow as O(n).
     *
     * @param a element of first set
     * @param b element of second set to be merged
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
     * @return all elements set
     */
    public HashMap<T, T> getElements() {
        return elements;
    }

    /**
     * @param elements set new elements for set
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