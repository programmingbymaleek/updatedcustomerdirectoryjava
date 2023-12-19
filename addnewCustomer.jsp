<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="CustomerSite.Customer" %>
<%@ page isELIgnored="false" %>

<head>
    <title>Add new Customer</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        form {
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 5px;
            background-color: #f9f9f9;
            width: 400px;
            margin-top: 20px; /* Added margin top */
        }
        div {
            margin-bottom: 10px;
        }
        label {
            display: inline-block;
            width: 100px;
        }
        input[type="text"] {
            width: 180px;
            padding: 5px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }
        input[type="submit"] {
            padding: 8px 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        textarea {
            width: 180px;
            height: 80px;
            padding: 5px;
            border-radius: 3px;
            border: 1px solid #ccc;
            resize: vertical;
        }
    </style>
</head>
<body>
    <form method="post" action="addnewCustomerServlet">
        <h1>Add New Customer</h1>
        <div>
            <label for="businessName">Business Name:</label>
            <input type="text" id="businessName" name="businessName">
        </div>
        <div>
            <label for="telephoneNumber">Telephone Number:</label>
            <input type="text" id="telephoneNumber" name="telephoneNumber">
        </div>
        <div>
            <label for="addressLine1">Address Line 1:</label>
            <input type="text" id="addressLine1" name="addressLine1">
        </div>
        <div>
            <label for="addressLine2">Address Line 2:</label>
            <input type="text" id="addressLine2" name="addressLine2">
        </div>
        <div>
            <label for="addressLine3">Address Line 3:</label>
            <input type="text" id="addressLine3" name="addressLine3">
        </div>
        <div>
            <label for="country">Country:</label>
            <input type="text" id="country" name="country">
        </div>
        <div>
            <label for="postcode">Postal Code:</label>
            <input type="text" id="postcode" name="postcode">
        </div>
        <!-- Other input fields for adding a new customer -->

        <!-- Submit button to add the new customer -->
        <td>
             <a href="updatecustomer" class="back">Back</a>
          </td>
        <input type="submit" value="Add Customer">
    </form>
</body>
</html>
