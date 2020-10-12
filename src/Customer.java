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

        // Attempt to enter the restaurant
        try {
            System.out.println("New customer attempting to enter restaurant");
            door.acquire(); // Wait on open seat
            // if this thread is first at the restaurant, then release the Nap semaphore.
            nap.release();
            System.out.println("Customer " + this.getName().substring(7) + " has entered the restaurant and is seated");
            System.out.println("Customer " + this.getName().substring(7) + " is waiting for the server");
            servicing.acquire(); // Wait for service
            System.out.println("Customer " + this.getName().substring(7) + " has been served");
            // now ready to leave so execute a release on the Door Semaphore.
            System.out.println("Customer " + this.getName().substring(7) + " is leaving");
            door.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
