package AB3;

import AB2.Body;

public class BodyLinkedListNode {
    private BodyLinkedListNode next;
    private Body value;

    public BodyLinkedListNode(Body body){
        this.value = body;
    }

    public BodyLinkedListNode(Body body,BodyLinkedListNode next){
        this.value = body;
        this.next = next;
    }


    public BodyLinkedListNode getNext() {return this.next;};
    public Body getValue() {return this.value;};

    /**
     *
     * @param next - New Node Link
     */
    public void setNext(BodyLinkedListNode next){ this.next = next; }
}
