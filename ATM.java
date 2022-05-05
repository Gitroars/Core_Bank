import java.util.Scanner;
import java.util.Random;
public class ATM {
    static Random random = new Random();
    static Scanner myObj = new Scanner(System.in);
    static Bank myBank = new Bank("BCA");
    static int searchIndex;

    public static int RunSearch(){
        System.out.print("Enter card number: ");
        long sn = myObj.nextLong();
        System.out.print("Enter CVV: ");
        int cvv = myObj.nextInt();
        return myBank.searchCustomer(sn,cvv);
    }
    public static String RandomNumbers(int digits){

        String result = "";

        // CVV Number
        if (digits < 5){
            for(int i=0; i< digits;i++){
                int rng = random.nextInt(9);
                result += Integer.toString(rng);
            }
        }

        // Account Number & Card Number
        else if(digits>=10){
            for(int i=0; i< digits;i++){
                long rng = random.nextInt(9);
                result += Long.toString(rng);
            }
        }

        return result;

    }



    public static void OrderZero(){
        System.exit(0);
    }
    public static void OrderOne(){
        System.out.print("Enter ID number: ");
        int idNum = myObj.nextInt();
        System.out.print("Enter first name: ");
        String f= myObj.next();
        System.out.print("Enter last name: ");
        String l= myObj.next();

        long an = Long.parseLong(RandomNumbers(10));
        Customer myCustomer = new Customer(idNum,f,l,an);
        myBank.addCustomer(myCustomer);

        System.out.println("Dear "+f+" "+l+",please choose the debit card tier ");
        System.out.println("1. Blue | Withdraw Limit : 10M | Deposit Limit : 50M | Transfer Limit : 50M");
        System.out.println("2. Gold | Withdraw Limit : 10M | Deposit Limit : 80M | Transfer Limit : 75M");
        System.out.println("3. Platinum | Withdraw Limit : 10M | Deposit Limit : 100M | Transfer Limit : 100M");
        int tierNum = myObj.nextInt();
        char tierChr='x';
        switch (tierNum){
            case 1:
                System.out.println("Blue Tier selected");
                tierChr='b';
                break;
            case 2:
                System.out.println("Gold Tier selected");
                tierChr='g';
                break;
            case 3:
                System.out.println("Platinum Tier selected");
                tierChr='p';
                break;
            default:
                System.out.println("Invalid input");
                break;
        }


        System.out.print("Enter starting balance: ");
        double newBalance = myObj.nextDouble();


        long sn = Long.parseLong(RandomNumbers(16));
        int cvv = Integer.parseInt(RandomNumbers(3));
        Account newAccount = new Account(tierChr,sn,cvv,newBalance);
        myBank.getCustomers(0).setAccount(newAccount);

        System.out.println("You've successfully opened your bank account and created your debit card!");
        System.out.println("---Debit Card Details---");
        System.out.println("Serial Number: "+ sn);
        System.out.println("CVV: "+ cvv);
        System.out.println("Balance: "+ newBalance);

        printMenu();

    }
    public static void OrderTwo(){
        int i = RunSearch();
    }


    public static void OrderThree(){
        int i = RunSearch();
        double currentBalance = myBank.getCustomers(i).getAccount().getBalance();
        System.out.println("Balance: "+currentBalance);
        printMenu();
    }
    public static void OrderFour(){
        int i = RunSearch();
        System.out.print("Enter withdraw amount: ");
        double amount = myObj.nextDouble();
        myBank.getCustomers(i).getAccount().withdraw(amount);
        printMenu();
    }
    public static void OrderFive(){
        int i = RunSearch();
        System.out.print("Enter deposit amount: ");
        double amount = myObj.nextDouble();
        myBank.getCustomers(i).getAccount().deposit(amount);
        printMenu();
    }
    public static void OrderSix(){
        int i = RunSearch();
        System.out.print("Enter account number:");
        long accountNumber = myObj.nextLong();
        System.out.print("Enter the nominal for transfer:");
        int nominal = myObj.nextInt();


    }

    public static void printMenu(){
        System.out.println("||| TRANSACTION MENU |||");
        System.out.println("0. Exit");
        System.out.println("1. Register & Create Account");
        System.out.println("2. Delete Account");
        System.out.println("3. Check Balance");
        System.out.println("4. Withdraw");
        System.out.println("5. Deposit");
        System.out.println("6. Transfer");
        int choice = myObj.nextInt();
        switch (choice){
            case 0:OrderZero(); break;
            case 1:OrderOne(); break;

            case 3:OrderThree(); break;
            case 4:OrderFour(); break;
            case 5:OrderFive(); break;
            case 6:OrderSix();break;

        }
    }
    public static void main(String[] args) {
            printMenu();
    }
}
