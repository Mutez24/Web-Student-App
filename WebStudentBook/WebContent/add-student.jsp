<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css"> 
<title>Web Student Book Add Student</title>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>ESILV Engineer School</h2>
    	</div>
	</div>
	<div id="container">
		<h3>Add New Student</h3> 
		<div id="content">
			<form action="AddStudentServlet" method="post"> 
				<table>
			        <tr>
			            <td> First Name: </td>
			            <td> <input type="text" name="firstName" /> </td>
			        </tr>
			        <tr>
			            <td> Last Name: </td>
			            <td> <input type="text" name="lastName" /> </td>
			        </tr>        
			        <tr>
			            <td> Email: </td>
			            <td> <input type="text" name="email" /> </td>
			        </tr>       
			        <tr>
			            <td><label></label> </td>
			            <td> <input type="submit" value="Save" /> </td>
			        </tr>
    			</table>
    			<tr>
			    	<a href="StudentControllerServlet">Back to list</a>
			    </tr>
			</form>
		</div>
	</div>
</body>
</html>