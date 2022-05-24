import java.util.*;
import java.io.*;

public class ThreadPractice implements Runnable {

    public static void main( String args[] ) {
        ThreadPractice runnable = new ThreadPractice();
        Thread thread1 = new Thread(runnable, "Thread 1");
        thread1.start();
    }

    public void run() {
        for ( int i = 0; i < 10; i++ ) {
            System.out.println(Thread.currentThread().getName() + " is on its " + i + " iteration.");
        }
    }

}   


// hopefully helpful tutorials
// https://www.youtube.com/watch?v=r_MbozD32eo
// 