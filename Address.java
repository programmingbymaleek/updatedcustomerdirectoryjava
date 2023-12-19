package CustomerApplicationTerminal;

public class Address {
    private String addressLine1, addressLine2, addressLine3, country,postcode;
    public Address(String addressLine1,String country, String postcode){
        this(addressLine1,"",country,postcode);
    }

    public Address(String addressLine1,String addressLine2,String country, String postcode){
        this(addressLine1,addressLine2,"",country,postcode);
    }

    public Address(String addressLine1,String addressLine2,String addressLine3,String country, String postcode){
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.country = country;
        this.postcode = postcode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public String getCountry() {
        return country;
    }

    public String getPostcode() {
        return postcode;
    }

}
