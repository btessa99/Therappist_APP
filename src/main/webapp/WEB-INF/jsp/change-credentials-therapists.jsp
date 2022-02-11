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

    <label>Change the number of patients you want to follow: <input type="number" name="number" min="1" max="5" value=${user.maxPatients}> </label> <br>

    <p> You are now <b>${user.state} </b>. Do you want to change your state?
     Note that state = <b>active</b> means that you are still able to get new patients</b>
    whereas state = <b>unavailable</b> means that new patients are no longer able to contact you.</p>
    <input type="radio" id = "active-state" value="active" name="change" <c:if test = "${user.state == 'active' }"> checked </c:if> >
    <label for="active-state"> Active </label><br>
    <input type="radio" id = "unavailable-state" value="unavailable" name="change" <c:if test = "${user.state == 'unavailable' }"> checked </c:if>>
    <label for="unavailable-state"> Unavailable </label><br>

    <br>

    <label> Choose your new specializations:</label><br>

    <input type="checkbox" id = "field1" name="field" value="Academic Issues"  <c:if test = "${user.specialization1 == 'Academic Issues' ||user.specialization2 == 'Academic Issues' || user.specialization3 == 'Academic Issues' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field1"> Academic issues</label><br>
    <input type="checkbox" id = "field2" name="field" value="Adoption"  <c:if test = "${user.specialization1 == 'Adoption' ||user.specialization2 == 'Adoption' || user.specialization3 == 'Adoption' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field2"> Adoption </label><br>
    <input type="checkbox" id = "field3" name="field" value="Anger Management"   <c:if test = "${user.specialization1 == 'Anger Management' ||user.specialization2 == 'Anger Management' || user.specialization3 == 'Anger Management' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field3"> Anger Management</label><br>
    <input type="checkbox" id = "field4" name="field" value="Antisocial Personality"   <c:if test = "${user.specialization1 == 'Antisocial Personality' ||user.specialization2 == 'Antisocial Personality' || user.specialization3 == 'Antisocial Personality' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field4"> Antisocial Personality</label><br>
    <input type="checkbox" id = "field5" name="field" value="Anxiety"   <c:if test = "${user.specialization1 == 'Anxiety' ||user.specialization2 == 'Anxiety' || user.specialization3 == 'Anxiety' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field5">Anxiety</label><br>
    <input type="checkbox" id = "field6" name="field" value="Coping Skills"  <c:if test = "${user.specialization1 == 'Coping Skills' ||user.specialization2 == 'Coping Skills' || user.specialization3 == 'Coping Skills' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field6">Coping Skills</label><br>
    <input type="checkbox" id = "field7" name="field" value="Discrimination"  <c:if test = "${user.specialization1 == 'Discrimination' ||user.specialization2 == 'Discrimination' || user.specialization3 == 'Discrimination' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field7"> Discrimination</label><br>
    <input type="checkbox" id = "field8" name="field" value="Divorce"   <c:if test = "${user.specialization1 == 'Divorce' ||user.specialization2 == 'Divorce' || user.specialization3 == 'Divorce' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field8">Divorce</label><br>
    <input type="checkbox" id = "field9" name="field" value="Family Conflict"   <c:if test = "${user.specialization1 == 'Family Conflict' ||user.specialization2 == 'Family Conflict' || user.specialization3 == 'Family Conflict' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field9">Family Conflict</label><br>
    <input type="checkbox" id = "field10" name="field" value="Fear"  <c:if test = "${user.specialization1 == 'Fear' ||user.specialization2 == 'Fear' || user.specialization3 == 'Fear' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field10">Fear</label><br>
    <input type="checkbox" id = "field11" name="field" value="Goal Setting"   <c:if test = "${user.specialization1 == 'Goal Setting' ||user.specialization2 == 'Goal Setting' || user.specialization3 == 'Goal Setting' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field11">Goal Setting</label><br>
    <input type="checkbox" id = "field12" name="field" value="Marital / Premarital"   <c:if test = "${user.specialization1 == 'Marital / Premarital' ||user.specialization2 == 'Marital / Premarital' || user.specialization3 == 'Marital / Premarital' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field12">Marital / Premarital</label><br>
    <input type="checkbox" id = "field13" name="field" value="Parenting"   <c:if test = "${user.specialization1 == 'Parenting' ||user.specialization2 == 'Parenting' || user.specialization3 == 'Parenting' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field13">Parenting</label><br>
    <input type="checkbox" id = "field14" name="field" value="Relationship Issues" <c:if test = "${user.specialization1 == 'Relationship Issues' ||user.specialization2 == 'Relationship Issues' || user.specialization3 == 'Relationship Issues' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field14">Relationship Issues</label><br>
    <input type="checkbox" id = "field15" name="field" value="Self Esteem"  <c:if test = "${user.specialization1 == 'Self Esteem' ||user.specialization2 == 'Self Esteem' || user.specialization3 == 'Self Esteem' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field15">Self Esteem</label><br>
    <input type="checkbox" id = "field16" name="field" value="Sexual Abuse"  <c:if test = "${user.specialization1 == 'Sexual Abuse' ||user.specialization2 == 'Sexual Abuse' || user.specialization3 == 'Sexual Abuse' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field16">Sexual Abuse</label><br>
    <input type="checkbox" id = "field17" name="field" value="Sleep or Insomnia"  <c:if test = "${user.specialization1 == 'Sleep or Insomnia' ||user.specialization2 == 'Sleep or Insomnia' || user.specialization3 == 'Sleep or Insomnia' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field17">Sleep or Insomnia</label><br>
    <input type="checkbox" id = "field18" name="field" value="Smoking Cessation"  <c:if test = "${user.specialization1 == 'Smoking Cessation' ||user.specialization2 == 'Smoking Cessation' || user.specialization3 == 'Smoking Cessation' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field18">Smoking Cessation</label><br>
    <input type="checkbox" id = "field19" name="field" value="Trauma"  <c:if test = "${user.specialization1 == 'Trauma' ||user.specialization2 == 'Trauma' || user.specialization3 == 'Trauma' }">checked</c:if>
           onchange="checkNumber(this)">
    <label for="field19">Trauma</label><br>

    <input type="submit" value="Submit Changes">
</form>
</body>
</html>