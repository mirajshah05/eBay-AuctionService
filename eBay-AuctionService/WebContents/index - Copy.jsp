<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.String"%>
<html>
<head>
    <title>Resultset Items</title>
    <link rel="stylesheet" type="text/css" href="styling.css">
</head>
<body >
    <div class="bg-img">
    </div>
    Hello, world.
   <% 
//creating arraylist object of type category class
List<String> list = (ArrayList<String>) request.getAttribute("item");
List<String> list2 = (ArrayList<String>) request.getAttribute("name");
String temp = String.valueOf(request.getAttribute("links"));
out.print("temp="+temp);
int npage = Integer.parseInt(temp);
//int pages = list.size()/25;
int i = 0;
//storing passed value from jsp

//list = request.getAttribute("item");
out.println("<br>");

%>
<div>
    <form action="search">
            <input type="text" name="q">
            <input type="hidden" name="skip" value="0">
            <input type="hidden" name="return" value="20000">
            <input type="submit">
        </form>
    
</div>

<div>
    Page Links:
    <% for(i = 0; i< npage; i++) { %>
    <a href="/project4_0/search?q=camera&skip=<%=i*25%>&return=20000"> &nbsp;<b>  <%=i+1%> </b></a>
    <%}%>
</div>
<div id ="Items">
    <table align="center">
        <tr>
            <td><b>Item Id</b></td> 
            <td><b>Name</b></td> 
        </tr>
            <% for(i = 0; i<list.size()&&i< 25; i++) { %>
        <tr> 
            <td><a href="/project4_0/item?id=<%=list.get(i) %>"><%=list.get(i) %></a> </td> 
            <td> <%=list2.get(i) %> </td>
        </tr>

            <%}%>
        
            
    </table>
</div>
    </div>
</body>
</html>