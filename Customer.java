public class Customer {
    private String idNumber;
    private long accountNumber;
    private String firstName,lastName;
    Account account;
    public Customer(String i, String f, String l, long an){
        idNumber = i;
        firstName = f;
        lastName = l;
        accountNumber = an;

    };

    public String getIdNumber() {return idNumber;}

    public long getAccountNumber() {
        return accountNumber;
    }

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
