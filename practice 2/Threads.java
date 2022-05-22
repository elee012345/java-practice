public class Threads extends Thread {

    int num;

    public Threads() {
        num = 0;
    }

    public static void main( String args[] ) {
        Threads ThreadTest = new Threads();
        ThreadTest.start();
    }

    public void run() {
        num++;
        System.out.println(num);
    }

}   