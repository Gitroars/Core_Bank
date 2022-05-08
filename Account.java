public class Account {
    private char tier;
    private long sn;
    private String ed;
    private int cvv;
    private int pin;
    private double balance = 0;

    public Account(char tier,long sn,String ed,int cvv,int pin, double balance){
        this.tier = tier;
        this.sn = sn;
        this.ed = ed;
        this.cvv = cvv;
        this.pin = pin;
        this.balance = balance;
    };

    public long getSn() {
        return sn;
    }
    public String getEd() {return ed;}
    public int getCvv() {
        return cvv;
    }
    public int getPin(){return pin;}

    public double getBalance() {
        return balance;
    }
    private void addBalance(double amt){
        balance += amt;
    }
    private void subtractBalance(double amt){balance -=amt;}

    public void deposit(double amt){
        switch (tier){
            case 'b':
                if(amt>0 &&amt<=50000000 && (amt%50000==0)){ //The deposit amount must be a positive multiplication of 50 thousand and at most 500 thousand
                    addBalance(amt); // add balance by amount
                }
                else{
                    System.out.println("Error: Invalid deposit amount");
                }
                break;
            case 'g':
                if(amt>0 && amt<=80000000 && (amt%50000==0)){ //The deposit amount must be a positive multiplication of 50 thousand and at most 800 thousand
                    addBalance(amt); // add balance by amount
                }
                else{
                    System.out.println("Error: Invalid deposit amount");
                }
                break;
            case 'p':
                if(amt>0 && amt<=100000000 && (amt%50000==0)){ //The deposit amount must be a positive multiplication of 50 thousand and at most 1 million
                    addBalance(amt); // add balance by amount
                }
                else{
                    System.out.println("Error: Invalid deposit amount");
                }
                break;
            default:
                System.out.println("Error: Invalid card tier");
        }


    }
    public void withdraw(double amt) {
        if (balance>=amt ) { //There must be sufficient balance for said withdrawal amount
            if(amt>0 && amt<10000000 && (amt%50000==0)){ //The withdrawal amount must be a multiplication of positive 50 thousand
                subtractBalance(amt); //reduce the balance by amount
            }
            else{
                System.out.println("Error: Invalid withdraw amount");
            }
        }
        else if(balance < amt){
            System.out.println("Error: Insufficient balance");
        }
        else{
            System.out.println("Error: Invalid balance");
        }
    }
    public void transferSender(double amt){
        if(balance>0 && balance>=amt && amt>=10000){ //If the balance is sufficient for a transfer to be done,
            switch(tier){
                case 'b': //For blue tier card,
                    if(amt<=50000000){subtractBalance(amt);} //Ensure the transfer amount must be less or equal to 50 million for a successful transfer
                    else{ System.out.println("Transfer amount exceeds transfer limit");}
                case 'g': //For gold tier card,
                    if(amt<=75000000){subtractBalance(amt);} //Ensure the transfer amount must be less or equal to 75 million for a successful transfer
                    else{System.out.println("Transfer amount exceeds transfer limit");}
                case 'p': //For platinum tier card,
                    if(amt<=100000000){subtractBalance(amt);} //Ensure the transfer amount must be less or equal to 100 million for a successful transfer
                    else{System.out.println("Transfer amount exceeds transfer limit");
            }

        }
        }
        else{
            System.out.println("Error: Invalid transfer");
        }
    }
    public void transferRecipient(double amt){
        addBalance(amt);
    }






}
