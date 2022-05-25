import java.util.*;
import java.io.*;

public class ThreadPractice implements Runnable {

    public static void main( String args[] ) {
        // the current thread that is running is our main thread
        System.out.println("The current thread we're using is: " + Thread.currentThread().getName());

        // making a new runnable interface
        ThreadPractice runnable = new ThreadPractice();
        // creating two threads off of the ThreadPractice object runnable
        // second parameter is setting the thread names
        Thread thread1 = new Thread(runnable, "Thread 1");
        Thread thread2 = new Thread(runnable, "Thread 2");

        // displaying the thread names
        System.out.println("thread1's name is " + thread1.getName());
        System.out.println("thread2's name is " + thread2.getName());

        // changing thread1's name
        thread1.setName("Thread One");
        System.out.println("thread1's name is now " + thread1.getName());

        // I haven't started the thread yet!
        System.out.println("Thread one is alive: " + thread1.isAlive());

        // only runs the run() method and doesn't actually start the thread 
        System.out.println("haven't actually started the thread!");
        thread1.run();
        System.out.println("Thread one is alive: " + thread1.isAlive());

        // starts thread 1
        thread1.start();
        // have to surround this with a try catch block
        // makes all other threads wait for thread 1 to die or 2 seconds
        try {
            thread1.join(600);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        // starts thread2 two seconds after thread 1
        thread2.start();

        // both threads are running, and thread 2 starts 2 seconds after thread 1
        // they run at the same time! the second thread is just two seconds behind.

        // wait until only the main thread is still running 
        // waits until both thread1 and thread2 are dead
        while ( Thread.activeCount() > 1 ) {
            // wait
        }

        // once a thread dies, it's dead
        // you can't start it again
        try {
            thread1.start();
        } catch ( Exception e ) {
            System.out.println("Error: " + e);
        }

        // making new threads for more demonstrations
        Thread thread3 = new Thread(runnable, "Thread 3");
        Thread thread4 = new Thread(runnable, "make me die!"); // you will see why this is named this later
        thread3.start();
        thread4.start();
        // oh no, thread 4 has errored and died!
        // but it's all good, because all the other threads can still keep running

        // wait until this thead has died for the other theads to run
        try {
            thread3.join();
        } catch (Exception e ) {
            System.out.println(e);
        }
        

        // more threads 
        Thread thread5 = new Thread(runnable, "Thread 5");
        // daemon threads are threads that java doesn't care about very much
        // what I mean is that if all user threads (non daemon threads) end, then 
        // jvm will stop your program even if the daemon threads are still running 
        System.out.println("thread5 is a daemon thread: " + thread5.isDaemon());

        // making it a daemon
        thread5.setDaemon(true);
        System.out.println("thread5 is a daemon thread: " + thread5.isDaemon());
        thread5.start();
        // thread 5 is running but we're going to throw an exception in the main thread 
        // this will stop the whole program 
        throw new ArithmeticException("hi there");

    }

    public void run() {
        for ( int i = 0; i < 10; i++ ) {
            // can't do this.getName() because the ThreadPractice thing is actually a runnable 
            // so for example thread1 is not an object of ThreadPractice 
            // it is a Thread object that has been made using the ThreadPractice class
            System.out.println(Thread.currentThread().getName() + " is on its " + i + " iteration.");

            // have to surround this in a try catch block
            // pauses the thread for a second for the count
            try {
                Thread.currentThread().sleep(300);
            } catch (Exception e) {
                System.out.println(e);
            }

            if ( Thread.currentThread().getName().equals("make me die!") ) {
                throw new ArithmeticException("get good");
            }

        }
    }

}   


// hopefully helpful tutorials
// https://www.youtube.com/watch?v=r_MbozD32eo
// 