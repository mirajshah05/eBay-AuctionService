<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.String"%>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" /> 
    <title>Resultset Items</title>
    <script type="text/javascript" src="autosuggest2.js"></script>
        <script type="text/javascript" src="suggestions2.js"></script>
        <link rel="stylesheet" type="text/css" href="autosuggest.css" />        
        <script type="text/javascript">
            window.onload = function () {
                var oTextbox = new AutoSuggestControl(document.getElementById("txt1"), new StateSuggestions());        
            }
            
            function check()
            {
                var searchtext = document.getElementById("txt1").value;
                if(searchtext=='')
                {
                    //alert('Enter any character');
                    return false;
                }
            }
        </script>     
		<link rel="stylesheet" type="text/css" href="styling.css">
   

</head>
<% 
//creating arraylist object of type category class
List<String> list = (ArrayList<String>) request.getAttribute("item");
List<String> list2 = (ArrayList<String>) request.getAttribute("name");
// Retrieve the query string
String query=String.valueOf(request.getAttribute("query_string"));
String temp = String.valueOf(request.getAttribute("links"));
//out.print("temp="+temp);
int npage = Integer.parseInt(temp);
//int pages = list.size()/25;
int i = 0;
//storing passed value from jsp
String temp2 = String.valueOf(request.getAttribute("tlength"));
int tresults = Integer.parseInt(temp2);
temp2 = String.valueOf(request.getAttribute("start"));
int start = Integer.parseInt(temp2);
//list = request.getAttribute("item");
out.println("<br>");

%>
<body>
    <div class="divWholePage">
			
			<div class="divSection">
				<div class="divBackgroundImage" style="">
                                
                                </div>
                           
				<div class="divRandomText">
                                     <div class="header" style="font-size: 24px; color: appworkspace;">
                                        <a href="keywordSearch.html" style="background-color:rgba(115, 112, 201, 0.6);">Home</a>
                                        <a href="getItem.html" style="background-color:rgba(115, 112, 201, 0.6);">Item Search</a>
                                        
                                    </div>
                                    <div class="displayleft tsearch">
                                        <form action="search" onsubmit="return check()">
                                            <input type="text" name="q" id="txt1" autocomplete="off" class="textbox"/>
                                            <input type="hidden" name="numResultsToSkip" value="0" />
                                            <input type="hidden" name="numResultsToReturn" value="20000" />
                                            <input type="submit" />
                                        </form>
                                         
                                        
                                    </div>	
                                    <div id ="Items" class="displayright ">
                                        <div class="tabledisplay " style="">
                                            <table align="center" style="font-size: 17px;">
                                            <tr>
                                                <td><b>Item Id</b></td> 
                                                <td><b>Name</b></td> 
                                            </tr>
                                            <tr></tr>
                                                <% for(i = 0; i<list.size()&&i< 15; i++) { %>
                                            <tr> 
                                                <td><a href="<%=request.getServletContext().getContextPath()%>/item?id=<%=list.get(i) %>"><%=list.get(i) %></a> </td> 
                                                <td> <%=list2.get(i) %> </td>
                                            </tr>

                                                <%}%>

                                                <tr></tr>
                                        </table>
                                        </div>
                                                
                                                <div class="links">
                                            Total Results: <%=tresults%><br>
                                            <u> <a href="
                                               <%
                                               String name = request.getServletContext().getContextPath();
                                               if(start<1){out.println('#');}
                                               else{out.println(name+"/search?q="+query+"&numResultsToSkip="+(start-15)+"&numResultsToReturn="+tresults);}
                                               %>
                                               ">
                                                    Prev</a> </u>
                                                 
                                            
                                            <% for(i = 0; i< npage; i++) { %>
                                            <a href="<%=name%>/search?q=<%=query%>&numResultsToSkip=<%=i*15%>&numResultsToReturn=<%=tresults%>"> &nbsp;<b>  <%=i+1%> </b></a>
                                            <%}%>
                                            <u>
                                            <a href="
                                               <%if(start+15>(tresults)){out.println('#');}
                                               else{out.println(name+"/search?q="+query+"&numResultsToSkip="+(start+15)+"&numResultsToReturn="+tresults);}
                                               %>
                                               ">
                                                Next</a> </u>
                                        </div>
                                                
                                    </div> 
									
				</div>
			</div>
			

    
    
   
<div>
    
    
</div>


    </div>
</body>
</html>