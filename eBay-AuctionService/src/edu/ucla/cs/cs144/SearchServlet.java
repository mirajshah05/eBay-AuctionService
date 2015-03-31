package edu.ucla.cs.cs144;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet implements Servlet {
       
    public SearchServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // your codes here
        try{
        String pageTitle = "My Page Title";
        
        //Enumeration<String> parameterNames = request.getParameterNames();
        
        String query = "default";
        query=URLEncoder.encode(request.getParameter("q"),"UTF-8");
        
        String skip = request.getParameter("numResultsToSkip");
        String return1 = request.getParameter("numResultsToReturn");
        int skp = 0;
        int nskip = Integer.parseInt(skip);//Integer.parseInt();
        int nreturn = 20000;//Integer.parseInt(request.getParameter("return"));
        int ret = Integer.parseInt(return1);
        if(ret<1){ret=20000;}
        AuctionSearchClient search = new AuctionSearchClient();
        //System.out.println("skip "+skip+" return"+return1);
        SearchResult results[] = search.basicSearch(query,skp,ret);
        //int length = results.length;
        //int length = 0;
        //List<String> temp = new ArrayList<String>();
        
        
        List<String> item = new ArrayList<String>() {};
        List<String> name = new ArrayList<String>() {};
        /*for(int i=0;i<results.length;i++)
            item.add(results[i]);*/
        //int max = 25;
        int pages = (int)  Math.ceil(results.length/15);
        if((results.length%15)!=0){pages++;}
        if(pages<=0){pages=1;}
        
        for(int i=nskip;i<results.length&&i<(15+nskip);i++){
            String a = results[i].getItemId();
            item.add(a);
            name.add(results[i].getName());
            //System.out.println(" "+a);
        }
        request.setAttribute("query_string",query);
        request.setAttribute("item", item);
        request.setAttribute("name", name);
        request.setAttribute("links", String.valueOf(pages));
        request.setAttribute("tlength", String.valueOf(results.length));
        request.setAttribute("start", String.valueOf(skip));
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        catch(Exception E){
            request.getRequestDispatcher("/item").forward(request,response);
        }
    }
   
}
