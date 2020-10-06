// imports

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
            if (nap.tryAcquire()) {

            } else {
                try {
                    nap.acquire();
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
