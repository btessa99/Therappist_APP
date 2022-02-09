<%--
  Created by IntelliJ IDEA.
  User: benedettatessa
  Date: 02/02/22
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>

<body>

<h1>Welcome to TherAPPist!</h1>
<br>
<p id="error-field">${errorMessage}</p>
<br>
<div>
<form  method="post">
    <label>Username : <input type="text" name="name" /></label> <br>
    <label>Password : <input type="password" name="password" /></label> <br>
    <label for="role-select">You want to login as a: </label>
    <select id = "role-select" name = "role">
        <option name = "patient"> Patient </option>
        <option name = "therapist"> Therapist </option>
    </select>
    <input type="submit" value="Login"/>
</form>
</div>
<br>
<p>You are not subscribed yet? Click <a href="/sign-in"> here!</a> if you want to sign up as a patience</p>
<p>Or <a href="/sign-in-therapist"> here!</a> if you want to become a therapist</p>
</body>

</html>