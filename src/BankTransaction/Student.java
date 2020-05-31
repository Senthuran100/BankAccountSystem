package BankTransaction;

import java.util.Random;

public class Student implements Runnable{

    private String name;
    private CurrentAccount account;
    private Thread utilityThread;

    public Student(String name, CurrentAccount account, ThreadGroup utilityThread) {
        this.name = name;
        this.account = account;
        this.utilityThread = new Thread(utilityThread,this,name);
    }

    public void start(){
        System.out.println("Starting " + utilityThread.getName() + " thread.");
        this.utilityThread.start();
    }

    public void join() throws InterruptedException{
        this.utilityThread.join();
    }

    @Override
    public void run() {
        int noOfTransactions = 6;
        Transaction[] transactions = new Transaction[6];
        Transaction[] withdrawTransactions = new Transaction[3];
        Transaction[] depositTransactions = new Transaction[3];

        transactions[0] = new Transaction(this.utilityThread.getName(), 200);
        transactions[1] = new Transaction(this.utilityThread.getName(), 100);
        transactions[2] = new Transaction(this.utilityThread.getName(), 150);
        transactions[3] = new Transaction(this.utilityThread.getName(), 150);
        transactions[4] = new Transaction(this.utilityThread.getName(), 300);
        transactions[5] = new Transaction(this.utilityThread.getName(), 250);

        Random numGen = new Random();

        while(noOfTransactions > 0){

            if( noOfTransactions % 2 == 0 ){
                System.out.println("Thread[ " + this.utilityThread.getName() + " ] depositing " + transactions[noOfTransactions-1].getAmount());
                this.account.deposit(transactions[noOfTransactions-1]);
            }
            else{
                System.out.println("Thread[ " + this.utilityThread.getName() + " ] withdrawing " + transactions[noOfTransactions-1].getAmount());
                this.account.withdrawal(transactions[noOfTransactions-1]);
            }

            try{
                int time = numGen.nextInt(4000)+1000;
                System.out.println("Thread[ " + this.utilityThread.getName() + " ] waiting for " + time/1000 + " seconds");
                Thread.sleep(time);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

            noOfTransactions--;
        }

        System.out.println("Terminating thread " + this.utilityThread.getName());
    }
}
