public class Customer {
    private int idNumber;
    private String firstName,lastName;
    Account account;
    public Customer(int i,String f,String l){
        idNumber = i;
        firstName = f;
        lastName = l;
    };

    public int getIdNumber() {return idNumber;}
        public String getFullName(){
            return getFirstName() + " " + getLastName();
        }
        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
