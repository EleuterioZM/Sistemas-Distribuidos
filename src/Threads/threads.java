package Threads;

public class threads {
    public static void main(String[] args) {

        Thread T = Thread.currentThread();
        System.out.println(T.getName());

        Thread nova = new Thread(new Meu_Runnable());
       // nova.run();//executando na mesma thread
       // nova.start(); //executando em uma nova   thread

        //runnnable como lambda
        Thread nova1 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
      //  nova1.start();

        //varias Threads.threads
        Thread nova2 = new Thread(new Meu_Runnable());
        nova.start();
        nova1.start();
        nova2.start();
    }
}
