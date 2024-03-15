import java.util.Queue;
import java.util.LinkedList;

public class BoundedBuffer {
    /*
    The BoundedBuffer class holds the produce() and consume() methods responsible for adding/removing items from
    the bounded buffer.
     */
    private static final int BUFFER_SIZE = 1000;

    // Queues use a FIFO ordering. Not bounded but size can be compared to determine if full.
    private Queue<Double> buffer = new LinkedList<>();

    public synchronized void produce(Double item) throws InterruptedException {
        // Adds items to the buffer if it is not full
        while (buffer.size() == BUFFER_SIZE) {
            wait();
        }
        buffer.add(item);
        notify();
    }

    public synchronized Double consume() throws InterruptedException {
        // Removes the items from the buffer if it is not empty
        while (buffer.isEmpty()) {
            wait();
        }
        Double ret = buffer.poll();
        notify();
        return ret;
    }
}
