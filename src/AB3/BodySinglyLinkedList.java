package AB3;

import AB2.Body;

/**
 * A list of bodies implemented as a singly linked list. The number of elements of the list is
 * not limited.
 */
//
// TODO: define further classes and methods for the implementation of the singly linked list, if
//  needed.
//
public class BodySinglyLinkedList {


    private int listSize;
    private BodyLinkedListNode head;

    /**
     * Initializes 'this' as an empty list.
     */
    public BodySinglyLinkedList() {
        listSize=0;
    }

    /**
     * Constructor: initializes this list as an independent copy of the specified list.
     * Calling methods of this list will not affect the specified list
     * and vice versa.
     *
     * @param list the list from which elements are copied to the new list, list != null.
     */
    public BodySinglyLinkedList(BodySinglyLinkedList list) {
        BodyLinkedListNode currentNode = list.head;
        listSize=0;
        while (currentNode!=null){
            addFirst(currentNode.getValue());

            currentNode=currentNode.getNext();
        }
    }

    /**
     * Inserts the specified element 'b' at the beginning of this list.
     *
     * @param b the body that is added (b can also be 'null').
     */
    public void addFirst(Body b) {
        if(head==null) {
            head = new BodyLinkedListNode(b);
            listSize++;
            return;
        }

        BodyLinkedListNode currentNode = head;
        while (currentNode.getNext()!=null){
            currentNode=currentNode.getNext();
        }
        currentNode.setNext(new BodyLinkedListNode(b));
        listSize++;
    }

    /**
     * Appends the specified element 'b' to the end of this list.
     *
     * @param b the body that is added (b can also be 'null').
     */
    public void addLast(Body b) {
        head = new BodyLinkedListNode(b,head);
        listSize++;
    }

    /**
     * Returns the last element in this list (at the end of the list) without removing it.
     * Returns 'null' if the list is empty.
     */
    public Body getLast() {
        if (head!=null) return head.getValue();
        else return null;
    }

    /**
     * Returns the first element in this list (at the beginning of the list) without removing it.
     * Returns 'null' if the list is empty.
     */
    public Body getFirst() {
        if(head==null) return null;

        BodyLinkedListNode currentNode = head;
        while (currentNode.getNext()!=null){
            currentNode=currentNode.getNext();
        }

        return currentNode.getValue();
    }

    /**
     * Retrieves and removes the first element in this list, that is, the element with index 0.
     * Indices of subsequent elements are decremented accordingly. Returns 'null' if the list is
     * empty.
     *
     * @return the first element in this list, or 'null' if the list is empty.
     */
    public Body pollFirst() {
        if(listSize==1) {
            Body returningNode = head.getValue();
            head = null;
            listSize--;
            return returningNode;
        }

        BodyLinkedListNode currentNode = head;


        while (currentNode.getNext() != null && currentNode.getNext().getNext()!=null ){
            currentNode=currentNode.getNext();
        }
        Body returningNode = null;

        if(currentNode.getNext()!=null) returningNode = currentNode.getNext().getValue();
        currentNode.setNext(null);

        listSize--;
        return returningNode;
    }

    /**
     * Retrieves and removes the last element in this list, that is, the element with the highest
     * index. Returns 'null' if the list is empty.
     *
     * @return the last element in this list, or 'null' if the list is empty.
     */
    public Body pollLast() {
        Body returnValue = null;
        if(head!=null) returnValue = head.getValue();

        head = head.getNext();
        listSize--;

        return returnValue;
    }

    /**
     * Inserts the specified element at the specified position in this list, such that a
     * following invocation of get(i) would return 'b'. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param i the position of the new element in the list, i >= 0 && i <= size().
     * @param b the body that is added (b can also be 'null').
     */
    public void add(int i, Body b) {
        int pos = listSize; //HEADS POSITION


        BodyLinkedListNode currentNode = head;
        while (currentNode.getNext()!=null){
            if(pos-1 == i)
            {
                BodyLinkedListNode newNode = new BodyLinkedListNode(b,currentNode.getNext());
                currentNode.setNext(newNode);
                listSize++;
                return;
            }

            currentNode=currentNode.getNext();
            pos--;
        }

        if(i==listSize) addLast(b);

    }

    /**
     * Returns the element with the specified index in this list. Returns 'null' if the list is
     * empty.
     *
     * @param i the list index of the element to be retrieved, i >= 0 && i < size().
     * @return the retrieved element at the specified position.
     */
    public Body get(int i) {

        int pos = listSize-1; //HEADS POSITION

        BodyLinkedListNode currentNode = head;
        while (currentNode !=null){
            if(pos == i)    return currentNode.getValue();

            currentNode=currentNode.getNext();
            pos--;
        }
        return null;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if
     * this list does not contain the element. More formally, returns the lowest index i such
     * that b == get(i), or -1 if there is no such index.
     *
     * @param b the body to search for.
     * @return the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     */
    public int indexOf(Body b) {
        int indexCounter = 0;
        BodyLinkedListNode currentNode = head;
        while (currentNode!=null){
            if (currentNode.getValue().equals(b)) return listSize-1-indexCounter;
            currentNode=currentNode.getNext();
            indexCounter++;
        }

        return -1;
    }

    /**
     * Returns the number of entries in this list (including 'null' entries).
     */
    public int size() {
        return listSize;
    }
}

