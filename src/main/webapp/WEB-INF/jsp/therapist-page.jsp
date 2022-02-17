<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chat.css" />
</head>
<body>

<a href="logout">Logout</a> &nbsp;
<c:if test="${role == 'admin'}">
    <a href="admin-page">Admin Control Page</a>
</c:if>

    <div class="info-therapist">
        <form method="post">
            <label for="patient">You want to chat with:</label>
            <select name="patient" id="patient" required>
                <c:forEach var="patient" items="${myPatients}">
                    <option value="${patient.username}">${patient.username}</option>
                </c:forEach>
            </select> <br>
            <button type="submit" id="submit"> Start Chatting!</button>
        </form>
    </div>
</body>
</html>