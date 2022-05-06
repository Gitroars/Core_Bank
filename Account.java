

public class Account {
    private char tier;
    private long sn;
    private int cvv;
    private String pin;
    private double balance = 0;
    public Account(char tier,long sn,int cvv,String pin, double balance){
        this.tier = tier;
        this.sn = sn;
        this.cvv = cvv;
        this.pin = pin;
        this.balance = balance;
    }

    public long getSn() {
        return sn;
    }

    public int getCvv() {
        return cvv;
    }
    public String getPin(){return pin;}

    public double getBalance() {
        return balance;
    }
    private void addBalance(double amt){
        balance += amt;
    }

    public void deposit(double amt){
        switch (tier){
            case 'b':
                if(amt>500000 && amt<=50000000){
                    addBalance(amt);
                }
                else if(amt<=50000){
                    System.out.println("Deposit amount must be more than Rp50.000 ");
                }
                else{
                    System.out.println("Deposit amount exceeds the limit");
                }
                break;
            case 'g':
                if(amt>0 && amt<=80000000){
                    addBalance(amt);
                }
                else{
                    System.out.println("Deposit amount exceeds the limit");
                }
                break;
            case 'p':
                if(amt>0 && amt<=100000000){
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
