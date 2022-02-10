<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<p>Hello ${user.fullName}  ${user.therapist}</p>

<form action="patient-page" method="get">
    <input type = "submit" value="submit">
</form>
</body>
</html>