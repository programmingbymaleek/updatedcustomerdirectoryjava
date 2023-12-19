package CustomerSite;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addnewCustomerServlet")
public class addnewCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Retrieve existing session if exists
        // Check if the user is not logged in
        if (session == null || session.getAttribute("authenticatedUser") == null) {
            response.sendRedirect("adminlogin.jsp"); // Redirect to login page if not logged in
            return; // Stop further execution
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodstore", "user", "user");

            String businessName = request.getParameter("businessName");
            String telephoneNumber = request.getParameter("telephoneNumber");
            String addressLine1 = request.getParameter("addressLine1");
            String addressLine2 = request.getParameter("addressLine2");
            String addressLine3 = request.getParameter("addressLine3");
            String postcode = request.getParameter("postcode");
            String country = request.getParameter("country");

            String sqlQuery = "INSERT INTO business_customers (businessName, telephoneNumber, addressLine1, addressLine2, addressLine3, country, postCode) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, businessName);
            preparedStatement.setString(2, telephoneNumber);
            preparedStatement.setString(3, addressLine1);
            preparedStatement.setString(4, addressLine2);
            preparedStatement.setString(5, addressLine3);
            preparedStatement.setString(6, country);
            preparedStatement.setString(7, postcode);

            int rowsInserted = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (rowsInserted > 0) {
                response.sendRedirect("updatecustomer"); // Redirect after successful addition
            } else {
                response.sendRedirect("adminlogin.jsp"); // Redirect if no addition occurred
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
