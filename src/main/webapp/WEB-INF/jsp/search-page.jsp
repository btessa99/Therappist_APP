<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css" />
</head>
<body>
<h1 class="color">Welcome, ${user.fullName}</h1>
<p class="color" id="bigger">Choose your therapist from the list below:</p>
<c:forEach var="therapist" items="${availableTherapists}">
    <div class="info-therapist">
    <form method="post">
        <input type="hidden" name="therapist" value=${therapist.username}><br>
        <b class="color">${therapist.fullName}</b><br>
        <b class="color">Specialized in: </b>
        <label> ${therapist.specialization1}</label>
        <label>, ${therapist.specialization2}</label>
        <label>and ${therapist.specialization3}</label> <br>
        <p> ${therapist.biography}</p> <br>
        <button type="submit" id="submit" class="color"> Choose ${therapist.fullName}!</button>
    </form>
    </div>
</c:forEach>
</body>
</html>