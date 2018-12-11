package application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;


/**
 * Implementation of a B+ tree to allow efficient access to
 * many different indexes of a large data set. 
 * BPTree objects are created for each type of index
 * needed by the program.  BPTrees provide an efficient
 * range search as compared to other types of data structures
 * due to the ability to perform log_m N lookups and
 * linear in-order traversals of the data items.
 * 
 * @author sapan (sapan@cs.wisc.edu)
 *
 * @param <K> key - expect a string that is the type of id for each item
 * @param <V> value - expect a user-defined type that stores all data for a food item
 */

/*
 * THERE'S STILL BUG'S!!!  I am having troubles with duplicates and occasionally the program crashes with an
 * IndexOutOfBounds Error when it's trying to insert a new node.  
 */
public class BPTree<K extends Comparable<K>, V> implements BPTreeADT<K, V> {

    // Root of the tree
    private Node root;
    // Branching factor is the number of children nodes 
    // for internal nodes of the tree
    private int branchingFactor;
    
    //Default Constructor
    public BPTree() {
        this.branchingFactor = 4;
        root = new LeafNode();
    }
    /**
     * Public constructor
     * 
     * @param branchingFactor 
     */
    public BPTree(int branchingFactor) {
        if (branchingFactor <= 2) {
            throw new IllegalArgumentException(
               "Illegal branching factor: " + branchingFactor);
        }
        this.branchingFactor = branchingFactor;
        root = new LeafNode();
    }
    
    
    /*
     * (non-Javadoc)
     * @see BPTreeADT#insert(java.lang.Object, java.lang.Object)
     */
    @Override
    public void insert(K key, V value) {
        root.insert(key, value);
    }
    
    
    /*
     * (non-Javadoc)
     * @see BPTreeADT#rangeSearch(java.lang.Object, java.lang.String)
     */
    @Override
    public List<V> rangeSearch(K key, String comparator) {
        if (!comparator.contentEquals(">=") && 
            !comparator.contentEquals("==") && 
            !comparator.contentEquals("<=") ) {
            throw new IllegalArgumentException("Illegal comparator: " + comparator);
        }
        else
            return root.rangeSearch(key, comparator);
    }
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        Queue<List<Node>> queue = new LinkedList<List<Node>>();
        queue.add(Arrays.asList(root));
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Queue<List<Node>> nextQueue = new LinkedList<List<Node>>();
            while (!queue.isEmpty()) {
                List<Node> nodes = queue.remove();
                sb.append('{');
                Iterator<Node> it = nodes.iterator();
                while (it.hasNext()) {
                    Node node = it.next();
                    sb.append(node.toString());
                    if (it.hasNext())
                        sb.append(", ");
                    if (node instanceof BPTree.InternalNode)
                        nextQueue.add(((InternalNode) node).children);
                }
                sb.append('}');
                if (!queue.isEmpty())
                    sb.append(", ");
                else {
                    sb.append('\n');
                }
            }
            queue = nextQueue;
        }
        return sb.toString();
    }
    
    
    /**
     * This abstract class represents any type of node in the tree
     * This class is a super class of the LeafNode and InternalNode types.
     * 
     * @author sapan
     */
    private abstract class Node {
        
        // List of keys
        List<K> keys;
        
        /**
         * Package constructor
         */
        Node() {
            this.keys = new ArrayList<K>();
        }
        
        /**
         * Inserts key and value in the appropriate leaf node 
         * and balances the tree if required by splitting
         *  
         * @param key
         * @param value
         */
        abstract void insert(K key, V value);

        /**
         * Gets the first leaf key of the tree
         * 
         * @return key
         */
        abstract K getFirstLeafKey();
        
        /**
         * Gets the new sibling created after splitting the node
         * 
         * @return Node
         */
        abstract Node split();
        
        /*
         * (non-Javadoc)
         * @see BPTree#rangeSearch(java.lang.Object, java.lang.String)
         */
        abstract List<V> rangeSearch(K key, String comparator);

        /**
         * 
         * @return boolean
         */
        abstract boolean isOverflow();
        
        public String toString() {
            return keys.toString();
        }
    
    } // End of abstract class Node
    
    /**
     * This class represents an internal node of the tree.
     * This class is a concrete sub class of the abstract Node class
     * and provides implementation of the operations
     * required for internal (non-leaf) nodes.
     * 
     * @author sapan
     */
    private class InternalNode extends Node {

        // List of children nodes
        List<Node> children;
        
        /**
         * Package constructor
         */
        InternalNode() {
            super();
            this.children = new ArrayList<Node>();
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#getFirstLeafKey()
         */
        K getFirstLeafKey() {
            return children.get(0).getFirstLeafKey();
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#isOverflow()
         */
        boolean isOverflow() {
            return children.size() > branchingFactor;
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#insert(java.lang.Comparable, java.lang.Object)
         */
        void insert(K key, V value) {       //needs fixing
            Node child = getChild(key);
            child.insert(key, value);
            if (child.isOverflow()) {
                Node brother = child.split();
                int valueIndex;
                int locate = Collections.binarySearch(keys, key);
                if(locate >= 0)
                    valueIndex = locate + 1;
                else
                    valueIndex = -locate - 1;
                
                if(locate >= 0)
                    children.set(valueIndex, brother);
                else {
                    keys.add(valueIndex,child.getFirstLeafKey());
                    children.set(valueIndex, brother);
                }
                
            }
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#split()
         */
        Node split() {      //Pretty sure working as intended
            int from = keys.size() / 2 + 1;
            int to = keys.size();
            InternalNode brother = new InternalNode(); // new node to hold split keys and children
            brother.keys.addAll(keys.subList(from, to));
            brother.children.addAll(children.subList(from, to + 1));
            
            //Clears the keys added into brother from the original node key and children list
            keys.subList(from - 1, to).clear();
            children.subList(from, to + 1).clear();
            
            return brother;
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#rangeSearch(java.lang.Comparable, java.lang.String)
         */
        List<V> rangeSearch(K key, String comparator) { //Pretty sure working as intended
            return getChild(key).rangeSearch(key, comparator);
        }
        
        //Returns the node located at Key.
        private Node getChild(K key) {      //Needs fixing
            int valueIndex;
            int locate = Collections.binarySearch(keys, key);
            if(locate >= 0) {
                valueIndex = locate;
                return children.get(valueIndex);
            }
            //Not found
            return null;
        }
    
    } // End of class InternalNode
    
    
    /**
     * This class represents a leaf node of the tree.
     * This class is a concrete sub class of the abstract Node class
     * and provides implementation of the operations that
     * required for leaf nodes.
     * 
     * @author sapan
     */
    private class LeafNode extends Node {
        
        // List of values
        List<V> values;
        
        // Reference to the next leaf node
        LeafNode next;
        
        // Reference to the previous leaf node
        LeafNode previous;
        
        /**
         * Package constructor
         */
        LeafNode() {
            super();
            values = new ArrayList<V>();
        }
        
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#getFirstLeafKey()
         */
        K getFirstLeafKey() {
            return keys.get(0);
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#isOverflow()
         */
        boolean isOverflow() {
            return values.size() > branchingFactor - 1;
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#insert(Comparable, Object)
         */
        void insert(K key, V value) { //Needs fixing
            int valueIndex;
            int locate = Collections.binarySearch(keys, key);
            if(locate >= 0)
                valueIndex = locate;
            else
                valueIndex = -locate - 1;
            
            //If key was found within the list update the value
            if(locate >= 0) {
                keys.add(valueIndex,key);
                values.add(valueIndex, value);
            } else { //If not add the key and set the value
                keys.add(valueIndex,key);
                values.add(valueIndex, value);
            }
            
            if(root.isOverflow()) {
                Node brother = split();
                InternalNode newRoot = new InternalNode();
                newRoot.keys.add(brother.getFirstLeafKey());
                newRoot.children.add(this);
                newRoot.children.add(brother);
                root = newRoot;
            }
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#split()
         */
        Node split() { //working
            int from = (keys.size() + 1) / 2;
            int to = keys.size();
            LeafNode brother = new LeafNode();
            brother.keys.addAll(keys.subList(from, to));
            brother.values.addAll(values.subList(from, to));

            keys.subList(from, to).clear();
            values.subList(from, to).clear();

            brother.next = next;
            brother.previous = this;
            next = brother;
            return brother;
        }
        
        /**
         * (non-Javadoc)
         * @see BPTree.Node#rangeSearch(Comparable, String)
         */
        List<V> rangeSearch(K key, String comparator) { //Pretty sure works as intended.
            List<V> filteredList = new LinkedList<V>();
            LeafNode curr = this;
            while(curr != null) {
                Iterator<K> keyIterator = curr.keys.iterator();
                Iterator<V> valIterator = curr.values.iterator();
                while(keyIterator.hasNext()) {
                    K keyIterVal = keyIterator.next();
                    V valIterVal = valIterator.next();
                    
                    int cmp = keyIterVal.compareTo(key);
                    if((comparator.contentEquals("==") && cmp == 0) || (comparator.contentEquals(">=") && cmp >= 0) ||
                                    (comparator.contentEquals("<=") && cmp <= 0)) {
                        filteredList.add(valIterVal);
                    }
                    }
                curr = curr.next;
            }
            return filteredList;
        }
        
    } // End of class LeafNode
    
    
    /**
     * Contains a basic test scenario for a BPTree instance.
     * It shows a simple example of the use of this class
     * and its related types.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // create empty BPTree with branching factor of 3
        BPTree<Double, Double> bpTree = new BPTree<>(3);
        // create a pseudo random number generator
        Random rnd1 = new Random();

        // some value to add to the BPTree
        Double[] dd = {0.0d, 0.5d, 0.2d, 0.8d};

        // build an ArrayList of those value and add to BPTree also
        // allows for comparing the contents of the ArrayList 
        // against the contents and functionality of the BPTree
        // does not ensure BPTree is implemented correctly
        // just that it functions as a data structure with
        // insert, rangeSearch, and toString() working.
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            Double j = dd[rnd1.nextInt(4)];
            list.add(j);
            bpTree.insert(j, j);
            System.out.println("\n\nTree structure:\n" + bpTree.toString());
        }
        List<Double> filteredValues = bpTree.rangeSearch(0.2d,">=");
        System.out.println("Filtered values: " + filteredValues.toString());
    }
} // End of class BPTree
