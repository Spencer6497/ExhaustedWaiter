// imports

public class Customer extends Thread {
    // Declare PRIVATE variables to store the arguments passed to the constructor
    // which are the threadgroup and the 3 semaphores.

    // Constructor for Customer which takes 4 arguments – the 3 semaphores and
    // the theadgroup for the thread.

    public void  run( )  {
        //this is the method that will execute when driver “starts” thread
        // if this thread is first to enter then release the Nap semaphore.
        // acquire from Servicing Semaphore to wait for service.
        // now ready to leave so execute a release on the Door Semaphore.
    }

}
