<%-- 
    Document   : create-food-page
    Created on : Jan 16, 2021, 6:13:27 PM
    Author     : LongNH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Create Food Page
        <c:set var="category" value="${requestScope.ALL_CATEGORY}" />
        <c:set var="idauto" value="${requestScope.FOOD_ID}" />
        <c:set var="today" value="${requestScope.TODAY}" />
        <form action="DispatchController?btAction=CreateAction" enctype="multipart/form-data" method="POST">
            FoodCategory : 

            <select name="foodCategory">
                <c:forEach var="cateid" items="${idauto}" >
                    <option value="${cateid.key}${cateid.value}">${cateid.key}</option>
                </c:forEach>
            </select> </br>
            FoodName : <input type="text" name="foodName" value="" required/> </br>
            Picture name: <input type="text" name="foodImage" value="" required/></br>
            Select file to upload: <input type="file" name="file" size="60" required/></br>
            FoodDescription : <input type="text" name="foodDescription" value="" required/> </br>
            FoodPrice : <input type="number" name="foodPrice" value="" min="1" max="20" step="0.05" required/> </br>
            FoodCreateDate : <input type="date" id="start" name="foodCreateDate" value="${today}" readonly required /> </br> 
            FoodQuantity : <input type="number" name="foodQuantity" value="" min="1" max="200" required/> </br>
            <button class="btn btn-default" type="submit" onclick="{
                        return confirm('Are you sure you want to continue');
                    }" >Create</button>
        </form>
            <c:set var="error" value="${requestScope.ERRORS}" />
            <div style="color: red">${error}</div>
    </body>
</html>
