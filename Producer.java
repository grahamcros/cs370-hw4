import java.util.Random;

public class Producer implements Runnable {
    /* Produces the items by using the BoundedBuffer class and a random number generator
     */

    private BoundedBuffer buffer;
    private double bufferTotal = 0;

    public Producer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    public double getBufferTotal() {
        return bufferTotal;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 100000; j++) {
                    Double bufferElement = random.nextDouble() * 100.0;
                    buffer.produce(bufferElement);
                    bufferTotal += bufferElement;
                }
                System.out.printf("Producer: Generated %d items, Cumulative value of generated items=%.3f\n", (i*100000), bufferTotal);
            }
            System.out.println("Producer: Finished generating 1,000,000 items");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
