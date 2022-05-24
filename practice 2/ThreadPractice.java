import java.util.*;
import java.io.*;

public class ThreadPractice implements Runnable {

    public static void main( String args[] ) {
        Runnable RunnableInterface = new ThreadPractice();
        Thread AThread = new Thread(RunnableInterface, "Thread 1");
        AThread.start();
    }

    public void run() {
        System.out.println("Hi");
    }

}   


// hopefully helpful tutorials
// https://www.youtube.com/watch?v=r_MbozD32eo
// 