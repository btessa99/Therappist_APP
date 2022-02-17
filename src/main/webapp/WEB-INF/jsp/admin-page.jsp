<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css" />
</head>
<body>
<a href="logout">Logout</a> &nbsp;<a href="therapist-page">Chat with users</a>
<h1 class="color">Administration page</h1>
<p class="color" id="bigger">List of therapists with pending approval</p>
<c:forEach var="therapist" items="${pendingTherapists}">
    <div class="info-therapist">
        <form method="post" name="approve">
            <input type="hidden" name="therapist" value=${therapist.username}><br>
            <b class="color">${therapist.fullName}</b><br>
            <b class="color">Specialized in: </b>
            <label> ${therapist.specialization1}</label>
            <label>, ${therapist.specialization2}</label>
            <label>and ${therapist.specialization3}</label> <br>
            <p> ${therapist.biography}</p> <br>
            <input type="radio" id = "active-state" value="active" name="state" checked >
            <label for="active-state"> Accept </label><br>
            <input type="radio" id = "unavailable-state" value="decline" name="state">
            <label for="unavailable-state"> Decline </label><br>
            <button type="submit" id="submitAccept" class="color"> Change State to ${therapist.fullName}</button>
        </form>
    </div>
</c:forEach>
<hr class="color">
<p class="color" id="bigger">List of therapists that can be promoted as Admin</p>
<c:forEach var="therapist" items="${activeTherapists}">
    <div class="info-therapist">
        <form method="post" name="admin">
            <input type="hidden" name="therapistActive" value=${therapist.username}><br>
            <b class="color">${therapist.fullName}</b><br>
            <b class="color">Specialized in: </b>
            <label> ${therapist.specialization1}</label>
            <label>, ${therapist.specialization2}</label>
            <label>and ${therapist.specialization3}</label> <br>
            <p> ${therapist.biography}</p> <br>
            <button type="submit" id="submitAdmin" class="color"> Promote ${therapist.fullName}</button>
        </form>
    </div>
</c:forEach>
</body>
</html>