package AB4;

public class IntVarConstMapNode {
    private IntVar key;
    private IntConst value;
    private IntVarConstMapNode leftChild;
    private IntVarConstMapNode rightChild;

    public IntVarConstMapNode(IntVar key,IntConst value){
        this.key=key;
        this.value=value;
    }

    public IntConst getValue(){return value;}
    public IntVar getKey(){return key;}

    public IntVarConstMapNode addNode(IntVarConstMapNode node){
        if(key.equals(node.key)) {
            value = node.value;
            return this;
        }
        if(node.key.getName().compareTo(this.key.getName())<0){
            //kleiner als diese Node
            if(leftChild==null) return leftChild=node;
            return leftChild.addNode(node);
        }
        //größer oder gleich als diese Node
        if(rightChild==null) return rightChild=node;
        return rightChild.addNode(node);

    }

    public IntConst get(IntVar key){

        if(this.key.equals(key)) {
            return this.value;
        }
        if(key.getName().compareTo(this.key.getName())<0){
            //kleiner als diese Node
            if(leftChild==null) return null;
            if(leftChild.key.equals(key)) return leftChild.value;
            return leftChild.get(key);
        }
        if(rightChild==null) return null;
        if(rightChild.key.equals(key)) return rightChild.value;
        return rightChild.get(key);
    }

    public IntVarConstMapNode copyNode(){
        IntVarConstMapNode node = new IntVarConstMapNode(key,value);
        if(leftChild!=null) node.leftChild =leftChild.copyNode();
        if(rightChild!=null) node.rightChild =rightChild.copyNode();
        return node;
    }

    public IntVarDoublyLinkedList copyToList(IntVarDoublyLinkedList list){

        if(leftChild!=null) leftChild.copyToList(list);
        list.addFirst(this.key);
        if(rightChild!=null) rightChild.copyToList(list);
        return list;
    }


}
