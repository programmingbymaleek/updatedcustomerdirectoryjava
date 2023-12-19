package CustomerSite;
import CustomerApplicationTerminal.Address;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;


import static java.lang.System.out;

@WebServlet("/editCustomerServlet")
public class editCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Retrieve existing session if exists
        // Check if the user is not logged in
        if (session == null || session.getAttribute("authenticatedUser") == null) {
            response.sendRedirect("adminlogin.jsp"); // Redirect to login page if not logged in
            return; // Stop further execution
        }

        out.println("in the edit customer servlet");
        int customerID = Integer.parseInt(request.getParameter("id"));
        out.println(customerID);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodstore", "user", "user");
            String sqlQuery = "SELECT * FROM business_customers WHERE customerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, customerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            Customer customer = null;
            if (resultSet.next()) {
                int customerId = resultSet.getInt("customerID");
                String businessName = resultSet.getString("businessName");
                String telephoneNumber = resultSet.getString("telephoneNumber");
                String addressLine1 = resultSet.getString("addressLine1");
                String addressLine2 = resultSet.getString("addressLine2");
                String addressLine3 = resultSet.getString("addressLine3");
                String country = resultSet.getString("country");
                String postCode = resultSet.getString("postCode");

                // Debugging output to check values
                out.println("Retrieved values:");
                out.println("Address Line 1: " + addressLine1);
                out.println("Address Line 3: " + addressLine3);
                out.println("Country: " + country);
                out.println("Post Code: " + postCode);

                // Double-check the order of parameters when creating the Address object
                customer = new Customer(customerId, businessName, telephoneNumber,
                        new Address(addressLine1, addressLine2,
                                addressLine3, country, postCode));

                // Output customer details for verification
                out.println("Customer details:");
                out.println("Customer ID: " + customer.getCustomerID());
                out.println("Business Name: " + customer.getBusinessName());
                out.println("Telephone Number: " + customer.getTelephoneNumber());
                out.println("Postal Code: " + customer.getAddress().getPostcode());
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            if (customer != null) {
                out.println("not null");
                out.println(customer);
                request.setAttribute("customerdata", customer);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/editcustomeruser.jsp");
                dispatcher.forward(request, response);
            } else {
                // Handle when no customer is found with the given ID
                response.sendRedirect("noCustomerFound.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodstore", "user", "user");

            String businessName = request.getParameter("businessName");
            String telephoneNumber = request.getParameter("telephoneNumber");
            String addressLine1 = request.getParameter("addressLine1");
            String addressLine2 = request.getParameter("addressLine2");
            String customerID = request.getParameter("customerId");
            String addressLine3 = request.getParameter("addressLine3");
            String postcode = request.getParameter("postcode");
            String country = request.getParameter("country");

            String sqlQuery = "UPDATE business_customers SET businessName=?, telephoneNumber=?, " +
                    "addressLine1=?, addressLine2=?, addressLine3=?, country=?, postCode=? " +
                    "WHERE customerID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, businessName);
            preparedStatement.setString(2, telephoneNumber);
            preparedStatement.setString(3, addressLine1);
            preparedStatement.setString(4, addressLine2);
            preparedStatement.setString(5, addressLine3);
            preparedStatement.setString(6, postcode);
            preparedStatement.setString(7, country);
            preparedStatement.setString(8, customerID);

            int rowsUpdated = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            out.println("run");

            if (rowsUpdated > 0) {
                response.sendRedirect("updatecustomer"); // Redirect after successful update
            } else {
                response.sendRedirect("adminlogin.jsp"); // Redirect if no update occurred
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }

    }
}



