import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.*;

@WebServlet(name = "ConfirmTicket", urlPatterns = { "/ConfirmTicket" })
public class ConfirmTicket extends HttpServlet {
       
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter output;
	    response.setContentType( "text/html" );
	    output = response.getWriter();

	    String tickets = request.getParameter("tickets");
	    
		Cookie ctickets = new Cookie("tickets", tickets);
		ctickets.setMaxAge(-1);  // session cookie
	    response.addCookie(ctickets);
	    
		Cookie cookies[];
	    cookies = request.getCookies(); // get client's cookies
	    String transport = null;
	    String depart = null;
	    String destination = null;
	    
		for (Cookie cookie : cookies) {
		    if ("transport".equals(cookie.getName())) transport = cookie.getValue();
		    else if ("depart".equals(cookie.getName())) depart = cookie.getValue();
		    else if ("destination".equals(cookie.getName())) destination = cookie.getValue();
		}
	    
	    output.println( "<html><head><meta http-equiv='Content-Type' content='text/html; charset=iso-8859-7'> <title>Travel on Screen</title> </head> <body> <h2>Please Confirm Your Tickets</h2><br><br> " );
	    output.println( "<form action='BookTicket' method='post'>");
        if (transport.equals("Airplane")) {

        	String firstname = request.getParameter("firstname");
        	Cookie cfirstname = new Cookie("firstname", firstname);
        	cfirstname.setMaxAge(-1);  // session cookie
            response.addCookie(cfirstname);

        	String lastname = request.getParameter("lastname");
        	Cookie clastname = new Cookie("lastname", lastname);
        	clastname.setMaxAge(-1);  // session cookie
            response.addCookie(clastname);

        	String classp = request.getParameter("classp");
        	Cookie cclassp = new Cookie("classp", classp);
        	cclassp.setMaxAge(-1);  // session cookie
            response.addCookie(cclassp);
            
        	output.println( "<strong>Method of transportation: </strong>" + transport );
			output.println( "<br><br>" );
        	output.println( "<strong>Departure: </strong>" + depart );
			output.println( "<br><br>" );
        	output.println( "<strong>Destination: </strong>" + destination );
			output.println( "<br><br>" );
        	output.println( "<strong>Class: </strong>" + classp );
			output.println( "<br><br>" );
        	output.println( "<strong>Owner: </strong>" + firstname + "&nbsp" + lastname );
			output.println( "<br><br>" );
        	output.println( "<strong>Number of tickets: </strong>" + tickets );
		}
        else if (transport.equals("Train")) {

        	String classp = request.getParameter("classp");
        	Cookie cclassp = new Cookie("classp", classp);
        	cclassp.setMaxAge(-1);  // session cookie
            response.addCookie(cclassp);
            
        	output.println( "<strong>Method of transportation: </strong>" + transport );
			output.println( "<br><br>" );
        	output.println( "<strong>Departure: </strong>" + depart );
			output.println( "<br><br>" );
        	output.println( "<strong>Destination: </strong>" + destination );
			output.println( "<br><br>" );
        	output.println( "<strong>Class: </strong>" + classp );
			output.println( "<br><br>" );
        	output.println( "<strong>Number of tickets: </strong>" + tickets );
        }
        else {
        	output.println( "<strong>Method of transportation: </strong>" + transport );
			output.println( "<br><br>" );
        	output.println( "<strong>Departure: </strong>" + depart );
			output.println( "<br><br>" );
        	output.println( "<strong>Destination: </strong>" + destination );
			output.println( "<br><br>" );
        	output.println( "<strong>Number of tickets: </strong>" + tickets );
			}
        output.println( "<input type='hidden' name='input' value='index'>");
		output.println( "<br><br>" );
        output.println( "<input type='submit' value='Confirm Ticket'></form>");
		output.println( "<br><br>" );
		output.println("<input Type='button' VALUE='Go Back' onClick='history.go(-1);return true;'>" );
		output.println( "<br>" );
		output.println("<form action='index.html' method='post'>" );
		output.println( "<br>" );
		output.println("<input type='submit' value='Go to the beginning'>" );
		output.println( "</form>" );
		output.println("<footer><br><br>Dimitrios Karakostas - 03110179</footer>" );
		output.println( "</body></html>" );
		output.close();
	}

}
