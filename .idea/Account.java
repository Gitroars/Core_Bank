

public class Account {
    private char tier;
    private long sn;
    private int cvv;
    private double balance = 0;
    public Account(char tier,long sn,int cvv, double balance){
        this.tier = tier;
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
    private void addBalance(double amt){
        balance += amt;
    }

    public void deposit(double amt){
        switch (tier){
            case 'b':
                if(amt<=50000000){
                    addBalance(amt);
                }
                else{
                    System.out.println("Deposit amount exceeds the limit");
                }
                break;
            case 'g':
                if(amt<=80000000){
                    addBalance(amt);
                }
                else{
                    System.out.println("Deposit amount exceeds the limit");
                }
                break;
            case 'p':
                if(amt<=100000000){
                    addBalance(amt);
                }
                else{
                    System.out.println("Deposit amount exceeds the limit");
                }
                break;
            default:
                System.out.println("Error: Invalid card tier");
        }


    }
    public void withdraw(double amt) {
        if (balance > 0 ) {
            if(amt<10000000){
                balance -= amt;
            }
            else{
                System.out.println("Withdraw amount exceeds the limit");
            }
        }
        else if(balance < amt){
            System.out.println("Insufficient balance");
        }
        else{
            System.out.println("Balance is empty");
        }
    }

}
