import java.util.LinkedList;

public class Bank {
    private LinkedList<Customer> customers = new LinkedList<Customer>();
    private int numberOfCustomers;
    private String bankName;
    public Bank(String bName){
        bankName = bName;
    }

    public void addCustomer(Customer c){
        customers.addLast(c);
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public Customer getCustomers(int index) {
        return customers.get(index);
    }
    public int searchCustomer(int idNum, String customerName){
        int index = -1;
        for(int i=0;i<customers.size();i++){
            Customer llElement = customers.get(i);

            String llName = llElement.getFullName();
            if(llName==customerName ){
                index = i;
                break;
            }
        }

        if(index == -1){
            System.out.println("Invalid name target");
            return 0;
        }

        return index;


    }
}
