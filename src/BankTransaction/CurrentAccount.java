package BankTransaction;

public class CurrentAccount implements BankAccount {
    private String accountName;
    private int accountNo;
    private int accountBalance;
    private Statement accountStatement;

    public CurrentAccount(String accountName, int accountNo, int accountBalance) {
        this.accountName = accountName;
        this.accountNo = accountNo;
        this.accountBalance = accountBalance;
        accountStatement=new Statement(accountName,accountNo);
    }

    @Override
    public synchronized int getBalance() {
        return this.accountBalance;
    }

    @Override
    public int getAccountNumber() {
        return this.accountNo;
    }

    @Override
    public String getAccountHolder() {
        return this.accountName;
    }

    @Override
    public synchronized void deposit(Transaction t) {
        this.accountBalance += t.getAmount();
        accountStatement.addTransaction( Thread.currentThread().getName(),t.getAmount(), this.accountBalance);
        notifyAll();
    }

    @Override
    public synchronized void withdrawal(Transaction t) {
        while(this.getBalance() < t.getAmount()){
            try{
                wait();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        this.accountBalance -= t.getAmount();
        accountStatement.addTransaction( Thread.currentThread().getName(),t.getAmount(), this.accountBalance);
        notifyAll();
    }

    @Override
    public boolean isOverdrawn() {
        return false;
    }

    @Override
    public void printStatement() {
       this.accountStatement.print();
    }
}
