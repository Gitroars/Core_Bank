import java.util.Scanner;
import java.util.Random;
import java.time.LocalDate;
import java.time.Month;
public class ATM {
    // Initialize library data
    static Random random = new Random();
    static Scanner myObj = new Scanner(System.in);
    // Initialize class data
    static Bank myBank = new Bank("BCA");
    // Initialize index value to locate an element in the list
    static int index = -1;
    // Initialize boolean values to loop through and traverse between menus
    static boolean inServiceMenu = true;
    static boolean inTellerMenu = false;
    static boolean inATMMenu = false;
    // Initialize functions to loop through and traverse between menus
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

    private static int RunSearch(){

        // Users input information of debit card for parameter input on search function
        System.out.print("Enter card number: ");
        long sn = myObj.nextLong();
        System.out.print("Enter expiration date(mm/yy): ");
        String ed = myObj.next();
        System.out.print("Enter CVV: ");
        int cvv = myObj.nextInt();
        System.out.print("Enter 6-digit PIN: ");
        String pinStr = myObj.next();
        int pinInt = Integer.parseInt(pinStr);
        int index = myBank.searchCustomer(sn,ed,cvv,pinInt); //set the value of index to the return value of element's index that meets the criteria
        return index;
    }
    private static String RandomNumbers(int digits){

        String result = ""; //Declare end-result to be empty
        int rng = 0; //Declare random number container to be zero

        // CVV Number
        if (digits < 5){
            for(int i=0; i< digits;i++){ //For each digit,
                rng=random.nextInt(9); //Set the value of random number to be from 0-9
                result += Integer.toString(rng); //Add the random number to the string
            }
        }

        // Account Number & Card Number
        else if(digits>=10){
            for(int i=0; i< digits;i++){ //For each digit,
                if(i==0){rng = random.nextInt(8)+1;}  //The first digit will at least be 1
                else{rng=random.nextInt(9);} //The second digit and so on will be from 0-9
                result += Integer.toString(rng); //Add the random number to the string
            }
        }

        return result;
    }
    private static String GetExpirationMonth(String month){
        // Change the string's value from word to number form
        if(month=="JANUARY"){month="01";}
        else if(month=="FEBRUARY"){month="02";}
        else if(month=="MARCH"){month="03";}
        else if(month=="APRIL"){month="04";}
        else if(month=="MAY"){month="05";}
        else if(month=="JUNE"){month="06";}
        else if(month=="JULY"){month="07";}
        else if(month=="AUGUST"){month="08";}
        else if(month=="SEPTEMBER"){month="09";}
        else if(month=="OCTOBER"){month="10";}
        else if(month=="NOVEMBER"){month="11";}
        else if(month=="DECEMBER"){month="12";}
        return month;

    }
    private static String GetExpirationYear(int year){
        String newYear = Integer.toString(year); //Change from int to string format
        newYear = newYear.substring(2); //Remove first two digits
        return newYear;
    }
    private static String GetExpirationDate(){
        String expirationDate= "";
        LocalDate currentdate = LocalDate.now();
        //Getting the current month
        String currentMonth = String.valueOf(currentdate.getMonth());
        //getting the current year
        int currentYear = currentdate.getYear();
        // Convert to new string format
        String newMonth = GetExpirationMonth(currentMonth);
        String newYear = GetExpirationYear(currentYear);
        // Convert to string format and return the formatted value
        expirationDate = newMonth+"/"+newYear;
        return expirationDate;
    }



