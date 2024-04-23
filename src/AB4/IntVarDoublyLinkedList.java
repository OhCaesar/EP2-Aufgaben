package AB4;

/**
 * A list of 'IntVar' objects implemented as a doubly linked list. The number of elements of the 
 * list is not limited. The list may also contain entries of 'null'.
 */
//
// TODO: define further classes and methods for the implementation of the doubly linked list, if
//  needed.
//
public class IntVarDoublyLinkedList {

    private IntVarDoublyLinkedNode originNode;
    private IntVarDoublyLinkedNode headNode;

    private int size;

    /**
     * Initializes 'this' as an empty list.
     */
    public IntVarDoublyLinkedList() {
        this.size = 0;
    }

    /**
     * Inserts the specified element 'v' at the beginning of this list.
     * @param v the variable that is added ('v' can also be 'null').
     */
    public void addFirst(IntVar v) {
        IntVarDoublyLinkedNode node = new IntVarDoublyLinkedNode(v);
        if(originNode==null) {
            originNode=node;
            headNode=originNode;
        }
        else originNode=originNode.setPrevious(node);
        size++;

    }

    /**
     * Appends the specified element 'v' to the end of this list.
     * @param v the variable that is added ('v' can also be 'null').
     */
    public void addLast(IntVar v) {
        IntVarDoublyLinkedNode node = new IntVarDoublyLinkedNode(v);
        if(headNode==null) {
            headNode=node;
            originNode=headNode;
        }
        else headNode=headNode.setNext(node);
        size++;
    }

    /**
     * Returns the last element in this list (at the end of the list) without removing it.
     * Returns 'null' if the list is empty.
     */
    public IntVar getLast() {
        if(headNode==null) return null;
        return headNode.getValue();
    }

    /**
     * Returns the first element in this list (at the beginning of the list) without removing it.
     * Returns 'null' if the list is empty.
     */
    public IntVar getFirst() {

        if(originNode==null) return null;
        return originNode.getValue();
    }

    /**
     * Retrieves and removes the first element in this list, that is, the element with index 0.
     * Indices of subsequent elements are decremented accordingly. Returns 'null' if the list is
     * empty.
     *
     * @return the first element in this list, or 'null' if the list is empty.
     */
    public IntVar pollFirst() {
        if(originNode==null) return null;
        IntVar returnValue = originNode.getValue();
        size--;
        if (originNode==headNode) {
            originNode = null; headNode = null;
            return returnValue;
        }
        originNode=originNode.getNext();
        originNode.setPrevious(null);
        return returnValue;
    }

    /**
     * Retrieves and removes the last element in this list, that is, the element with the highest
     * index. Returns 'null' if the list is empty.
     * @return the last element in this list, or 'null' if the list is empty.
     */
    public IntVar pollLast() {
        if(headNode==null) return null;
        IntVar returnValue = headNode.getValue();
        if (originNode==headNode) {
            originNode = null; headNode = null;
            return returnValue;
        }
        headNode=headNode.getPrevious();
        headNode.setNext(null);
        size--;
        return returnValue;
    }


    /**
     * Inserts the specified element at the specified position in this list, such that a
     * following invocation of get(i) would return 'v'. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (adds one to their indices).
     * @param i the position of the new element in the list, i >= 0 && i <= size().
     * @param v the body that is added ('v' can also be 'null').
     */
    public void add(int i, IntVar v) {
        if(i==0) addFirst(v);
        IntVarDoublyLinkedNode iterable = originNode;
        for (int j = 0; j < i; j++) {
            if (iterable==null) return;
            iterable=iterable.getNext();
        }
        IntVarDoublyLinkedNode previous = iterable.getPrevious();
        size++;
        new IntVarDoublyLinkedNode(v,previous,iterable);

    }

    /**
     * Returns the element with the specified index in this list. Returns 'null' if the list is
     * empty.
     * @param i the list index of the element to be retrieved, i >= 0 && i < size().
     * @return the retrieved element at the specified position.
     */
    public IntVar get(int i) {
        IntVarDoublyLinkedNode iterable = originNode;
        for (int j = 0; j < i; j++) {
            if (iterable==null) return null;
            iterable=iterable.getNext();
        }
        return iterable==null ? null:iterable.getValue();
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param i the index of the element to be replaced, i >= 0 && i < size().
     * @param v the new element to be set at the specified index ('v' can also be 'null').
     * @return the element that was replaced.
     */
    public IntVar set(int i, IntVar v) {
        if(i==0) addFirst(v);
        IntVarDoublyLinkedNode iterable = originNode;
        for (int j = 0; j < i; j++) {
            if (iterable==null) return null;
            iterable=iterable.getNext();
        }
        IntVar oldVal = iterable.getValue();
        iterable.setValue(v);
        return oldVal;
    }

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent
     * elements to the left (subtracts one from their indices). Returns the element that was
     * removed from the list.
     * @param i the index of the element to be removed, i >= 0 && i < size().
     * @return the removed element.
     */
    public IntVar remove(int i) {

        IntVarDoublyLinkedNode iterable = originNode;

        for (int j = 0; j < i; j++) {
            if (iterable == null) return null;
            iterable = iterable.getNext();
        }
        if(iterable==null) return null;
        iterable.getPrevious().setNext(iterable.getNext());

        size--;
        return iterable.getValue();
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if
     * this list does not contain the element. More formally, returns the highest index i such
     * that v == get(i), or -1 if there is no such index.
     * @param v the element to search for ('v' can also be 'null').
     * @return the index of the last occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     */
    public int lastIndexOf(IntVar v) {
        int count = 0;
        int lastIndex = -1;
        IntVarDoublyLinkedNode iterable = originNode;
        while (iterable!=null){
            if (iterable.getValue()==v) lastIndex = count ;
            iterable=iterable.getNext();
            count ++;
        }
        return lastIndex;
    }

    /**
     * Returns the number of entries in this list (including 'null' entries).
     * @return the number of entries in this list (including 'null' entries).
     */
    public int size() {
        if(size<0)size=0;
        return size;
    }
}

