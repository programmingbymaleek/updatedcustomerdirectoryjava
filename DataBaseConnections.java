package CustomerApplicationTerminal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

record DataExists(String businessName,String telephoneNumber,Address address) {
}


public class DataBaseConnections {
    private String url;
    private String username;
    private String password;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Database connection parameters
        DataBaseConnections dataBaseConnections = new DataBaseConnections();
        executeTask(dataBaseConnections);

    }

    private void databaseConnection() {
        this.password = "user";
        this.url = "jdbc:mysql://localhost:3306/foodstore";
        this.username = "user";
    }

    private void listAllCustomers() throws SQLException {
        databaseConnection();
        ArrayList<Customer> customersList = new ArrayList<>();
        // Establishing a connection to the database
        Connection connection = DriverManager.getConnection(url, username, password);
        // Creating a SQL statement (example of a SELECT query)
        String sqlQuery = "SELECT * FROM business_customers";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        // Executing the query
        ResultSet resultSet = preparedStatement.executeQuery();
        // Processing the results
        while (resultSet.next()) {
            // Example: Retrieving values from the result set
            int customerID = resultSet.getInt("customerID");
            String businessName = resultSet.getString("businessName");
            String telephoneNumber = resultSet.getString("telephoneNumber");
            String addressLine1 = resultSet.getString("addressLine1");
            String addressLine2 = resultSet.getString("addressLine2");
            String addressLine3 = resultSet.getString("addressLine3");
            String postalCode = resultSet.getString("postCode");
            String country = resultSet.getString("country");
            Customer customers = new Customer(customerID, businessName, telephoneNumber, new Address(addressLine1,
                    addressLine2, addressLine3, country, postalCode));
            customersList.add(customers);
            //print out all customer in the database in an arraylist
        }
        ArrayList<String> formattedCustomersDetails = new ArrayList<>();
        for (Customer customer : customersList) {
            //    Do something with the retrieved data
            String formattedCustomerDetails =
                    "Customer={id=\"" + customer.getCustomerID() +
                            "\",Business Name=\"" + customer.getBusinessName() +
                            "\"," + "Telephone Number =\"" +
                            "" + customer.getTelephoneNumber() +
                            "\",Address Line 1 =\"" + customer.address.getAddressLine1() +
                            "\",Address Line 2 =\"" + customer.address.getAddressLine2() +
                            "\",Address Line 3 =\"" + customer.address.getAddressLine3() +
                            "\",Country =\"" + customer.address.getCountry() +
                            "\",Postal Code =\"" + customer.address.getPostcode() +
                            "\"}";
            formattedCustomersDetails.add(formattedCustomerDetails);
        }
        // Print the formatted data
        for (String formattedCustomers : formattedCustomersDetails) {
            System.out.println(formattedCustomers);
        }
    }


    private static void printActionOptions() {
        System.out.println();
        String actions = """
                [1]  List  all Customers
                [2] Search for Customer by ID
                [3] Add a new Customer
                [4] Update a Customer by customer ID
                [5] Delete a Customer by ID
                [6] Exit
                Enter a number for which action you what to perform""";
        System.out.println(actions + " ");
    }

    private void findCustomerByID(int idd) throws SQLException {
        databaseConnection();
        // Establishing a connection to the database
        Connection connection = DriverManager.getConnection(url, username, password);
        // Creating a SQL statement (example of a SELECT query)
        String sqlQuery = "SELECT * FROM business_customers WHERE customerID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, idd);
            // Executing the query
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int customerID = resultSet.getInt("customerID");
                String businessName = resultSet.getString("businessName");
                String telephoneNumber = resultSet.getString("telephoneNumber");
                String addressLine1 = resultSet.getString("addressLine1");
                String addressLine2 = resultSet.getString("addressLine2");
                String addressLine3 = resultSet.getString("addressLine3");
                String postalCode = resultSet.getString("postCode");
                String country = resultSet.getString("country");
                ArrayList<String> formattedCustomer = new ArrayList<>();

                String formatedAddress = addressLine1 + " " + addressLine2 + " " + addressLine3;

                String retrivedCustomer = "Customer={id=\"" + customerID + "\",Business Name=\"" + businessName + "\"," +
                        "Telephone Number=\"" +
                        "" + telephoneNumber + "\",Address=\"" + formatedAddress + "\"}";
                formattedCustomer.add(retrivedCustomer);
                // Print out the information of persons with the searched age
                for (String retrivedFormatedCustomer : formattedCustomer) {
                    System.out.println(retrivedFormatedCustomer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void addNewCustomer(Customer customer) throws SQLException {
        databaseConnection();
        // Establishing a connection to the database
        Connection connection = DriverManager.getConnection(url, username, password);
        String insertQuery = " INSERT INTO business_customers (businessName,telephoneNumber,addressLine1," +
                "addressLine2,addressLine3,country,postcode) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, customer.getBusinessName());
            preparedStatement.setString(2, customer.getTelephoneNumber());
            preparedStatement.setString(3, customer.address.getAddressLine1());
            preparedStatement.setString(4, customer.address.getAddressLine2());
            preparedStatement.setString(5, customer.address.getAddressLine3());
            preparedStatement.setString(6, customer.address.getCountry());
            preparedStatement.setString(7, customer.address.getPostcode());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New Customer added.");
            } else {
                System.out.println("Failed to add Customer.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }

    private String isCustomerPresent(int recordID) throws SQLException {
        databaseConnection();
        String endCustomer = "";
        // Establishing a connection to the database
        Connection connection = DriverManager.getConnection(url, username, password);
        // Creating a SQL statement (example of a SELECT query)
        String sqlQuery = "SELECT * FROM business_customers WHERE customerID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, recordID);
            // Executing the query
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String businessName = resultSet.getString("businessName");
                String telephoneNumber = resultSet.getString("telephoneNumber");
                String addressLine1 = resultSet.getString("addressLine1");
                String addressLine2 = resultSet.getString("addressLine2");
                String addressLine3 = resultSet.getString("addressLine3");
                String postalCode = resultSet.getString("postCode");
                String country = resultSet.getString("country");
                ArrayList<DataExists> customerToFind = new ArrayList<>();
                customerToFind.add(new DataExists(businessName, telephoneNumber, new Address(addressLine1,addressLine2
                        ,addressLine3,postalCode,country)));

                endCustomer = customerToFind.toString();

            }

        }

        return endCustomer;

    }


    private void updateACustomer(int recordToFind) throws SQLException {

        if (!isCustomerPresent(recordToFind).isEmpty()) {
            System.out.println("true...");
            databaseConnection();
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            // Creating a SQL statement (example of a SELECT query)
            System.out.println("""
                        Are you Sure you want to update this Customer Record in the database?\n
                        type YES/Y to confirm or NO/N to cancel.""");
            String enteredValue = scanner.nextLine().trim().toLowerCase();
            System.out.println(enteredValue);
            if (!enteredValue.equals("yes") && !enteredValue.equals("y")) {
                System.out.println("No  update was made");
                return;
            }
            // Creating a SQL statement (example of a SELECT query)
            System.out.println("Enter Business Name:");
            String businessName = scanner.nextLine();
            System.out.println("Enter Telephone Number:");
            String telephoneNumber = scanner.nextLine();
            System.out.println("Enter Address 1:");
            String addressLine1 = scanner.nextLine();
            System.out.println("Enter Address 2:");
            String addressLine2 = scanner.nextLine();
            System.out.println("Enter Address 3:");
            String addressLine3 = scanner.nextLine();
            System.out.println("Enter Postal Code");
            String postalCode = scanner.nextLine();
            System.out.println("Enter Country");
            String country = scanner.nextLine();
            String updateSqlQuery = "UPDATE business_customers SET businessName = ?, telephoneNumber = ?, addressLine1 = ?, " +
                    "addressLine2 = ?, addressLine3 = ?, postCode = ?, country = ? " +
                    "WHERE customerID = ?";
            try (PreparedStatement upDatePreparedStatement = connection.prepareStatement(updateSqlQuery)) {
                upDatePreparedStatement.setString(1, businessName);
                upDatePreparedStatement.setString(2, telephoneNumber);
                upDatePreparedStatement.setString(3, addressLine1);
                upDatePreparedStatement.setString(4, addressLine2);
                upDatePreparedStatement.setString(5, addressLine3);
                upDatePreparedStatement.setString(6, postalCode);
                upDatePreparedStatement.setString(7, country);
                upDatePreparedStatement.setInt(8, recordToFind);
                int rowsAffected = upDatePreparedStatement.executeUpdate();
                System.out.println(rowsAffected + "row(s) updated.");
            }
        } else {
            System.out.println("No record found..");
            return;
        }

    }

    private void deleteRecord(int recordToDelete) throws SQLException {
        if(isCustomerPresent(recordToDelete).isEmpty()){
            System.out.println("Invalid data record");
            return;
        }
        // Establishing a connection to the database
        databaseConnection();
        System.out.println(isCustomerPresent(recordToDelete));
        System.out.println("""
                        Are you Sure you want to Delete this product from the database?\n
                        type YES/Y to confirm or NO/N to cancel.""");
        String enteredValue = scanner.nextLine().trim().toLowerCase();
        System.out.println(enteredValue);
        if (!enteredValue.equals("yes") && !enteredValue.equals("y")) {
            System.out.println("No  update was made");
            return;
        }
        Connection connection = DriverManager.getConnection(url, username, password);
        // Creating a SQL statement (example of a SELECT query)
        String sqlQueryDelete = "DELETE FROM business_customers WHERE customerID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryDelete)) {
            preparedStatement.setInt(1, recordToDelete);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");
        }

    }




    private static void executeTask(DataBaseConnections dataBaseConnections) {
        boolean flag = true;
        while (flag) {
            printActionOptions();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> dataBaseConnections.listAllCustomers();
                    case 2 -> {
                        System.out.println("Enter the customers ID:");
                        int id = Integer.parseInt(scanner.nextLine());
                        dataBaseConnections.findCustomerByID(id);
                    }
                    case 3 -> {
                        System.out.println("Enter Business Name");
                        String businessName = scanner.nextLine();
                        System.out.println("Enter Telephone Number:");
                        String telephoneNumber = scanner.nextLine();
                        System.out.println("Enter Address 1:");
                        String addressLine1 = scanner.nextLine();
                        System.out.println("Enter Address 2:");
                        String addressLine2 = scanner.nextLine();
                        System.out.println("Enter Address 3:");
                        String addressLine3 = scanner.nextLine();
                        System.out.println("Enter Postal Code");
                        String postalCode = scanner.nextLine();
                        System.out.println("Enter Country");
                        String country = scanner.nextLine();

                        Customer customer = new Customer(businessName, telephoneNumber, new Address(addressLine1,
                                addressLine2, addressLine3, postalCode, country));
                        dataBaseConnections.addNewCustomer(customer);
                    }
                    case 4 -> {
                        System.out.println("To make update, enter the customers ID");
                        int recordToFind = Integer.parseInt(scanner.nextLine());
                        dataBaseConnections.updateACustomer(recordToFind);
                    }

                    case 5->{
                        System.out.println("Enter the record you what deleted");
                        int recordId = Integer.parseInt(scanner.nextLine());
                        dataBaseConnections.deleteRecord(recordId);
                    }

                    default -> flag = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}



