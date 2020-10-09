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
            // tryacquire on the Nap Semaphore  (might go to sleep here)
            // Return true if customers waiting (nap = 1)
            if (nap.tryAcquire()) {
                // Perform service
                try {
                    // Simulate service with sleep
                    System.out.println("Waiter is servicing customer X");
                    Thread.sleep(100);
                    servicing.release();
                } catch (InterruptedException e) {
                    // This block should never occur
                    System.out.println("Waiter INTERRUPTED -- ERROR");
                    e.printStackTrace();
                }
            // Return false if no customers waiting (nap = 0)
            } else {
                try {
                    System.out.println("Waiter is sleeping");
                    nap.acquire(); // if waiter acquires nap semaphore, that means a customer has awoken him
                    System.out.println("Waiter is now awake");
                    // Go to top of do-while loop
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // if tryacquire returns false then execute acquire on Nap Semaphore to sleep.
            // sleep for 50-500 milliseconds to simulate preparation and service
            // release the Servicing Semaphore.
        } while(true);
    }
}
