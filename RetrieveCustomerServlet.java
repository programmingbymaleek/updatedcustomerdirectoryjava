package CustomerSite;
import CustomerApplicationTerminal.Address;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"", "/retrieveServlet"})
public class RetrieveCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customerList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodstore", "user", "user");
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM business_customers";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int customerID = resultSet.getInt("customerID");
                String businessName = resultSet.getString("businessName");
                String telephoneNumber = resultSet.getString("telephoneNumber");
                String addressLine1 = resultSet.getString("addressLine1");
                String addressLine2 = resultSet.getString("addressLine2");
                String addressLine3 = resultSet.getString("addressLine3");
                String postalCode = resultSet.getString("postCode");
                String country = resultSet.getString("country");
                Customer customer = new Customer(customerID, businessName, telephoneNumber, new Address(addressLine1,
                        addressLine2, addressLine3, country, postalCode));
                customerList.add(customer);
//               for(Customer customer1 :customerList ){
//                   System.out.println( customer1.address.getPostcode());
//               }
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        request.setAttribute("customer", customerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}





