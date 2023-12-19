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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/updatecustomer")
public class viewproductUserServerlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession(true);
        if (session != null && session.getAttribute("authenticatedUser") != null) {
            List<Customer> customerList = new ArrayList<>();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodstore", "user", "user");
                Statement statement = connection.createStatement();
                String sqlQuery = "SELECT * FROM business_customers";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
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
                    Customer customer = new Customer(customerID, businessName, telephoneNumber, new Address(addressLine1,
                            addressLine2, addressLine3, country, postalCode));
                    customerList.add(customer);
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println("run her ------>");
            System.out.println(customerList);
            request.setAttribute("customerlist", customerList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editcustomer.jsp");
            dispatcher.forward(request, response);

        }

    }
}

