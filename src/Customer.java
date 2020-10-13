import java.util.concurrent.Semaphore;

public class Customer extends Thread {
    // Declare PRIVATE variables to store the arguments passed to the constructor
    // which are the threadgroup and the 3 semaphores.
    private volatile Semaphore door, servicing, nap;
    private static Semaphore countMutex = new Semaphore(1);
    private static int custCount = 0;

    // Constructor for Customer which takes 4 arguments – the 3 semaphores and
    // the theadgroup for the thread.
    Customer(ThreadGroup tg, Semaphore door, Semaphore servicing, Semaphore nap) {
        super(tg, "");
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

            // Increment customer number, save to instance variable for recall
            countMutex.acquire();
            custCount += 1;
            int customerNumber = custCount;
            countMutex.release();

            System.out.println("Customer " + customerNumber + " has entered the restaurant and is seated");
            System.out.println("Customer " + customerNumber + " is waiting for the server");
            servicing.acquire(); // Wait for service
            System.out.println("Customer " + customerNumber + " has been served");
            // now ready to leave so execute a release on the Door Semaphore.
            door.release();
            System.out.println("Customer " + customerNumber + " is leaving");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
