/*
Spencer Peace
CSC 460-001
Dr. Gary Newell
Due 10/12/2020
 */

import java.util.*;
import java.util.concurrent.Semaphore;

public class Driver {
    // Declare private static Semaphores for Door, Servicing, and Nap
    private static volatile Semaphore door;
    private static volatile Semaphore servicing;
    private static volatile Semaphore nap;

    public static void main(String[] args) {
        // Instantiate Door Semaphore to starting value of 15 and make it FCFS
        door = new Semaphore(15, true);

        // Instantiate Servicing Semaphore and Nap semaphores to 0 and also FCFS
        servicing = new Semaphore(0, true);
        nap = new Semaphore(0, true);

        // create and instantiate the threadGroup objects.
        ThreadGroup rushHour = new ThreadGroup("rushHour");
        ThreadGroup slowTime = new ThreadGroup("slowTime");

        // create a Random number generator for milliseconds during slow time
        Random rand = new Random();
        // Create scanner object for user input
        Scanner input = new Scanner(System.in);

        // declare and instantiate array of 100 Customer threads
        Customer[] customerArr = new Customer[100];

        // instantiate a new Waiter object passing it the Nap and Servicing semaphores.
        Waiter waiter = new Waiter(nap, servicing);

        //Walk through the first 50 cells of the array and create 50 Customer object/threads
        // passing them the first ThreadGroup rushhour the Door, Servicing and Nap
        // semaphores.
        for (int i = 0; i < 50; i++) {
            customerArr[i] = new Customer(rushHour, door, servicing, nap);
        }
        // Next, walk through remaining 50 doing the same but passing the second
        // ThreadGroup slowtime.
        for (int i = 50; i < 100; i++) {
            customerArr[i] = new Customer(slowTime, door, servicing, nap);
        }

        // prompt user to hit enter to start rush hour simulation
        System.out.print("Please press Enter to start rush hour simulation: ");
        // This method call effectively blocks for i/o, controlling the execution of the driver program
        input.nextLine();

        // Start up the Waiter object/thread.       YOU MUST START WAITER FIRST!
        waiter.start();

        // sleep for 1 second and then walk through and start up the first 50 customers
        try {
            Thread.sleep(1000);
            for (int i = 0; i < 50; i++) {
                customerArr[i].start();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // While the first ThreadGroup’s activeCount( ) is greater than 0
        while (rushHour.activeCount() > 0) {
            ; // busy wait
        }

        // prompt user to hit enter for slow time simulation
        System.out.print("Please press Enter to start slow time simulation: ");
        // This method call effectively blocks for i/o, controlling the execution of the driver program
        input.nextLine();

        // walk through second 50 customer objects and start them BUT this
        // time wait from a random 50-500 milliseconds between each start.
        for (int i = 50; i < 100; i++) {
            customerArr[i].start();
            try {
                // Sleep anywhere from 50-5000 milliseconds in between start
                int n = rand.nextInt(5000);
                n += 50;
                Thread.sleep(n);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // wait for the second ThreadGroup’s activeCount( ) to reach zero
        while (slowTime.activeCount() > 0) {
            ; // busy wait
        }
        // then interrupt the Waiter object and the program will end.
        waiter.interrupt();
    }
}
