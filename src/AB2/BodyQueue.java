package AB2;

import java.util.Arrays;

/**
 * A queue of bodies. A collection designed for holding bodies prior to processing.
 * The bodies of the queue can be accessed in a FIFO (first-in-first-out) manner,
 * i.e., the body that was first inserted by 'add' is retrieved first by 'poll'.
 * The number of elements of the queue is not limited.
 */
public class BodyQueue {

    //TODO: declare variables.
    private Body[] bodyQueue;
    private int queueSize;

    /**
     * Initializes this queue with an initial capacity.
     * @param initialCapacity the length of the internal array in the beginning,
     *                        initialCapacity > 0.
     */
    public BodyQueue(int initialCapacity) {
        bodyQueue = new Body[initialCapacity];
        queueSize = size();
    }

    /**
     * Initializes this queue as an independent copy of the specified queue.
     * Calling methods of this queue will not affect the specified queue
     * and vice versa.
     * @param q the queue from which elements are copied to the new queue, q != null.
     */
    public BodyQueue(BodyQueue q) {
        bodyQueue = Arrays.copyOf(q.bodyQueue,q.size()*2);
        queueSize = size();
    }

    /**
     * Adds the specified body 'b' to this queue.
     * @param b the element that is added to the queue.
     */
    public void add(Body b) {
        if(queueSize == bodyQueue.length)
            bodyQueue = Arrays.copyOf(bodyQueue,queueSize*2);
        bodyQueue[queueSize] = b;
        //Updating and recalculating the queues size
        size();
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns 'null' if this queue is empty.
     * @return the head of this queue (or 'null' if this queue is empty).
     */
    public Body poll() {
        if(queueSize==0) return null;
        Body element = bodyQueue[0];
        bodyQueue = Arrays.copyOfRange(bodyQueue,1,bodyQueue.length+1);

        //Updating and recalculating the queues size
        size();
        return element;
    }

    /**
     * Returns the number of bodies in this queue.
     * @return the number of bodies in this queue.
     */
    public int size() {
        int counter = 0;
        for(Body b : bodyQueue)
            if (b != null)  counter ++;
        queueSize = counter;
        return queueSize;
    }
}
