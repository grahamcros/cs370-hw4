public class Consumer implements Runnable {
    /*
    Consumes the items using methods from BoundedBuffer()
     */
    private BoundedBuffer buffer;
    private double bufferTotal = 0;

    public Consumer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    public double getBufferTotal() {
        return bufferTotal;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 100000; j++) {
                    Double item = buffer.consume();
                    bufferTotal += item;
                }
                System.out.printf("Consumer: Consumed %d items, Cumulative value of consumed items=%.3f\n", (i*100000), bufferTotal);
            }
            System.out.println("Consumer: Finished consuming 1,000,000 items");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
