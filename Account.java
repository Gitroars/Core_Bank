

public class Account {

    private long sn;
    private int cvv;
    private double balance = 0;
    public Account(long sn,int cvv, double balance){
        this.sn = sn;
        this.cvv = cvv;
        this.balance = balance;
    }

    public long getSn() {
        return sn;
    }

    public int getCvv() {
        return cvv;
    }

    public double getBalance() {
        return balance;
    }
    public void deposit(double amt){
        balance += amt;

    }
    public void withdraw(double amt){
        balance -=amt;

    }
}
