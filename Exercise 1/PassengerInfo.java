import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.*;

@WebServlet(name = "PassengerInfo", urlPatterns = { "/PassengerInfo" })
public class PassengerInfo extends HttpServlet {
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter output;
	    response.setContentType( "text/html" ); 
	    output = response.getWriter();
	    String transport = null;

	    String inp = request.getParameter("input");
	    if (inp.equals("transportation")) { // redirection from transportation jsp
	    	transport = request.getParameter("transport");   // choice made will be sent back to client
	    	Cookie ctransport = new Cookie("transport", transport);   // to be stored there as a cookie
	    	ctransport.setMaxAge(-1);  // session cookie
	        response.addCookie(ctransport);
	    }
	    else { // redirection from route jsp
	    	String depart = request.getParameter("depart");
	    	Cookie cdepart = new Cookie("depart", depart);
	    	cdepart.setMaxAge(-1);  // session cookie
	        response.addCookie(cdepart);
	    	
	    	String destination = request.getParameter("destination");
	    	Cookie cdestination = new Cookie("destination", destination);
	    	cdestination.setMaxAge(-1);  // session cookie
	        response.addCookie(cdestination);
	        
			Cookie cookies[];  
		    cookies = request.getCookies(); // get client's cookies
		    for (Cookie cookie : cookies) {
			    if ("transport".equals(cookie.getName())) transport = cookie.getValue();
			}
	    }
        
        output.println( "<html><head><meta http-equiv='Content-Type' content='text/html; charset=iso-8859-7'> <title>Travel on Screen</title> </head> <body> <h2>Please choose your depart info</h2><br><br> " );
		output.println( "<form action='ConfirmTicket' method='post'>" );
		
        if (transport.equals("Airplane")) {
			output.println( "<strong>Select Class&nbsp&nbsp</strong>" );
			output.println( "<select name='classp'>" );
			output.println( "<option value='Choose'>Class</option>" );
			output.println( "<option value='1st Class'>1st Class</option>" );
			output.println( "<option value='Business'>Business</option>" );
			output.println( "</select>" );
			output.println( "<br><br>" );
			output.println( "<strong>Please fill in your name</strong><br><br>" );
			output.println( "First Name: ");
			output.println( "<input type='text' name='firstname'><br>" );
			output.println( "Last Name: ");
			output.println( "<input type='text' name='lastname'><br><br>" );
		}
		else if (transport.equals("Train")) {
			output.println( "<strong>Select Class&nbsp&nbsp</strong>" );
			output.println( "<select name='classp'>" );
			output.println( "<option value='Choose'>Class</option>" );
			output.println( "<option value='1st Class'>1st Class</option>" );
			output.println( "<option value='Business'>Business</option>" );
			output.println( "</select>" );
			output.println( "<br><br><br>" );
		}

		output.println( "<strong>Select Number of tickets</strong><br><br>" );
		output.println( "<select name='tickets'>" );
		output.println( "<option value='0'>0</option>" );
		output.println( "<option value='1'>1</option>" );
		output.println( "<option value='2'>2</option>" );
		output.println( "<option value='3'>3</option>" );
		output.println( "<option value='4'>4</option>" );
		output.println( "<option value='5'>5</option>" );
		output.println( "</select>" );
		output.println( "<br><br><br>" );
		output.println( "<input type='submit' value='Submit'>" );
		output.println( "<input type='reset' value='Clear'></form>" );
		output.println( "<br><br><br>" );
		output.println("<intut type='button' value='Go Back' onClick='history.go(-1);return true;'>" );
		output.println( "<br>" );
		output.println("<form action='index.html' method='post'>" );
		output.println( "<br>" );
		output.println("<input type='submit' value='Go to the beginning'>" );
		output.println( "</form>" );
		output.println("<br><br>Dimitrios Karakostas - 03110179" );
		output.println( "</body></html>" );
		output.close();
		
	}

}
