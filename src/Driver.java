/*
Spencer Peace
CSC 460-001
Dr. Gary Newell
Due 10/12/2020
 */

import java.util.*;
import java.util.concurrent.Semaphore;

// Declare private static Semaphores for Door, Servicing, and Nap

public class Driver {
    public static void main(String[] args) {
        // Instantiate Door Semaphore to starting value of 15 and make it FCFS
        // Instantiate Servicing Semaphore and Nap semaphores to 0 and also FCFS
        // create and instantiate the threadGroup objects.

        // create a Random number generator and Scanner (Random( )
        // Scanner(System.in))

        // Create the two threadgroups for rushhour and slowtime.
        // declare an array of 100 Customer threads


        // instantiate a new Waiter object passing it the Nap and Servicing semaphores.
        // Declare and instantiate an array of 100 Customer objects/threads.

        //Walk through the first 50 cells of the array and create 50 Customer object/threads
        // passing them the first ThreadGroup rushhour the Door, Servicing and Nap
        // semaphores.
        // Next, walk through remaining 50 doing the same but passing the second
        // ThreadGroup slowtime.

        // prompt user to hit enter to start rush hour simulation

        // Start up the Waiter object/thread.       YOU MUST START WAITER FIRST!

        // sleep for 1 second and then walk through and start up the first 50 customers
                Thread.sleep(1000);
        // While the first ThreadGroup’s activeCount( ) is greater than 0
        // busy wait

        // prompt user to hit enter for slow time simulation

        // walk through second 50 customer objects and start them BUT this
        // time wait from a random 50-500 milliseconds between each start.

        // wait for the second ThreadGroup’s activeCount( ) to reach zero
        // then interrupt the Waiter object and the program will end.
    }
}