    private static void QuitApp(){
        System.exit(0); //Terminate the program
    }
    private static void CreateAccount(){
        index = myBank.getNumberOfCustomers(); //The new index value on the list should be the current number of customers

        // Ask user input for unique id
        boolean idAvailability = false;
        while(idAvailability){ // User must input an ID number that has not been used
            System.out.print("Enter ID number: ");
            String idNum = myObj.next();
            idAvailability = myBank.searchIDAvailability(idNum);
        }
        System.out.print("Enter first name: ");
        String f= myObj.next();
        System.out.print("Enter last name: ");
        String l= myObj.next();
        int anExist = -1;
        long an = 0;
        while(anExist==-1){
            an = Long.parseLong(RandomNumbers(10));
            anExist = myBank.searchTarget(an);
        }

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


        //Generate a unique 16-digit card number
        boolean cardNumberAvailable = false;
        long sn = 0;
        while(cardNumberAvailable){
            sn = Long.parseLong(RandomNumbers(16));
            cardNumberAvailable = myBank.searchCNAvailability(sn);
        }
        // Generate a 3-digit CVV
        int cvv = Integer.parseInt(RandomNumbers(3));


        // User will be required to create a 6-digit PIN. A PIN in valid format is required to break out of the loop.
        boolean pinCreated = false;
        String pinStr = "";
        int pinInt = 0;

        while(!pinCreated){
            System.out.print("Enter 6-digit PIN: ");
            pinStr = myObj.next();

            int length=0;
            for(int i=0; i<pinStr.length();i++){
                length++;
            }
            if(length==6){
                pinCreated=true;
                pinInt = Integer.parseInt(pinStr);
            }
            else{
                System.out.println("Error: PIN must be in 6 digits format");
            }
        }
        // Generate expiration date for the card according to current date
        String expirationDate = GetExpirationDate();
        // Set the new bank account according to generated and inputted data
        Account newAccount = new Account(tierChr,sn,expirationDate,cvv,pinInt,newBalance);
        myBank.getCustomers(index).setAccount(newAccount);

        // Print information of the bank account and debit card
        System.out.println("You've successfully opened your bank account and created your debit card!");
        System.out.println("Account Number: "+an);
        System.out.println("Card Number: "+ sn);
        System.out.println("Expiration Date: "+expirationDate);
        System.out.println("CVV: "+ cvv);
        System.out.println("Balance: "+ newBalance);

    }
    private static void DeleteAccount(){
        int i = RunSearch();
        myBank.deleteCustomer(i);

    }


    private static void CheckBalance(int index){

        if(index!=-1){
            double currentBalance = myBank.getCustomers(index).getAccount().getBalance();
            System.out.println("Balance: "+currentBalance);
        }
        else{
            System.out.println("Error: Customer not found");
        }


    }
    private static void WithdrawBalance(int index){

        if(index!=-1){
            System.out.print("Enter withdraw amount: ");
            double amount = myObj.nextDouble();
            myBank.getCustomers(index).getAccount().withdraw(amount);
        }
        else{
            System.out.println("Error: Customer not found");
        }
    }
    private static void DepositBalance(int index){

        if(index!=-1){
            System.out.print("Enter deposit amount: ");
            double amount = myObj.nextDouble();
            myBank.getCustomers(index).getAccount().deposit(amount);
        }
        else{
            System.out.println("Error: Customer not found");
        }
    }
    private static void TransferBalance(int index){

        if(index!=-1){
            System.out.print("Enter account number:");
            long accountNumber = myObj.nextLong();
            int targetIndex = myBank.searchTarget(accountNumber);
            if(targetIndex!=-1 && (index!=targetIndex)){ //Upon verifying the account number exist and is not of the user's,
                System.out.print("Enter the nominal for transfer:"); //Ask the user to input the nominal amount
                double nominal = myObj.nextDouble(); //Declare nominal's value to be of user input
                myBank.getCustomers(index).getAccount().transferSender(nominal); //Subtract sender's balance by nominal
                myBank.getCustomers(targetIndex).getAccount().transferRecipient(nominal); //Add recipient's balance by nominal
            }
            else{
                System.out.println("Error: Invalid account number");
            }

        }
        else{
            System.out.println("Error: Customer not found");
        }
    }

    private static void printMenu(){
        // User will stay on said menu until they decide to go back or quit the menu. Users are given several options on each menu. The user inputs will then call a function.
        //Main Menu
        while(inServiceMenu){
            System.out.println("---SERVICE MENU---");
            System.out.println("0. Quit");
            System.out.println("1. Bank Teller");
            System.out.println("2. ATM");
            int choice = myObj.nextInt();
            switch (choice){
                case 0: QuitApp();break;
                case 1: enableTeller();break;
                case 2: if(myBank.getNumberOfCustomers()>0){index=-1;enableATM();break;}
                default: System.out.println("Error: Invalid choice");break;
            }
        }
        // Teller Menu
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
        // ATM Menu
        while(inATMMenu){
            if(index==-1){
                index=RunSearch();//If a debit card is not inserted yet, then ask for one
            }
            if(index!=-1){ // Upon verification, display the menu options for the user to choose from
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
            else if(index==-1){ //If no match identity is found,
                System.out.println("Error: Card Verification Failed"); //Informs user of invalid input
                disableATM();  // Return to Service Menu Display
            }


        }


    }
    public static void main(String[] args) {
        // Keep the program alive
        while(true){
            printMenu();
        }

    }
}
