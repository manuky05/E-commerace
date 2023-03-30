<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
        <h1> USER REGISTATION FORM</h1>
        
        
        <form action="saveUser" method="POST">
        
        <table>
        
        <tr>
        <td>USERNAME</td>
        <td><input type="text" name="userName"></td>
        </tr>
        
        <tr>
        <td>USERADDRESS</td>
        <td><input type="text" name="useraddress"></td>
        </tr>
        
        <tr>
				<td>USERAGE</td>
				<td><input type="text" name="userAge"></td>
			</tr>
        
        <tr>
        <td>PASSWORD</td>
        <td><input type="text" name="password"></td>
        </tr>
        <tr>

				<td><input type="submit" value="REGISTER"></td>
			</tr>
        
        </table>
        
        </form>
</body>
</html>