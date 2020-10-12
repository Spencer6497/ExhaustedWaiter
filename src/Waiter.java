// imports

import java.util.Collection;
import java.util.concurrent.Semaphore;

public class Waiter extends Thread {
    // declare private variables to hold values passed to constructor
    private volatile Semaphore nap, servicing;

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
                    System.out.println("Waiter is servicing customer X");
                    // Simulate service
                    Thread.sleep(100);
                    servicing.release();
                } else {
                    // No customers waiting, ok to take nap
                    System.out.println("Waiter is SLEEPING");
                    nap.acquire();
                    System.out.println("Waiter is now AWAKE");
                    // Customers waiting, start servicing
                    System.out.println("Waiter is servicing customer X");
                    // Simulate service
                    Thread.sleep(100);
                    servicing.release();
                }
            } catch (InterruptedException e) {
                System.out.println("\nEND OF SIMULATION");
                break;
            }
        } while(true);
    }
}
