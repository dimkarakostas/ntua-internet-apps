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
if (inp.equals("route")) { // redirection from route jsp
	String depart = request.getParameter("depart");
	Cookie cdepart = new Cookie("depart", depart);
	cdepart.setMaxAge(-1);  // session cookie
    response.addCookie(cdepart);
	
	String destination = request.getParameter("destination");
	Cookie cdestination = new Cookie("destination", destination);
	cdestination.setMaxAge(-1);  // session cookie
    response.addCookie(cdestination);
	
	next_page = "PassengerInfo";
}
else {
	next_page = "Route.jsp";
}
ArrayList<String> tr = new ArrayList<String>(Arrays.asList("Airplane", "Bus", "Train"));
%>
<h2>Please Select Method Of Transportation</h2>
<br>
<form action='<% out.print(next_page); %>' method='get'>
<table>
<tr>
<td>
<div id='transportation' style="height:100px;width:100px;float:left;">
<%
for (int i = 0; i < tr.size(); i++) {
%>
<input type='radio' name='transport' value='<% out.print(tr.get(i)); %>'> <% out.print(tr.get(i)); %> <br>
<%
}
%>
</div> </td> </tr>
</table>
<input type='hidden' name='input' value='transportation'>
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