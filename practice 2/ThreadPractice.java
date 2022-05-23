public class Threads extends Thread {

    int num;

    public ThreadPractice() {
        num = 0;
    }

    public static void main( String args[] ) {
        ThreadPractice ThreadTest = new ThreadPractice();
        ThreadTest.start();
    }

    public void run() {
        num++;
        System.out.println(num);
    }

}   