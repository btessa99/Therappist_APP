<%@ page import="it.unipi.dii.dsmt.therappist.jstlUtility.JstlUtility" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
<h1>Welcome, ${user.fullName}</h1>
<h2>Choose your therapist from the list below:</h2>
<c:forEach var="therapist" items="${availableTherapists}">
    <tr>
        <td>${therapist.fullName}</td>
        <td></td>
    </tr>
</c:forEach>
</body>
</html>