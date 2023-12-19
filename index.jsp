<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="CustomerSite.Customer" %>

<html>
<head>
    <title>Product List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            margin-top: 20px;
            color: #333;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .no-products {
            text-align: center;
            margin-top: 20px;
            color: #666;
        }
        .login-link {
            text-align: center;
            margin-top: 20px;
        }
        .login-link a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Customer List</h1>
    <table border="1">
        <tr>
            <th>Customer ID</th>
            <th>Business Name</th>
            <th>Telephone Number</th>
            <th>Address</th>
            <!-- Add other columns as needed -->
        </tr>
        <% List<Customer> customerList = (List<Customer>) request.getAttribute("customer");
        if (customerList != null && !customerList.isEmpty()) {
            for (Customer customer : customerList) { %>
                <tr>
                    <td><%= customer.getCustomerID() %></td>
                    <td><%= customer.getBusinessName() %></td>
                    <td><%= customer.getTelephoneNumber() %></td>
                    <td><%= customer.getAddress().getAddressLine1()+" "+customer.getAddress().getAddressLine2()
                    +" "+customer.getAddress().getAddressLine3()+" "+customer.getAddress().getPostcode()
                    %></td>
                    <!-- Access other product properties accordingly -->
                </tr>
        <% } } else { %>
                <tr>
                    <td colspan="5" class="no-customers">No products available</td>
                </tr>
        <% } %>
    </table>
    <div class="login-link"><a href="adminlogin.jsp">Admin Login</a></div>
</body>
</html>