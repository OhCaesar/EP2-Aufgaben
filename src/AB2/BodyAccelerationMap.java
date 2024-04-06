package AB2;
import AB1.Vector3;

import java.util.Arrays;

/**
 * A map that associates a body with an acceleration vector. The number of
 * key-value pairs is not limited.
 */
public class BodyAccelerationMap {

    private Body[] keyArray;
    private Vector3[] valueArray;

    /**
     * Initializes this map with an initial capacity.
     * @param initialCapacity specifies the size of the internal array. initialCapacity > 0.
     */
    public BodyAccelerationMap(int initialCapacity) {
        if(initialCapacity<0) throw new IllegalArgumentException("InitialCapacity cannot be lower than 1");
        keyArray = new Body[initialCapacity];
        valueArray = new Vector3[initialCapacity];
    }

    /**
     * Adds a new key-value association to this map. If the key already exists in this map,
     * the value is replaced and the old value is returned. Otherwise, 'null' is returned.
     * @param key a body != null.
     * @param acceleration the acceleration vector to be associated with the key.
     * @return the old value if the key already existed in this map, or 'null' otherwise.
     */
    public Vector3 put(Body key, Vector3 acceleration) {
        //absteigend sortiert
        Vector3 oldValue = get(key);
        if(oldValue==null) {

            int size = concatSize();

            int left = 0;
            int right = size - 1;

            while (left <= right) {
                int middle = left + ((right - left) / 2);
                if (keyArray[middle].getMass() < key.getMass()) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }

            Body[] restKeyArray = Arrays.copyOfRange(keyArray,right+1,size);
            Vector3[] restValueArray = Arrays.copyOfRange(valueArray,right+1,size);

            //insert Values :
            int insertionIndex = right+1;
            keyArray[insertionIndex] = key;
            valueArray[insertionIndex] = acceleration;

            if(size == 0) return null;

            //Rest Einfügen
            int restCounter = 1;
            //Verlängerung des Arrays falls notwendig
            if(insertionIndex+restKeyArray.length>keyArray.length) {
                keyArray = Arrays.copyOf(keyArray, insertionIndex + restKeyArray.length);
                valueArray = Arrays.copyOf(valueArray, insertionIndex + restKeyArray.length);
            }
            while (insertionIndex+restCounter<keyArray.length && restCounter-1 < restKeyArray.length){
                keyArray[insertionIndex+restCounter] = restKeyArray[restCounter-1];
                valueArray[insertionIndex+restCounter] = restValueArray[restCounter-1];
                restCounter ++;
            }


        }else{
            //replacing Acceleration value of existing object

            int size = concatSize();

            //Edge Cases
            if(size == 0) return null;
            if(key.getMass()>keyArray[0].getMass() || key.getMass()<keyArray[size-1].getMass()) return null;

            int left = 0;
            int right = size-1;

            while (left <= right) {
                int middle = left + ((right - left) / 2);
                if(keyArray[middle].equals(key))    valueArray[middle] = acceleration;
                if (keyArray[middle].getMass() < key.getMass())     right = middle - 1;
                else {
                    left = middle + 1;
                }
            }


            return oldValue;
        }

        return null;
    }

    /**
     * Returns the value associated with the specified key, i.e. the acceleration vector
     * associated with the specified body. Returns 'null' if the key is not contained in this map.
     * @param key a body != null.
     * @return the value associated with the specified key (or 'null' if the key is not contained in
     * this map).
     */
    public Vector3 get(Body key) {

        int size = concatSize();
        //Edge Cases
        if(size == 0) return null;
        if(key.getMass()>keyArray[0].getMass() || key.getMass()<keyArray[size-1].getMass()) return null;

        int left = 0;
        int right = size-1;

        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if(keyArray[middle].equals(key))     return valueArray[middle];
            if (keyArray[middle].getMass() < key.getMass())     right = middle - 1;
            else {
                left = middle + 1;
            }
        }
        return null;
    }


    private int concatSize(){
        if(keyArray.length==0) return 0;
        int size = keyArray.length-1;
        while (keyArray[size]==null ) {
            size--;
            if(size == 0 ) break;
        }
        if(keyArray[size]!=null) size+=1;
        return size;
    }
}
