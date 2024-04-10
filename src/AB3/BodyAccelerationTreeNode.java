package AB3;

import AB1.Vector3;
import AB2.Body;

public class BodyAccelerationTreeNode {

    private Vector3 value;
    private Body key;
    private BodyAccelerationTreeNode leftNode;
    private BodyAccelerationTreeNode rightNode;


    public BodyAccelerationTreeNode(Body key,Vector3 value){
        this.key=key;
        this.value= value;
    }

    /**
     *
     * @param key
     * @return
     */
    public Vector3 get(Body key){
        if(this.key==key) return value;
        if(key.getMass()<=this.key.getMass() && leftNode!=null) return leftNode.get(key);
        else if(key.getMass()>this.key.getMass() && rightNode!=null) return rightNode.get(key);

        return null;
    }

    /**
     * Rekursives anlegen von nodes
     * right -> größere Elemente
     * left -> kleinere Elemente
     * @param key
     * @param value
     * @return
     */
    public Vector3 setNodes(Body key, Vector3 value){
        Vector3 oldValue = null;
        if (key.equals(this.key)) {
            oldValue = this.value;
            this.value = value;
            return oldValue;
        }
        if (leftNode != null && leftNode.key.equals(key)) {
            oldValue = leftNode.value;
            leftNode.value = value;
            return oldValue;
        }
        if (rightNode != null && rightNode.key.equals(key)) {
            oldValue = rightNode.value;
            rightNode.value = value;
            return oldValue;
        }

        if (key.getMass() > this.key.getMass()) {
            if (rightNode != null)
                return rightNode.setNodes(key, value);
            else
                rightNode = new BodyAccelerationTreeNode(key, value);
        } else {
            if (leftNode != null)
                return leftNode.setNodes(key, value);
            else
                leftNode = new BodyAccelerationTreeNode(key, value);
        }
        return oldValue;
    }

    public String printableRepresentation(){
        String s = "";
        if(leftNode!=null){
            s+=leftNode.printableRepresentation();
        }
        s="[" + key + "," + value + "],"+s;
        if(rightNode!=null){
            s=rightNode.printableRepresentation()+s;
        }
        return s;
    }


}
