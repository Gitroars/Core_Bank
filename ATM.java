import java.util.Scanner;

public class ATM {
    static Scanner myObj = new Scanner(System.in);
    static Bank myBank = new Bank("BCA");
    static int searchIndex;
    public static int RunSearch(){
        System.out.print("Enter ID number: ");
        int idNum = myObj.nextInt();
        System.out.print("Enter first name: ");
        String firstName = myObj.next();
        System.out.print("Enter last name: ");
        String lastName = myObj.next();
        String fullName = firstName + " "+ lastName;
        return myBank.searchCustomer(idNum,fullName);
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
    }

    public static void OrderTwo(){
        System.out.print("Enter starting balance: ");
        double newBalance = myObj.nextDouble();
        myBank.getCustomers(0).getAccount().deposit(newBalance);
    }
    public static void OrderThree(){
        int i = RunSearch();
        double currentBalance = myBank.getCustomers(i).getAccount().getBalance();
        System.out.println("Balance: "+currentBalance);
    }
    public static void OrderFour(){
        int i = RunSearch();
        System.out.print("Enter withdraw amount: ");
        double amount = myObj.nextDouble();
        myBank.getCustomers(i).getAccount().withdraw(amount);
    }
    public static void OrderFive(){
        int i = RunSearch();
        System.out.print("Enter deposit amount: ");
        double amount = myObj.nextDouble();
        myBank.getCustomers(i).getAccount().deposit(amount);
    }

    public static void printMenu(){
        System.out.println("||| TRANSACTION MENU |||");
        System.out.println("0. Exit");
        System.out.println("1. Register");
        System.out.println("2. Create New Account");
        System.out.println("3. Check Balance");
        System.out.println("4. Withdraw");
        System.out.println("5. Deposit");
    }
    public static void main(String[] args) {




            printMenu();



    }
}
