import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;  

import javax.xml.transform.dom.*;

public class XMLTransformerAskhsh extends HttpServlet {
	ServletContext ctx;
	ServletContext ctx_both;
	ServletContext ctx_children;
	ServletContext ctx_attr;
	String absPath_both;
	String absPath_children;
	String absPath_attr;
	SAXSource xsltDoc; TransformerFactory tF;
	Transformer myTransformer;
	Document doc;
	Document doc_both;
	Document doc_children;
	Document doc_attr;

	public void init(ServletConfig config) throws UnavailableException {
		try {
			ctx_both = config.getServletContext();
			absPath_both = ctx_both.getRealPath("/WEB-INF/CarPresentor_both.xsl");
			xsltDoc = new SAXSource(new InputSource(absPath_both));
			tF = TransformerFactory.newInstance();
			DocumentBuilderFactory fact_both = DocumentBuilderFactory.newInstance();
			fact_both.setNamespaceAware(true);
			DocumentBuilder builder_both = fact_both.newDocumentBuilder();
			doc_both = builder_both.parse(absPath_both);

			ctx_children = config.getServletContext();
			absPath_children = ctx_children.getRealPath("/WEB-INF/CarPresentor_children.xsl");
			xsltDoc = new SAXSource(new InputSource(absPath_children));
			tF = TransformerFactory.newInstance();
			DocumentBuilderFactory fact_children = DocumentBuilderFactory.newInstance();
			fact_children.setNamespaceAware(true);
			DocumentBuilder builder_children = fact_children.newDocumentBuilder();
			doc_children = builder_children.parse(absPath_children);

			ctx_attr = config.getServletContext();
			absPath_attr = ctx_attr.getRealPath("/WEB-INF/CarPresentor_attr.xsl");
			xsltDoc = new SAXSource(new InputSource(absPath_attr));
			tF = TransformerFactory.newInstance();
			DocumentBuilderFactory fact_attr = DocumentBuilderFactory.newInstance();
			fact_attr.setNamespaceAware(true);
			DocumentBuilder builder_attr = fact_attr.newDocumentBuilder();
			doc_attr = builder_attr.parse(absPath_attr);
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	private void changeDomByColor(Document doc, String color) {
		NodeList nl = doc.getElementsByTagName("h1");
		Attr a = doc.createAttribute("style");
		a.setValue("background-color: " + color);
		nl.item(0).getAttributes().setNamedItem(a);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String color = request.getParameter("color");
		String pres = request.getParameter("pres");
		if (pres.equals("both")) {
			doc = doc_both;
			ctx = ctx_both;
		}
		else if (pres.equals("children")) {
			doc = doc_children;
			ctx = ctx_children;
		}
		else {
			doc = doc_attr;
			ctx = ctx_attr;
		}
		changeDomByColor(doc, color);
		PrintWriter pwr = response.getWriter();
		try {
			DOMSource ds = new DOMSource(doc) ; 
	       	myTransformer = tF.newTransformer(ds);

			StreamSource xmlSource = new StreamSource(ctx.getResourceAsStream("/WEB-INF/Cars.xml"));
			response.setContentType("text/html"); //in order to put in http body
			myTransformer.transform(xmlSource, new StreamResult(pwr));
			pwr.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		PrintWriter output;
		response.setContentType( "text/html" );
		output = response.getWriter();         
		output.println( "<html><head><title>Chop Shop" );
		output.println( "</title></head><body>" );
		output.println( "<div class=\"menu\" style=\"text-align:center; float:center;\">" );
		output.println( "<h2>Welcome to Chop Shop</h2>" );
		output.println( "<img alt=\"Welcome\" src=\"http://thechopshopbarandgrill.com/wp-content/uploads/2013/09/Chop-Shop-Logo-New.png\"> <!-- Online image path, may be corrupted --> <br><br>");
		output.println( "<form action='XMLTransformerAskhsh' method='post'>");
		output.println( "<strong>Please select header color:<br> </strong><pre>");
		output.println( "<input type='radio' name='color' value='red' checked>Red<br>");
		output.println( "<input type='radio' name='color' value='#33CCCC'>Light green<br>");
		output.println( "<input type='radio' name='color' value='#00CCFF'>Light blue<br></pre>");
		output.println( "<strong>Please select xml info:<br> </strong><pre>");
		output.println( "<input type='radio' name='pres' value='both' checked>Both<br>");
		output.println( "<input type='radio' name='pres' value='children'>Children<br>");
		output.println( "<input type='radio' name='pres' value='attr'>Attributes<br></pre>");
     	output.println( "</pre><input type='submit' value='Submit'>");
		output.println( "</form>");
		output.println( "</div></body></html>" );
		output.close();
	}
	
}