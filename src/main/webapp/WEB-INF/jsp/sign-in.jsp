
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<h1>SIGN IN</h1>

<form method="post">
    <label>First Name:  <input type="text" name="first_name" required/> </label><br>
    <label>Last Name: <input type="text" name="last_name" required/></label> <br>
    <label>Email: <input type="text" name="email" required/></label>  <br>
    <label>Username: <input type="text" name="username" required/></label>  <br>
    <label>Password: <input type="password" name="pass" required/> </label> <br>
    <label>Confirm Password:  <input type="password" name="pass_confirm" required/> </label><br>

<br>
    <label for="fields-select">You need help with:</label>

    <select name="fields" id="fields-select">
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
