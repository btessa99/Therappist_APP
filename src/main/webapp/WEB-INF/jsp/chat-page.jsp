<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chat.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/chat.js"></script>
</head>


<body onload="connect('${user.username}')">


<c:choose>
    <c:when test = "${role == 'patient'}">
        <a href="terminate">Terminate Therapy</a>&nbsp;
        <a href="change-credentials">Change your credentials</a> &nbsp;&nbsp;
    </c:when>
    <c:otherwise>
        <a href="change-credentials-therapists">Change your credentials</a> &nbsp;
        <a href="therapist-page">Change Patient</a> &nbsp;
    </c:otherwise>
</c:choose>
<a href="logout">Logout</a>

<div id = "title">
    <h3>THERAPPIST CHAT</h3>
</div>
<div id="chat-holder">
    <c:forEach var="message" items="${history}">
        <c:choose>
            <c:when test = "${message.sender == user.username}">
                <div class="message_sent">
                    <label class="color">Me</label>
                    <p class="message_text">${message.text}</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="message_received">
                    <label>${endpoint}</label>
                    <p class="message_text">${message.text}</p>
                </div>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>
<br>
<div id="send">
    <label> <textarea id="msg" rows="4" cols="60"></textarea></label>
    <button type="submit" onclick="send('${user.username}', '${endpoint}')">Send</button>

</div>
</body>
</html>