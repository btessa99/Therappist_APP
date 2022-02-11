<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<h1>CHANGE CREDENTIALS</h1>

<p id="error-field">${errorMessage}</p>

<form method="post">

    <label>Password: <input type="password" name="pass" value=""/> </label> <br>
    <label>Confirm Password: <input type="password" name="pass_confirm" value=""/> </label><br>

    <br>
    <label for="fields-select">Select the new issue:</label>

    <select name="fields" id="fields-select">
        <option value=""></option>
        <option value="Academic Issues" <c:if test = "${user.issue == 'Academic Issues'}">selected</c:if>>Academic Issues</option>
        <option value="Adoption" <c:if test = "${user.issue == 'Adoption'}">selected</c:if>>Adoption</option>
        <option value="Anger Management" <c:if test = "${user.issue == 'Anger Management'}">selected</c:if>>Anger Management</option>
        <option value="Antisocial Personality" <c:if test = "${user.issue == 'Antisocial Personality'}">selected</c:if>>Antisocial Personality</option>
        <option value="Anxiety" <c:if test = "${user.issue == 'Anxiety'}">selected</c:if>>Anxiety</option>
        <option value="Coping Skills" <c:if test = "${user.issue == 'Coping Skills'}">selected</c:if>>Coping Skills</option>
        <option value="Discrimination" <c:if test = "${user.issue == 'Discrimination'}">selected</c:if>>Discrimination</option>
        <option value="Divorce" <c:if test = "${user.issue == 'Divorce'}">selected</c:if>>Divorce</option>
        <option value="Family Conflict" <c:if test = "${user.issue == 'Family Conflict'}">selected</c:if>>Family Conflict</option>
        <option value="Fear" <c:if test = "${user.issue == 'Fear'}">selected</c:if>>Fear</option>
        <option value="Goal Setting" <c:if test = "${user.issue == 'Goal Setting'}">selected</c:if>>Goal Setting</option>
        <option value="Grief" <c:if test = "${user.issue == 'Grief'}">selected</c:if>>Grief</option>
        <option value="Marital / Premarital" <c:if test = "${user.issue == 'Marital / Premarital'}">selected</c:if>>Marital / Premarital</option>
        <option value="Parenting" <c:if test = "${user.issue == 'Parenting'}">selected</c:if>>Parenting</option>
        <option value="Relationship Issues" <c:if test = "${user.issue == 'Relationship Issues'}">selected</c:if>>Relationship Issues</option>
        <option value="Self Esteem" <c:if test = "${user.issue == 'Self Esteem'}">selected</c:if>>Self Esteem</option>
        <option value="Sexual Abuse" <c:if test = "${user.issue == 'Sexual Abuse'}">selected</c:if>>Sexual Abuse</option>
        <option value="Sleep or Insomnia" <c:if test = "${user.issue == 'Sleep or Insomnia'}">selected</c:if>>Sleep or Insomnia</option>
        <option value="Smoking Cessation" <c:if test = "${user.issue == 'Smoking Cessation'}">selected</c:if>>Smoking Cessation</option>
        <option value="Trauma" <c:if test = "${user.issue == 'Trauma'}">selected</c:if>>Trauma</option>
    </select>

    <input type="submit" value="Submit">
</form>
</body>
</html>