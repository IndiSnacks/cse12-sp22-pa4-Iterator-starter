/**
 * Name: Sahil Gathe	
 * ID: A16840774
 * Email: sgathe@ucsd.edu
 * Sources used: Tutors, Zybooks, and Lecture Slides
 * 
 * This file creates a linked list and holds the methods to interact with said linked list
 */
import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev - new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next - new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element - new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        }

        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        }
    }

    /**
     * This protected class implents the Link list ittrator 
     */
    protected class MyListIterator implements ListIterator<E>{
        Node left;
        Node right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;

        /**
        * Constructor of the Iterator
        */
        public MyListIterator(){
            this.left = head;
            this.right = right.getNext();
            this.idx = 0;
            this.forward = true;
            this.canRemoveOrSet = false;
        }

        /**
        * @return true if there is an element node going foward
        * and false otherwise 
        */
        public boolean hasNext(){
            if(forward == true){
                if(right.data != null){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                if(left.data != null){
                    return true;
                }
                else{
                    return false;
                }
            }
        }

        /**
         * @return the next element in list going foward
         */
        public E next(){
            if(hasNext() == false){
                throw new NoSuchElementException("Next elemts dose not exist");
            }
            else if(forward == true){
                E rightData = right.data;
                idx++;
                right = right.getNext();
                left = right.getPrev();
                return rightData;
            }
            else{
                E leftData = left.data;
                idx--;
                left = left.getPrev();
                right = left.getNext();
                return leftData;
            }
        }

        /**
         * @return true if there is an element note when going backwards
         * and false otherwise 
         */
        public boolean hasPrevious(){
            if(forward == true){
                if(left.data != null){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                if(right.data != null){
                    return true;
                }
                else{
                    return false;
                }
            }
        }

        /**
         * @return the next element in the list when going backward
         */
        public E previous(){
            if(hasPrevious() == false){
                throw new NoSuchElementException("Previous has no element");
            }
            else{
                if(forward == true){
                    idx--;
                    E data = left.data;
                    left = left.getPrev();
                    right = left.getNext();
                    return data;
                }
                else{
                    idx++;
                    E data = right.data;
                    right = right.getNext();
                    left = right.getPrev();
                    return data;

                }
            }
        }

        /**
         * @return  the index of the element that would be returned by a 
         * call to next()
         */
        public int nextIndex(){
            if(idx != 0){
                return idx + 1;
            }
            else{
                return -1;
            }
        }

        /**
         * @return Return the index of the
         * element that would be returned by a call to previous().
         */
        public int previousIndex(){
            return 0;
        }

        /**
         * Insert the given item into the list 
         * immediately before the element that would be returned by next()
         * @return newly added item if we call previous() 
         * immediately following add
         */
        public void add(E element){

        }

        /**
         * replacces the value of the more recently next()/prevoius() called node
         * with new value element
         */
        public void set(E elements){

        }

        /**
         * removes the last elemnt returned by the most 
         * recent next()/previous() call
         */
        public void remove(){
            
        }
    }

    //  Implementation of the MyLinkedList Class
    /** Only 0-argument constructor is defined */
    public MyLinkedList() {
        this.size = 0;
        this.head = new Node(null);
        this.tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
    }

    /**
     * @returns the current size of the arraylist 
     */
    @Override
    public int size() {
        return this.size; 
    }

    /**
     * given a index argument 
     * @returns the vaule of the node in that positon in that index
     * in the linked list. 
     */
    @Override
    public E get(int index) {
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException("get index out of bounds");
        }
        else{
            Node tempnode = getNth(index);
            return tempnode.getElement();
        }
    }

    /**
     * creates a new node object 
     * @param data will be the value of the node
     * @param Index will be the index of the node
     * @returns true if node is created
     * @throws NullPointerExcption if data is null 
     */
    @Override
    public void add(int index, E data) {
        size++;
       if(data == null){
           throw new NullPointerException("null input into add");
       }
       else if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("add index out of bounds");
       }
       else{
            Node tempNode = getNth(index);
            Node newNode = new Node(data);
            if(index == 0){
                newNode.setPrev(head);
                newNode.setNext(head.next);
                head.getNext().setPrev(newNode);
                head.setNext(newNode);
            }
            else if(index == size -1){
                tail.getPrev().setNext(newNode);
                newNode.setPrev(tail.getPrev());
                tail.setPrev(newNode);
                newNode.setNext(tail);
            }
            else{
                tempNode.getPrev().setNext(newNode);
                newNode.setPrev(tempNode.getPrev());
                tempNode.setPrev(newNode);
                newNode.setNext(tempNode);
                tempNode.getNext().setPrev(tempNode);
            }
       }
    }

    /**
     * creates a new node 
     * @param data as the node value
     * @return true if node is created 
     * @throws NullPointerExption if data is null
     */
    public boolean add(E data) {
        if(data == null){
            throw new NullPointerException("a null data point is being added");
        }else{
            size++;
            Node addNode = new Node(data);
            tail.prev.setNext(addNode);
            addNode.setPrev(tail.prev);
            addNode.setNext(tail);
            tail.setPrev(addNode);
            return true;
        }
    }

    /**
     * replaces a given index
     * @param index with a value
     * @param data
     * @return the previously stored value
     */
    public E set(int index, E data) {
        if(data == null){
            throw new NullPointerException("set data is null");
        }
        else if(index > size || index < 0){
            throw new IndexOutOfBoundsException("set index out of bounds");
        }
        else{
            Node tempNode = getNth(index);

            Node replacemntNode = new Node(data);
            replacemntNode.setNext(tempNode.next);
            replacemntNode.setPrev(tempNode.prev);
            tempNode.getNext().setPrev(replacemntNode);
            tempNode.getPrev().setNext(replacemntNode);
            return tempNode.data;
        }
    }

    /**
     * removes the node at a give
     * @param index
     */
    public E remove(int index) {
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("remove index out of bounds");
        }
        else if(index == 0){
            Node tempNode = head.getNext();
            head.setNext(tempNode.getNext());
            tempNode.getNext().setPrev(head);

            size--;
            return tempNode.getElement();
        }
        else{
            Node tempNode = getNth(index);

            tempNode.getNext().setPrev(tempNode.getPrev());
            tempNode.getPrev().setNext(tempNode.getNext());
            size--;
            return tempNode.getElement();
        }
    }

    /**
     * removes all the node in the arraylist
     */
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    /**
     * @return true if the size is 0
     */
    public boolean isEmpty() {
        if(size > 0){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * takes in 
     * @param index 
     * @return the node at index
     */
    protected Node getNth(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("get index out of bounds");
        }
        else{
            Node tempNode = head;
            for(int i = 0; i <= index; i++){
                tempNode = tempNode.getNext();
            }

            return tempNode;
        }
    }
}