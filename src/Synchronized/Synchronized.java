package Synchronized;

public class Synchronized {
    static int i = -1;

    public static void main(String[] args) {
        MeuRunnable runnable = new MeuRunnable();

        Thread T0 = new Thread(runnable);
        Thread T1 = new Thread(runnable);
        Thread T2 = new Thread(runnable);
        Thread T3 = new Thread(runnable);
        Thread T4 = new Thread(runnable);


        T0.start();
        T1.start();
        T2.start();
        T3.start();
        T4.start();

    }

    public static class MeuRunnable implements Runnable {
        /*static Object lock1 = new Object();
        static Object lock2 = new Object();
*/
        @Override
        // public synchronized void run() {
        public void run() {
            synchronized (this) {
                i++;
                String nome = Thread.currentThread().getName();
                System.out.println(nome + ":" + i);

            }
      /*      synchronized (lock1) {
                i++;
                String nome = Thread.currentThread().getName();
                System.out.println(nome + ":" + i);
            }
            synchronized (lock2) {
                i++;
                String nome = Thread.currentThread().getName();
                System.out.println(nome + ":" + i);
            }*/


        }
    }
}