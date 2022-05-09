
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

    public int searchCustomer(long snNumber, String expirationDate,int cvvNumber, int pinNumber){
        int index = -1;  //declare the index to be negative one at start
        for(int i=0;i<customers.size();i++){ //Traverse through the linked list,
            Customer llElement = customers.get(i);
            long llcardNumber = llElement.getAccount().getSn();
            String llcardED = llElement.getAccount().getEd();
            int llcardCVV = llElement.getAccount().getCvv();
            int llcardPIN = llElement.getAccount().getPin();
            // Check whether the current element's data values matches with the one from parameter
            if(llcardNumber==snNumber && llcardED.equals(expirationDate )&& llcardCVV==cvvNumber && llcardPIN==pinNumber){
                index = i; //change the index's value to current index
                return index;
            }
        }
        return index;
    }

    public int searchTarget(long accountNumber){
        int index = -1; //declare the index to be negative one at start
        for(int i=0;i<customers.size();i++){ //Traverse through the linked list,
            Customer llElement = customers.get(i);
            long llAccountNumber = llElement.getAccountNumber();
            // Check whether current element's account number is the same with parameter input's
            if(llAccountNumber==accountNumber){
                index = i; //change the index's value to current index
                return index;
            }
        }
        return index;
    }

    public boolean searchIDAvailability(String idNumber){

            for(int i=0;i<customers.size();i++){
                Customer llElement = customers.get(i);
                String llIDNumber = llElement.getIdNumber();
                if(llIDNumber.equals(idNumber)){return false;}
            }
            return true;




    }
    public boolean searchCNAvailability(long cardNumber){

            for(int i=0;i<customers.size();i++){
                Customer llElement = customers.get(i);
                long llCardNumber = llElement.getAccount().getSn();
                if(llCardNumber==cardNumber){return false;}
            }
            return true;



    }

}
