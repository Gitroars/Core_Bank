import java.util.Scanner;
import java.util.Random;
public class ATM {
    static Random random = new Random();
    static Scanner myObj = new Scanner(System.in);
    static Bank myBank = new Bank("BCA");
    static int index = -1;

    static boolean inServiceMenu = true;
    static boolean inTellerMenu = false;
    static boolean inATMMenu = false;

    private static void disableService(){
        inServiceMenu = false;
    }
    private static void enableService(){
        inServiceMenu=true;
    }
    private static void enableTeller(){
        disableService();
        inTellerMenu = true;
    }
    private static void disableTeller(){
        inTellerMenu = false; enableService();

    }
    private static void enableATM(){
        disableService();
        inATMMenu = true;
    }
    private static void disableATM(){
        inATMMenu = false; enableService();

    }

    public static int RunSearch(){
        System.out.print("Enter card number: ");
        long sn = myObj.nextLong();
        System.out.print("Enter CVV: ");
        int cvv = myObj.nextInt();
        System.out.print("Enter 6-digit PIN: ");
        String pin = myObj.next();
        int index = myBank.searchCustomer(sn,cvv,pin);
        return index;
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



    public static void QuitApp(){
        System.exit(0);
    }
    public static void CreateAccount(){
        System.out.print("Enter ID number: ");
        String idNum = myObj.next();
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

        boolean pinCreated = false;
        String pinStr = "";

        while(!pinCreated){
            System.out.print("Enter 6-digit PIN: ");
            pinStr = myObj.next();
            int length=0;
            for(int i=0; i<pinStr.length();i++){
                length++;
            }
            if(length==6){
                pinCreated=true;
            }
            else{
                System.out.println("Error: PIN must be in 6 digits format");
            }
        }

        Account newAccount = new Account(tierChr,sn,cvv,pinStr,newBalance);
        myBank.getCustomers(0).setAccount(newAccount);

        System.out.println("You've successfully opened your bank account and created your debit card!");
        System.out.println("[Debit Card]");
        System.out.println("Serial Number: "+ sn);
        System.out.println("CVV: "+ cvv);
        System.out.println("Balance: "+ newBalance);



    }
    public static void DeleteAccount(){
        int i = RunSearch();
        myBank.deleteCustomer(i);

    }


    public static void CheckBalance(int index){

        if(index!=-1){
            double currentBalance = myBank.getCustomers(index).getAccount().getBalance();
            System.out.println("Balance: "+currentBalance);
        }
        else{
            System.out.println("Error: Customer not found");
        }


    }
    public static void WithdrawBalance(int index){

        if(index!=-1){
            System.out.print("Enter withdraw amount: ");
            double amount = myObj.nextDouble();
            myBank.getCustomers(index).getAccount().withdraw(amount);
        }
        else{
            System.out.println("Error: Customer not found");
        }
    }
    public static void DepositBalance(int index){

        if(index!=-1){
            System.out.print("Enter deposit amount: ");
            double amount = myObj.nextDouble();
            myBank.getCustomers(index).getAccount().deposit(amount);
        }
        else{
            System.out.println("Error: Customer not found");
        }
    }
    public static void TransferBalance(int index){

        if(index!=-1){
            System.out.print("Enter account number:");
            long accountNumber = myObj.nextLong();
            System.out.print("Enter the nominal for transfer:");
            int nominal = myObj.nextInt();
        }
        else{
            System.out.println("Error: Customer not found");
        }
    }

    public static void printMenu(){


        while(inServiceMenu){
            System.out.println("---SERVICE MENU---");
            System.out.println("0. Quit");
            System.out.println("1. Bank Teller");
            System.out.println("2. ATM");
            int choice = myObj.nextInt();
            switch (choice){
                case 0: QuitApp();break;
                case 1: enableTeller();break;
                case 2: index=-1;enableATM();break;
                default: System.out.println("Error: Invalid choice");break;
            }
        }
        while(inTellerMenu){
            System.out.println("---TELLER MENU---");
            System.out.println("0. Back");
            System.out.println("1. Create Account");
            System.out.println("2. Close Account");
            int choice = myObj.nextInt();
            switch (choice){
                case 0: disableTeller();break;
                case 1: CreateAccount();break;
                case 2: DeleteAccount();break;
                default: System.out.println("Error: Invalid choice");break;
            }
        }
        while(inATMMenu){
            if(index==-1){index=RunSearch();}
            System.out.println("---TRANSACTION MENU---");
            System.out.println("0. Back");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            int choice = myObj.nextInt();
            switch (choice){
                case 0: disableATM(); break;
                case 1: CheckBalance(index); break;
                case 2: WithdrawBalance(index);break;
                case 3: DepositBalance(index);break;
                case 4: TransferBalance(index);break;
                default: System.out.println("Error: Invalid choice");break;
            }
        }


    }
    public static void main(String[] args) {
        while(true){
            printMenu();
        }

    }
}
