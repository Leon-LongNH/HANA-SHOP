<%-- 
    Document   : update-page
    Created on : Jan 13, 2021, 4:51:50 PM
    Author     : LongNH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update Food</h1>
        <c:set var="food" value="${requestScope.UPDATE_FOOD}" />
        <form action="DispatchController?btAction=UpdateAction" enctype="multipart/form-data" method="POST">
            FoodID : <input type="text" name="foodID" value="${food.foodID}" readonly /> </br>
            FoodName : <input type="text" name="foodName" value="${food.foodName}" required/> </br>
            FoodImg : <img src="img/${food.foodImage}" alt="Food" width="300" height="300" /></br>
            Picture name: <input type="text" name="foodImage" value="${food.foodImage}" readonly required/></br>
            Select file to upload: <input type="file" name="file" size="60" /></br>
            FoodDescription : <input type="textarea" name="foodDescription" value="${food.foodDescription}" required/> </br>
            FoodPrice : <input type="number" name="foodPrice" value="${food.foodPrice}" min="1" max="20" step="0.05" required/> </br>
            FoodCreateDate : <input type="date" id="start" name="foodCreateDate" value="${food.foodCreateDate}" required /> </br> 
            FoodCategory : <input type="text" name="foodCategory" value="${food.foodCategory}" readonly required/> </br>
            FoodQuantity : <input type="number" name="foodQuantity" value="${food.foodQuantity}" min="1" max="200" required/> </br>
            <button class="btn btn-default" type="submit" value="UpdateAction" onclick="{return confirm('Are you sure you want to continue');}" >Update</button>
        </form>
    </body>
</html>
