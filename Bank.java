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
        numberOfCustomers++;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public Customer getCustomers(int index) {
        return customers.get(index);
    }
    public int searchCustomer(long idNumber, int cvvNumber){
        int index = -1;
        for(int i=0;i<customers.size();i++){
            Customer llElement = customers.get(i);

            long llcardNumber = llElement.getAccount().getSn();
            int llcardCVV = llElement.getAccount().getCvv();

            if(llcardNumber==idNumber && llcardCVV==cvvNumber){
                index = i;
                break;
            }
        }

        if(index == -1){

            return 0;
        }

        return index;


    }
}
