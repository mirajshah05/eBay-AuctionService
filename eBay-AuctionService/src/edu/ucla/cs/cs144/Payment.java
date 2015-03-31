/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucla.cs.cs144;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author miraj
 */
public class Payment extends HttpServlet  implements Servlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // your codes here
        String card_number = "";
        try{
            HttpSession session = request.getSession();
            if(!session.isNew()){
                card_number =   request.getParameter("credit_card_no");
                session.setAttribute("credit_card_no", card_number);
                String name1 = request.getServletContext().getContextPath();
                String sname1 = request.getServerName();
                //String name = "https://"+sname1+":8443"+name1+"/confirmation.jsp";
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String time = dateFormat.format(date);
                session.setAttribute("time_transaction", time);
                //System.out.println(dateFormat.format(date));
               request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
            }
            else{
                request.getRequestDispatcher("/item").forward(request,response);
            }
                
		   //response.sendRedirect("http://localhost:1448/itemdetails.jsp");
        }
        catch(Exception e){
            //response.sendRedirect(request.getHeader("referer"));
			request.getRequestDispatcher("/item").forward(request,response);
        }
        
    }
    
}
