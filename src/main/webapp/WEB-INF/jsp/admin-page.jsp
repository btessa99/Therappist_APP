<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css" />
</head>
<body>
<a href="logout">Logout</a> <br>
<h1 class="color">Administration page</h1>
<p class="color" id="bigger">List of therapists with pending approval</p>
<c:forEach var="therapist" items="${pendingTherapists}">
    <div class="info-therapist">
        <form method="post">
            <input type="hidden" name="therapist" value=${therapist.username}><br>
            <b class="color">${therapist.fullName}</b><br>
            <b class="color">Specialized in: </b>
            <label> ${therapist.specialization1}</label>
            <label>, ${therapist.specialization2}</label>
            <label>and ${therapist.specialization3}</label> <br>
            <p> ${therapist.biography}</p> <br>
            <button type="submit" id="submit" class="color"> Approve ${therapist.fullName}</button>
        </form>
    </div>
</c:forEach>
</body>
</html>