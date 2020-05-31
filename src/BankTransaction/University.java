package BankTransaction;

import java.util.List;
import java.util.Random;

public class University implements Runnable{

    private String universityName;
    private List<CurrentAccount> studentAccount;
    private Thread utilityThread;

    public University(String universityName, List<CurrentAccount> Accounts, ThreadGroup utilityThread) {
        this.universityName = universityName;
        this.studentAccount = Accounts;
        this.utilityThread = new Thread(utilityThread, this, universityName);;
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
        Transaction courseFee = new Transaction(this.utilityThread.getName(), 600);
        Random numGen = new Random();
        int noOfWithdrawals = 6;
            while (noOfWithdrawals > 0) {
             for(int i=0;i<studentAccount.size();i++) {
                System.out.println("Thread[ " + this.utilityThread.getName() + " ] withdrawing " + courseFee.getAmount());
                this.studentAccount.get(i).withdrawal(courseFee);
                try {
                    int time = numGen.nextInt(4000) + 1000;
                    System.out.println("Thread[ " + this.utilityThread.getName() + " ] waiting for " + time / 1000 + " seconds");
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                noOfWithdrawals--;
            }
        }

        System.out.println("Terminating thread "+ this.utilityThread.getName());
    }
}
