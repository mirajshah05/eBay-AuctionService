package edu.ucla.cs.cs144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProxyServlet extends HttpServlet implements Servlet {
       
    public ProxyServlet()
    {
    }


    private final String USER_AGENT = "Mozilla/5.0";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
         
          
         response.setContentType("text/xml");
         response.setHeader("Cache-Control", "no-cache");
         String keyword_received = request.getParameter("keyword_entered");
         PrintWriter out = response.getWriter();
         
        try 
        {
        String url="http://google.com/complete/search?output=toolbar&q="+keyword_received;
        URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	//String inputLine;
        //inputLine=in.readLine();
        int inputLine;
        while ((inputLine = in.read()) != -1) 
        {
		out.write(inputLine);
        }
	in.close();    
        }
        catch (Exception ex)
        {
            Logger.getLogger(ProxyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.close();


    }
}
