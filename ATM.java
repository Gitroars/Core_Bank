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
    public static String RandomSNCVV(int digits){

        String result = "";
        if (digits == 3){
            for(int i=0; i< digits;i++){
                int rng = random.nextInt(9);
                result += Integer.toString(rng);

            }
        }
        else if(digits==16){
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
        Customer myCustomer = new Customer(idNum,f,l);
        myBank.addCustomer(myCustomer);

        System.out.println("Dear "+myBank.getCustomers(0).getFullName()+",Please choose the debit card tier ");
        System.out.println("1. Blue | Withdraw Limit : 10M | Deposit Limit : 25M");
        System.out.println("2. Gold | Withdraw Limit : 10M | Deposit Limit : 40M");
        System.out.println("3. Platinum | Withdraw Limit : 10M | Deposit Limit : 50M");
        int tier = myObj.nextInt();
        switch (tier){
            case 1:
                System.out.println("Blue Tier selected");
                break;
            case 2:
                System.out.println("Gold Tier selected");
                break;
            case 3:
                System.out.println("Platinum Tier selected");
                break;
            default:
                break;
        }


        System.out.print("Enter starting balance: ");
        double newBalance = myObj.nextDouble();

        long sn = Long.parseLong(RandomSNCVV(16));
        int cvv = Integer.parseInt(RandomSNCVV(3));
        Account newAccount = new Account(sn,cvv,newBalance);
        myBank.getCustomers(0).setAccount(newAccount);

        System.out.println("You've successfully opened your bank account and created your debit card!");
        System.out.println(" Debit Card Details ");
        System.out.println("Serial Number: "+ sn);
        System.out.println("CVV: "+ cvv);
        System.out.println("Balance: "+ newBalance);

        printMenu();

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

    }

    public static void printMenu(){
        System.out.println("||| TRANSACTION MENU |||");
        System.out.println("0. Exit");
        System.out.println("1. Register & Open Account");
//        System.out.println("2. Create New Account");
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
        }
    }
    public static void main(String[] args) {
            printMenu();
    }
}
