package CustomerApplicationTerminal;

public class Customer {
    private int customerID;
    private String businessName, telephoneNumber;
    Address address;

    public Customer(int customerID, String businessName,String telephoneNumber,Address address) {
        this.customerID = customerID;
        this.businessName = businessName;
        this.telephoneNumber = telephoneNumber;
        this.address =  address;
    }

    public Customer(String businessName,String telephoneNumber,Address address) {
        this.businessName = businessName;
        this.telephoneNumber = telephoneNumber;
        this.address =  address;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public Address getAddress() {
        return address;
    }
}

