<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Travel on Screen</title>
</head>
<body>

<%
response.setContentType( "text/html" ); 
String inp = request.getParameter("input");
String next_page = new String();
if (inp.equals("transportation")) { // redirection from transportation jsp
	String transport = request.getParameter("transport");
	Cookie ctransport = new Cookie("transport", transport);
	ctransport.setMaxAge(-1);  // session cookie
    response.addCookie(ctransport);
	next_page = "PassengerInfo";
}
else {
	next_page = "Transportation.jsp";
}
ArrayList<String> city = new ArrayList<String>(Arrays.asList("Athens", "Paris", "Berlin", "Amsterdam", "Moscow", "Istanbul", "Vienna", "Budapest", "London"));
%>
<h2>Please Select route</h2>
<br>
<form action='<% out.print(next_page); %>' method='get'>
<table>
<tr>
<td>
<div id='depart' style="height:250px;width:200px;float:left;">
<strong>Departure</strong><br><br>
<%
for (String c : city) {
%>
<input type='radio' name='depart' value='<% out.print(c); %>'> <% out.print(c); %> <br>
<%
	}
%>
<br></div>
</td>
<td>
<div id='destination' style="height:250px;width:200px;float:left;">
<strong>Destination</strong><br><br>
<%
for (String c : city) {
%>
<input type='radio' name='destination' value='<% out.print(c); %>'> <% out.print(c); %> <br>
<%
}
%>
<br><br></div>
</td>
</tr>
</table>
<input type='hidden' name='input' value='route'>
<input type='submit' value='Submit'>
<input type='reset' value='Clear'>
</form>
<br><br>
<input type='button' VALUE='Go Back' onClick='history.go(-1);return true;'>
<br>
<form action='index.html' method='post'>
<br>
<input type='submit' value='Go to the beginning'>
</form>
<br><br>
Dimitrios Karakostas - 03110179

</body>
</html>