
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/controls.js"></script>
</head>
<body onload="formCheck()">
<h1>SIGN IN</h1>

<p id="error-field">${errorMessage}</p>


<form method="post" id="form">
    <label>Full Name:  <input type="text" name="full_name" required/> </label><br>
    <label>Date of birth: <input type="date" name="date_of_birth" required/></label><br>
    <label>Email: <input type="text" name="email" required id="email"/></label>  <br>
    <label>Username: <input type="text" name="username" required/></label>  <br>
    <label>Password: <input type="password" name="pass" required id="password"/> </label> <br>
    <label>Confirm Password:  <input type="password" name="pass_confirm" id="confirmation"required/> </label><br>


<br>
    <label for="fields-select">You need help with:</label>

    <select name="fields" id="fields-select" required>
        <option value=""> </option>
        <option value="Academic Issues">Academic Issues</option>
        <option value="Adoption">Adoption</option>
        <option value="Anger Management">Anger Management</option>
        <option value="Antisocial Personality">Antisocial Personality</option>
        <option value="Anxiety">Anxiety</option>
        <option value="Coping Skills">Coping Skills</option>
        <option value="Discrimination">Discrimination</option>
        <option value="Divorce">Divorce</option>
        <option value="Family Conflict">Family Conflict</option>
        <option value="Fear">Fear</option>
        <option value="Goal Setting">Goal Setting</option>
        <option value="Grief">Grief</option>
        <option value="Marital / Premarital">Marital / Premarital</option>
        <option value="Parenting">Parenting</option>
        <option value="Relationship Issues">Relationship Issues</option>
        <option value="Self Esteem">Self Esteem</option>
        <option value="Sexual Abuse">Sexual Abuse</option>
        <option value="Sleep or Insomnia">Sleep or Insomnia</option>
        <option value="Smoking Cessation">Smoking Cessation</option>
        <option value="Trauma">Trauma</option>
    </select>

<input type="submit" value="Submit">
</form>
</body>
</html>
