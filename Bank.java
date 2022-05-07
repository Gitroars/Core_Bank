import java.util.LinkedList;

public class Bank {
    private LinkedList<Customer> customers = new LinkedList<Customer>();
    private int numberOfCustomers;
    private String bankName;
    public Bank(String bName){
        bankName = bName;
    }

    public void addCustomer(Customer c){
        customers.add(c);
        System.out.println(customers);
    }
    public void deleteCustomer(int index){
        customers.remove(index);
    }

    public int getNumberOfCustomers() {
        return customers.size();
    }

    public Customer getCustomers(int index) {
        return customers.get(index);
    }
    public void printCustomers(){
        customers.forEach((element)->System.out.print(element + " "));

    }
    public int searchCustomer(long snNumber, String expirationDate,int cvvNumber, int pinNumber){
        int index = -1;
        for(int i=0;i<customers.size();i++){
            Customer llElement = customers.get(i);
            long llcardNumber = llElement.getAccount().getSn();
            String llcardED = llElement.getAccount().getEd();
            int llcardCVV = llElement.getAccount().getCvv();
            int llcardPIN = llElement.getAccount().getPin();

            if(llcardNumber==snNumber && llcardED==expirationDate && llcardCVV==cvvNumber && llcardPIN==pinNumber){
                index = i;
                return index;
            }
        }
        return index;

    }
    public int searchTarget(long accountNumber){
        int index = -1;
        for(int i=0;i<customers.size();i++){
            Customer llElement = customers.get(i);
            long llAccountNumber = llElement.getAccountNumber();
            if(llAccountNumber==accountNumber){
                index = i;
                return index;
            }
        }
        return index;
    }
}
