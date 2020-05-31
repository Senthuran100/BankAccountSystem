package BankTransaction;

import java.util.ArrayList;
import java.util.List;

public class BankingSystem {

    public static void main(String[] args) {
        System.out.println("---------- Creating bank account ----------");
        CurrentAccount Sueaccount = new CurrentAccount("Sue", 909028298, 2000);
        System.out.println("Initialized bank account. Initial balance: " + Sueaccount.getBalance());
        System.out.println("-------------------------------------------------\n");

        System.out.println("---------- Creating bank account ----------");
        CurrentAccount RickAccount = new CurrentAccount("Rick", 867783898, 3000);
        System.out.println("Initialized bank account. Initial balance: " + RickAccount.getBalance());
        System.out.println("-------------------------------------------------\n");

        System.out.println("---------- Creating thread groups ----------");
        ThreadGroup studentGroup = new ThreadGroup("StudentGroup");
        ThreadGroup organisationGroup = new ThreadGroup("OrganisationGroup");
        System.out.println("Created thread groups: StudentGroup, OrganisationGroup");
        System.out.println("-------------------------------------------------\n");

        System.out.println("---------- Creating student thread ----------");
        Student Sue = new Student("Sue", Sueaccount, studentGroup);
        System.out.println("Created  student thread");
        System.out.println("-------------------------------------------------\n");

        System.out.println("---------- Creating student thread ----------");
        Student Rick = new Student("Rick", RickAccount, studentGroup);
        System.out.println("Created  student thread");
        System.out.println("-------------------------------------------------\n");

        List<CurrentAccount> Accounts=new ArrayList<CurrentAccount>();
        Accounts.add(RickAccount);
        Accounts.add(Sueaccount);

        System.out.println("---------- Creating university thread ----------");
        University uow = new University("UoW",Accounts , studentGroup);
        System.out.println("Created university thread");
        System.out.println("-------------------------------------------------\n");

        System.out.println("---------- Creating loan company thread ----------");
        LoanCompany loansSue = new LoanCompany("LoanSue", Sueaccount, organisationGroup);
        System.out.println("Created  loan company thread");
        System.out.println("-------------------------------------------------\n");

        System.out.println("---------- Creating loan company thread ----------");
        LoanCompany loansRick = new LoanCompany("LoanRick", RickAccount, organisationGroup);
        System.out.println("Created  loan company thread");
        System.out.println("-------------------------------------------------\n");


        Sue.start();
        Rick.start();
        uow.start();
        loansSue.start();
        loansRick.start();

        try {
            Sue.join();
            Rick.join();
            uow.join();
            loansSue.join();
            loansRick.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("-------------------------------------------------\n");
        System.out.println("Threads successfully terminated");

        // Print final bank statement
        System.out.println("---------- Final bank statement ----------");
        Sueaccount.printStatement();
        RickAccount.printStatement();
        System.out.println("-------------------------------------------------\n");

    }
}
