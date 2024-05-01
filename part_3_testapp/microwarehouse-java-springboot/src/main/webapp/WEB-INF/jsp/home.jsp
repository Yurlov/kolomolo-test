<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Top Cuisine Types</title>
</head>
<body>

<h1>Top 10 Cuisine Types</h1>
<form action="/process" method="post">
    <button type="submit">Process Dataset File</button>
</form>

<hr>

<% if (request.getAttribute("topCuisineList") != null) { %>
<h2>Results:</h2>
<table>
    <tr>
        <th>Cuisine Type</th>
        <th>Count</th>
    </tr>
    <c:forEach var="cuisine" items="${topCuisineList}">
    <tr>
        <td>${cuisine.cuisine_type}</td>
        <td>${cuisine.count}</td>
    </tr>
    </c:forEach>
</table>
<% } %>

</body>