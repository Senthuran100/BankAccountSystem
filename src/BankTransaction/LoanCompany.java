package BankTransaction;

import java.util.Random;

public class LoanCompany implements Runnable{

    private String companyName;
    private CurrentAccount clientAccount;
    private Thread utilityThread;

    public LoanCompany(String companyName, CurrentAccount clientAccount, ThreadGroup utilityThread) {
        this.companyName = companyName;
        this.clientAccount = clientAccount;
        this.utilityThread = new Thread(utilityThread,this,companyName);
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
        Transaction loanDeposit = new Transaction(this.utilityThread.getName(), 1000);
        Random numGen = new Random();
        int noOfDeposits = 3;

        while(noOfDeposits > 0){
            System.out.println("Thread[ " + this.utilityThread.getName() + " ] depositing " + loanDeposit.getAmount());
            this.clientAccount.deposit(loanDeposit);

            try{
                int time = numGen.nextInt(4000)+1000;
                System.out.println("Thread[ " + this.utilityThread.getName() + " ] waiting for " + time/1000 + " seconds");
                Thread.sleep(time);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            noOfDeposits--;

        }
        System.out.println("Terminating thread "+ this.utilityThread.getName());
    }
}
