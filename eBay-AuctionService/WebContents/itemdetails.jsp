<%-- 
    Document   : itemdetails
    Created on : Feb 19, 2015, 7:53:11 PM
    Author     : miraj
--%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Arrays"%>
<%@page import="edu.ucla.cs.cs144.Bid"%>
<%@page import="edu.ucla.cs.cs144.Item_interf"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="styling.css">
         <style type="text/css"> 
         html { height: 100% } 
         body { height: 100%; margin: 0px; padding: 0px } 
        #map_canvas {  z-index: 250;min-height: 300px; min-width: 150px;  padding-top: 20px;} 
        #forming {margin-left: 10px;}
        </style> 
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=false"></script>
        <script>
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
        
        
        <script>
        var geocoder;
        var map;
        var address;
        var mapOptions;
        var nomark=0;
        function initialize() {
          
          geocoder = new google.maps.Geocoder();
          var latlng = new google.maps.LatLng(-34.397, 150.644);
          mapOptions = {
            zoom: 14,
            center: latlng
          }
          map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
          
          address = document.getElementById('itemloc').value;
          
          codeAddress();
        }
        
        function setAllMap(map) {
            for (var i = 0; i < markers.length; i++) {
              markers[i].setMap(map);
            }
            
          }
        function codeAddress() {
          
          geocoder.geocode( { 'address': address}, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
              map.setCenter(results[0].geometry.location);
              if(nomark==0){
                  var marker = new google.maps.Marker({
                  map: map,
                  position: results[0].geometry.location
                });
              }
              
            } 
            else {
             initialize2();
            }
          });
        }
        function initialize2(){
            geocoder = new google.maps.Geocoder();
            var latlng = new google.maps.LatLng(40.097948, -101.026328);
            mapOptions = {
            zoom: 0,
            center: latlng
          }
          map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
          address = "USA";
          nomark=1;
          codeAddress();
          nomark=0;
        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
    </head>
    <!-- Item_interf item = (Item_interf)request.getAttribute("xmldata"); -->
    <body>
         <%
        out.println("<br>");
         
		Item_interf item = (Item_interf)session.getAttribute("xmldata");
		
        if(item==null) {
            %>
            <div class="divWholePage">
                <div class="divSection">
                    <div class="header" style="font-size: 24px; color: appworkspace;padding-top: 10%">
                         <a href="keywordSearch.html" style="background-color:rgba(115, 112, 201, 0.6);">Home</a>
                         <a href="getItem.html" style="background-color:rgba(115, 112, 201, 0.6);">Item Search</a>
                    </div>
                    <div class="displayleft tsearch" id="forming">
                        <form action="item">
                            <input type="text" name="id" id="txt1" style="-webkit-border-radius: 20px; 
       -moz-border-radius: 20px;">
                            <input type="submit"> <br><br>
                           <input type="hidden" id="itemloc">
                           <input type="hidden" id="itemcon">
                       </form>
                    </div>
                    <div class="displayright">
                        <span style="padding-top: 25%;padding-left: 505"><b>No Item Found!! Please search for another Item or return back to Home Page</b></span>
                    </div>                 
                    
                </div>
            </div>
        
        <%
            
        }
        else{
        Bid bidder = new Bid();
        %>
         <div class="divWholePage">
			
			<div class="divSection">
				<div class="divBackgroundImage" style="">
                                
                                </div>
                                
				<div class="divRandomText">
                                    <div class="displayleft tsearch" id="forming">
                                        <form action="item" onsubmit="return check()">
                                             <input type="text" name="id" id="txt1" style="-webkit-border-radius: 20px; 
                        -moz-border-radius: 20px;">
                                             <input type="submit"> <br><br>
                                            <input type="hidden" value="<%=item.Item_Location_content%>" id="itemloc">
                                            <input type="hidden" value="<%=item.Item_Country_content%>" id="itemcon">
                                        </form>
                                        <div id="map_canvas" style="width:100%; height:100%"></div> 
                                    </div>
                                    <div class="displayright">
                                        <div class="header" style="font-size: 24px; color: appworkspace;margin-left: -20%">
                                        <a href="keywordSearch.html" style="background-color:rgba(115, 112, 201, 0.6);">Home</a>
                                        <a href="getItem.html" style="background-color:rgba(115, 112, 201, 0.6);">Item Search</a>
                                    </div>
                                        <div class="itemlist">
                                             
                                            <table border="1" cellpadding="5" style="max-width: 70%; font-size: 16px" class="tabledisplay">
                                            <caption><h2>List of Items</h2></caption>
                                            <tr>

                                            <!--<c:forEach var="user" items="${listUsers.rows}">!-->

                                                    <td>Item Id</td>
                                                    <td><%=item.item_id%> </td>
                                            </tr>
                                            <tr>
                                                <td>Name </td>
                                                <td><%=item.Name_content%></td>                    
                                            </tr>
                                            <tr>
                                                <td>Current Price</td>
                                                <td><%=item.Currently_content%></td>    
                                            </tr>
                                            <tr>
                                                <td>Number of bids</td>
                                                <td><%=item.Number_of_Bids_content%></td>    
                                            </tr>
                                            <tr>
                                                <td>Location</td>
                                                <td><%=item.Item_Location_content%></td>    
                                            </tr>
                                            <tr>
                                                <td>Country</td>
                                                <td><%=item.Item_Country_content%></td>    
                                            </tr>
                                            <tr>
                                                <td>Seller</td>
                                                <td><%=item.Item_Seller_UserID%></td>    
                                            </tr>
                                            <tr>
                                                <td>First Bid</td>
                                                <td><% if(item.First_Bid_content.isEmpty()||item.First_Bid_content==null||item.First_Bid_content.equals(null)){out.print("-");}else{out.print(item.First_Bid_content);} %></td>    
                                            </tr>
                                            
                                                <td>Buy Price</td>
                                                <td>
                                                   
												<%
												if(item.BuyPrice_content==null)
												{
												out.print("-");
												//out.print(item.BuyPrice_content);
												}
												else
												{
												out.print(item.BuyPrice_content);
												session.setAttribute("ItemID", item.item_id);
												session.setAttribute("ItemName", item.Name_content);
												session.setAttribute("BuyPrice", item.BuyPrice_content);
												String name1 = request.getServletContext().getContextPath();
                                                                                                String sname1 = request.getServerName();
                                                                                                
                                                                                                %>
                                                                                                 <%//String name = "https://"+sname1+":8443"+name1+"/credit_card.jsp";%>
                                                                                                
                                                                                                
                                                                                                <form action="credit_card.jsp" method="post">
                                                                                                <input type="submit" value="Pay Now"> 
                                                                                                </form>
												<%
												}
												%>
    
                                                </td>
                                            <tr>
                                                <td>Seller</td>
                                                <td><%=item.Item_Seller_UserID %></td>    
                                            </tr>
                                            <tr>
                                                <td>Bid Start Time</td>
                                                <td><%=item.Item_Started_content %></td>    
                                            </tr>
                                            <tr>
                                                <td>Bid End Time</td>
                                                <td><%=item.Item_Ends_content %></td>    
                                            </tr>
                                            <tr>
                                                <td>Description</td>
                                                <td><%=item.Item_Description_content %></td>    
                                            </tr>
                                            <!--</c:forEach>!-->
                                            </table>
                                            
                                            <% if(Integer.parseInt(item.Number_of_Bids_content)>0) {
                                                Arrays.sort(item.bids,new Comparator<Bid>() {
                                                @Override
                                                public int compare(Bid b1, Bid b2) {
                                                    if(b1.Time_content.equals(b2.Time_content)) return 0;
                                                    try {
                                                        SimpleDateFormat format_in = new SimpleDateFormat("MMM-dd-yy HH:mm:ss");
                                                        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                        Date bidTime1 = format_in.parse(b1.Time_content);
                                                        Date bidTime2 = format_in.parse(b2.Time_content);
                                                        
                                                         if(bidTime1.after(bidTime2)) return -1;
                                                        return 1;
                                                    } catch (ParseException ex) {
                                                        ex.printStackTrace();
                                                       // ErrorDispatcher.redirectOnError();
                                                    }               
                                                    return 0;
                                                }
                                            });
                                            %>
                                            
                                            <table cellpadding="5" style="max-width: 70%; font-size: 16px" class="tabledisplay flat-table">
                                            <caption><h2>Bidder Information</h2></caption>
                                            
                                            

                                                <% for(int i = 0; i <Integer.parseInt(item.Number_of_Bids_content) ; i+=1) { %>
                                                <%bidder=item.bids[i];%>
                                                <th style="background-color:rgba(255,0,0,0.5);">
                                                    Bidder <%=i+1%> Information
                                                </th>
                                                <th></th>
                                                <tr>
                                                    <td>Bidder Name:</td>
                                                    <td><%=bidder.UserID %></td>
                                                </tr>
                                                <tr>
                                                    <td>Bidder Rating:</td>
                                                    <td><%=bidder.Rating %> </td>
                                                </tr>
                                                <tr>
                                                    <td>Location:</td>
                                                    <td><%if(item.Bidder_Location_content[i]==null){out.print("-");}else{out.print(bidder.Bidder_Location_content);} %></td>
                                                </tr>
                                                <tr>
                                                    <td>Country:</td>
                                                    <td><%if(item.Bidder_Country_content[i]==null){out.print("-");}else{out.print(bidder.Bidder_Country_content);} %></td>
                                                </tr>
                                                <tr>
                                                    <td>Time:</td>
                                                    <td><%=bidder.Time_content %></td>
                                                </tr>
                                                <tr>
                                                    <td>Amount:</td>
                                                    <td><%=bidder.Amount_content %></td>
                                                </tr>
                                                <%}%>
                                            </table>
                                                <% }%>
                                        </div>
                                    </div>
                                </div>
                                       
                        </div>
        
        
         </div>
        <%}%>
        
    </body>
</html>
