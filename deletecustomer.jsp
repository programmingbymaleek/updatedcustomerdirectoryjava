<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="CustomerSite.Customer" %>
<%@ page isELIgnored="false" %>

<head>
    <title>Delete Customer</title>
    <style>
        body {
           display: flex;
           flex-direction:column;
           justify-content: flex-start;
           align-items: center;
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

        .logout-container {
            position:relative;
            top:2rem;
            display: block;
            justify-content: flex-end;

        }
        .logout-form{
        border:none;
        }
    </style>
</head>
<body>
    <form method="post" action="deleteCustomerServlet">
        <h1>Delete Customer</h1>
        <p>Customer ID: ${customerdata.customerID}</p>
        <c:choose>
            <c:when test="${not empty customerdata}">
                <!-- If Customer is not empty -->
                <input type="hidden" name="customerId" value="${customerdata.customerID}">
                <div>
                    <label for="businessName">Business Name:</label>
                    <input type="text" id="businessName" name="businessName" value="${customerdata.businessName}">
                </div>
                <div>
                    <label for="telephoneNumber">Telephone Number:</label>
                    <input type="text" id="telephoneNumber" name="telephoneNumber" value="${customerdata.telephoneNumber}">
                </div>
                <div>
                    <label for="addressLine1">Address Line 1:</label>
                    <input type="text" id="addressLine1" name="addressLine1" value="${customerdata.address.addressLine1}">
                </div>

                <div>
                     <label for="addressLine2">Address Line 2:</label>
                     <input type="text" id="addressLine1" name="addressLine2" value="${customerdata.address.addressLine2}">
                </div>
                 <div>
                   <label for="addressLine2">Address Line 3:</label>
                   <input type="text" id="addressLine3" name="addressLine3" value="${customerdata.address.addressLine3}">
                  </div>
                   <div>
                      <label for="country">Country</label>
                         <input type="text" id="country" name="country" value="${customerdata.address.country}">
                   </div>
                    <div>
                      <label for="postal Code">Postal Code</label>
                         <input type="text" id="postcode" name="postcode" value="${customerdata.address.postcode}">
                          </div>
                <!-- Other input fields for SKU, category, price, etc. -->

                <!-- Submit button to update product -->
                <h4>How you sure you want to delete this Customer?</h4>
                 <td>
                   <a href="updatecustomer" class="back">Back</a>
                 </td>
                <input type="submit" value="Delete customer">
            </c:when>
        </c:choose>
        </form>
    <div class="logout-container">
              <form method="post" action="logoutServlet" class="logout-form">
                  <button type="submit" style="margin-top:-4rem; font-size:18px">Logout</button>
              </form>
            </div>
</body>
</html>
