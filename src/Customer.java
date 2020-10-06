// imports

import java.util.concurrent.Semaphore;

public class Customer extends Thread {
    // Declare PRIVATE variables to store the arguments passed to the constructor
    // which are the threadgroup and the 3 semaphores.
    private ThreadGroup tg;
    private volatile Semaphore door, servicing, nap;

    // Constructor for Customer which takes 4 arguments – the 3 semaphores and
    // the theadgroup for the thread.
    Customer(ThreadGroup tg, Semaphore door, Semaphore servicing, Semaphore nap) {
        this.tg = tg;
        this.door = door;
        this.servicing = servicing;
        this.nap = nap;
    }

    public void  run( )  {
        //this is the method that will execute when driver “starts” thread
        try {
            System.out.println("Customer attempting to enter restaurant");
            door.acquire();
            System.out.println("Customer X has entered the restaurant and is seated");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // if this thread is first to enter then release the Nap semaphore.
        // acquire from Servicing Semaphore to wait for service.
        // now ready to leave so execute a release on the Door Semaphore.
    }

}
