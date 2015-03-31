package edu.ucla.cs.cs144;

import java.io.IOException;
import java.io.StringReader;
import java.util.Comparator;
import java.util.Date;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // your codes here
        try{
            String id = "default";
            id =   request.getParameter("id");
            
            AuctionSearchClient search = new AuctionSearchClient();
            String xmldata = search.getXMLDataForItemId(id);
            if(xmldata.length()<10)
			{
                request.setAttribute("xmldata", null);
            }
            else{
                 MyParser parse1 = new MyParser();
                Item_interf item = parse1.processFile(xmldata);
                /*if(item==null){
                    response.sendRedirect(request.getHeader("referrer"));
                }*/

                /*Item_interf.sort(new Comparator<Item_interf>() {
                        @Override
                        public int compare(Item_interf b1, Item_interf b2) {
                            if(b1.Time_content.equals(b2.Time_content)) return 0;
                            try {
                                Date bidTime1 = timestampFormat.parse(b1.Time_content);
                                 Date bidTime2 = timestampFormat.parse(b2.Time_content);
                                 if(bidTime1.before(bidTime2)) return -1;
                                return 1;
                            } catch (Exception ex) {
                                ex.printStackTrace();
                               // ErrorDispatcher.redirectOnError();
                            }               
                            return 0;
                        }
                    });


                */
				// new lines of code
				HttpSession session = request.getSession(true);
				session.setAttribute("xmldata", item);
				// end of new lines of code
                request.setAttribute("xmldata", item);
                //System.out.println(" "+item.UserID);
                //request.setAttribute("name", name);
                
            }
           request.getRequestDispatcher("/itemdetails.jsp").forward(request, response);
		   //response.sendRedirect("http://localhost:1448/itemdetails.jsp");
        }
        catch(Exception e){
            //response.sendRedirect(request.getHeader("referer"));
			request.getRequestDispatcher("/item").forward(request,response);
        }
        
    }
}
