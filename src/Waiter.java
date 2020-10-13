import java.util.Collection;
import java.util.concurrent.Semaphore;

public class Waiter extends Thread {
    // declare private variables to hold values passed to constructor
    private volatile Semaphore nap, servicing;
    private static int customerNum = 0;

    // Constructor which takes two Semaphore objects (Nap and Servicing)
    Waiter(Semaphore nap, Semaphore servicing) {
        this.nap = nap;
        this.servicing = servicing;
    }

    public void run() {
        do {
            try {
                // Try to take a nap
                if (nap.tryAcquire()) {
                    // Customers waiting, start servicing
                    Thread.sleep(100);
                    customerNum++;
                    System.out.println("Waiter is servicing customer " + customerNum);
                    servicing.release();
                } else {
                    // No customers waiting, ok to take nap
                    System.out.println("Waiter is SLEEPING");
                    nap.acquire();
                    System.out.println("Waiter is now AWAKE");
                    // Customers waiting, start servicing
                    Thread.sleep(100);
                    customerNum++;
                    System.out.println("Waiter is servicing customer " + customerNum);
                    servicing.release();
                }
            } catch (InterruptedException e) {
                System.out.println("\nEND OF SIMULATION");
                break;
            }
        } while(true);
    }
}
