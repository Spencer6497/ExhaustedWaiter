// imports

public class Waiter extends Thread {
    // declare private variables to hold values passed to constructor

    // Constructor which takes two Semaphore objects (Nap and Servicing)

    public void run() {
        do {
            // tryacquire on the Nap Semaphore  (might go to sleep here)
            // if tryacquire returns false then execute acquire on Nap Semaphore to sleep.
            // sleep for 50-500 milliseconds to simulate preparation and service
            // release the Servicing Semaphore.
        } while(true);
    }
}
