package Threads;

public class Meu_Runnable implements Runnable {
    @Override
    public void run() {
      //  System.out.println("Ola mundo!");
        String nome = Thread.currentThread().getName();
        System.out.println(nome );
    }
}
