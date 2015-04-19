import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.*;

@WebServlet(name = "BookTicket", urlPatterns = { "/BookTicket" })
public class BookTicket extends HttpServlet {
       
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter output;
	    response.setContentType( "text/html" ); 
	    output = response.getWriter();

		Cookie cookies[];  
	    cookies = request.getCookies(); // get client's cookies
	    String transport = null;
	    String depart = null;
	    String destination = null;
	    String tickets = null;
	    String classp = null;
	    String firstname = null;
	    String lastname = null;
	    
		for (Cookie cookie : cookies) {
		    if ("transport".equals(cookie.getName())) transport = cookie.getValue();
		    else if ("depart".equals(cookie.getName())) depart = cookie.getValue();
		    else if ("destination".equals(cookie.getName())) destination = cookie.getValue();
		    else if ("tickets".equals(cookie.getName())) tickets = cookie.getValue();
		    else if ("classp".equals(cookie.getName())) classp = cookie.getValue();
		    else if ("firstname".equals(cookie.getName())) firstname = cookie.getValue();
		    else if ("lastname".equals(cookie.getName())) lastname = cookie.getValue();
		}
	    
	    output.println( "<html><head><meta http-equiv='Content-Type' content='text/html; charset=iso-8859-7'> <title>Travel on Screen</title> </head> <body> <h2>Your Tickets</h2><br><br> " );
	    
        if (transport.equals("Airplane")) {
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
        else if(transport.equals("Train")) {
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
        output.println( "<br><br>" );
		output.println("<h3>Thank you!</h3>");
		output.println( "<br><br>" );
		output.println("<form action='index.html' method='post'>" );
		output.println( "<br>" );
		output.println("<input type='submit' value='Go to the beginning'>" );
		output.println( "</form>" );
		output.println("<footer><br><br>Dimitrios Karakostas - 03110179</footer>" );
		output.println( "</body></html>" );
		output.close();
	}

}
