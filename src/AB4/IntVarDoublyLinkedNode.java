package AB4;

public class IntVarDoublyLinkedNode {

    //Previous
    private IntVarDoublyLinkedNode previous;
    //Next
    private IntVarDoublyLinkedNode next;
    //Data
    private IntVar value;

    /**
     *
     * @param value
     */
    public IntVarDoublyLinkedNode(IntVar value){
        this.value=value;
    }

    /**
     *
     * @param value
     * @param previous
     * @param next
     */
    public IntVarDoublyLinkedNode(IntVar value,IntVarDoublyLinkedNode previous,IntVarDoublyLinkedNode next){
        this.value = value;
        setNext(next);
        setPrevious(previous);
    }

    public IntVarDoublyLinkedNode getNext() {return next;}
    public IntVarDoublyLinkedNode getPrevious() {return previous;}
    public IntVar getValue(){return value;}
    public void setValue(IntVar value){this.value=value;}
    public IntVarDoublyLinkedNode setPrevious(IntVarDoublyLinkedNode previous){
        if(previous==null) return this.previous=null;
        if(this.previous!=null) previous.previous = this.previous;
        //Avoiding loop between set methods
        if (previous.getNext()==null) previous.next=this;
        return this.previous=previous;
    }
    public IntVarDoublyLinkedNode setNext(IntVarDoublyLinkedNode next){
        if(next==null) return this.next=null;
        if(this.next !=null) next.next=this.next;
        //Avoiding loop between set methods
        if (next.getPrevious()==null) next.previous=this;
        return this.next = next;
    }


}
