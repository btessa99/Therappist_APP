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
        <a href="change-credentials">Change your credentials</a> &nbsp;
    </c:when>
    <c:otherwise>
        <a href="change-credentials-therapists">Change your credentials</a>
        <a href="therapist-page.jsp">Change Patient</a>
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
                    <p class="message_text">${message.text}</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="message_received">
                    <p class="message_text">${message.text}</p>
                </div>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>
<div id="send">
    <label> <textarea id="msg"></textarea></label>
    <button onclick="send('${user.username}', '${endpoint}')">Send</button>
</div>
</body>
</html>