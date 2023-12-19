<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="CustomerSite.Customer" %>
<html>
<head>
    <title>Edit Customer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
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
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
            color: #333;
            font-weight: 600;
        }
        tr:hover {
            background-color: #f9f9f9;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
        .edit-link {
            margin-right: 10px;
        }

         .adduser-link {
                    text-align: center;
                    margin-top: 20px;
                }
                .adduser-link a {
                    text-decoration: none;
                    color: #007bff;
                    font-weight: bold;
                }
         .logout-container{
           display:flex;
           justify-content:center;
           align-items:center;
              }
                             button{width:8rem; height:3rem; font-size:1.2rem;margin-top:1rem;}
    </style>
</head>
<body>
    <h1>Edit List</h1>
    <table border="1">
        <tr>
        <th>Customer ID</th>
        <th>Business Name</th>
        <th>Telephone Number</th>
        <th>Address</th>
        <th>Edit</th>
        <th>Delete</th>
            <!-- Add other columns as needed -->
        </tr>
        <%
            List<Customer> customerList = (List<Customer>) request.getAttribute("customerlist");
            if (customerList != null) {
                 for (Customer customer : customerList) {
        %>
                    <tr>
                        <td><%= customer.getCustomerID() %></td>
                         <td><%= customer.getBusinessName() %></td>
                         <td><%= customer.getTelephoneNumber() %></td>
                         <td><%= customer.getAddress().getAddressLine1()+" "+customer.getAddress().getAddressLine2()
                          +" "+customer.getAddress().getAddressLine3()+" "+customer.getAddress().getPostcode()
                         %></td>
                        <td>
                            <a href="editCustomerServlet?id=<%= customer.getCustomerID() %>" class="edit-link">Edit</a>
                        </td>
                        <td>
                            <a href="deleteCustomerServlet?id=<%= customer.getCustomerID() %>">Delete</a>
                        </td>
                    </tr>
        <%
                }
            }
        %>
    </table>
    <div class="adduser-link"><a href="addnewCustomer.jsp">Add a new Customer</a></div>
   <div class="logout-container">
    <form method="post" action="logoutServlet" class="logout-form">
          <button type="submit">Logout</button>
       </form>
     </div>
   </div>
</body>
</html>
