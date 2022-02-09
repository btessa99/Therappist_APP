
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<h1>SIGN IN</h1>

<p>Before accepting your request we have to make sure you have the right qualification. Compile this form and
we will contact you as soon as possible to schedule an interview</p>

<form method="post" >
    <label>First Name:  <input type="text" name="first_name" required/> </label><br>
    <label>Last Name: <input type="text" name="last_name" required/></label> <br>
    <label>Email: <input type="text" name="email" required/></label>  <br>
    <label>Phone Number: <input type="text" name="phone_number" required/></label>  <br>
    <label>Password: <input type="password" name="pass" required/> </label> <br>
    <label>Confirm Password:  <input type="password" name="pass_confirm" required/> </label><br>
    <br>

   <label>Choose the max number of patients you want to follow: <input type="number" min="1" max="5"> </label>

    <br>

    <label >You can choose up to three specialisation fields:</label><br>

        <input type="checkbox" id = "field1" name="field" value="Academic Issues">
        <label for="field1"> Academic issues</label><br>
        <input type="checkbox" id = "field2" name="field" value="Adoption">
        <label for="field2"> Adoption </label><br>
        <input type="checkbox" id = "field3" name="field" value="Anger Management">
        <label for="field3"> Anger Management</label><br>
        <input type="checkbox" id = "field4" name="field" value="Antisocial Personality">
        <label for="field4"> Antisocial Personality</label><br>
        <input type="checkbox" id = "field5" name="field" value="Anxiety">
        <label for="field5">Anxiety</label><br>
        <input type="checkbox" id = "field6" name="field" value="Coping Skills">
        <label for="field6">Coping Skills</label><br>
        <input type="checkbox" id = "field7" name="field" value="Discrimination">
        <label for="field7"> Discrimination</label><br>
        <input type="checkbox" id = "field8" name="field" value="Divorce">
        <label for="field8">Divorce</label><br>
        <input type="checkbox" id = "field9" name="field" value="Family Conflict">
        <label for="field9">Family Conflict</label><br>
        <input type="checkbox" id = "field10" name="field" value="Fear">
        <label for="field10">Fear</label><br>
        <input type="checkbox" id = "field11" name="field" value="Goal Setting">
        <label for="field11">Goal Setting</label><br>
        <input type="checkbox" id = "field12" name="field" value="Marital / Premarital">
        <label for="field12">Marital / Premarital</label><br>
        <input type="checkbox" id = "field13" name="field" value="Parenting">
        <label for="field13">Parenting</label><br>
        <input type="checkbox" id = "field14" name="field" value="Relationship Issues">
        <label for="field14">Relationship Issues</label><br>
        <input type="checkbox" id = "field15" name="field" value="Self Esteem">
        <label for="field15">Self Esteem</label><br>
        <input type="checkbox" id = "field16" name="field" value="Sexual Abuse">
        <label for="field16">Sexual Abuse</label><br>
        <input type="checkbox" id = "field17" name="field" value="Sleep or Insomnia">
        <label for="field17">Sleep or Insomnia</label><br>
        <input type="checkbox" id = "field18" name="field" value="Smoking Cessation">
        <label for="field18">Smoking Cessation</label><br>
        <input type="checkbox" id = "field19" name="field" value="Trauma">
        <label for="field19">Trauma</label><br>


    <input type="submit" value="Submit">
</form>
</body>
</html>
