

<html>
<head>
	<title>
	Confirmation Page
	</title>
     <link rel="stylesheet" type="text/css" href="styling.css">
</head>
<body>
    <div class="divWholePage">
			
			<div class="divSection">
				<div class="divBackgroundImage" style="">
                                
                                </div>
                           
				<div class="divRandomText">
                                    <%
                                        if(request.isSecure()){
                                        String name1 = request.getServletContext().getContextPath();
                                        String sname1 = request.getServerName();
                                        
                                        %>
                                        <%String name = "http://"+sname1+":1448"+name1+"/";%>
                                        <%String getItem = "http://"+sname1+":1448"+name1+"/";%>

                                     <div class="header" style="font-size: 24px; color: appworkspace;">
                                        <a href="<%=name%>" style="background-color:rgba(115, 112, 201, 0.6);">Home</a>
                                        <a href="<%=getItem%>getItem.html" style="background-color:rgba(115, 112, 201, 0.6);">Item Search</a>
                                        
                                    </div>
                                    <div class="displayleft ">
                                        
                                         
                                        
                                    </div>	
                                    
                                        <div id ="Items" class="displayright tsearch" style="font-size: 20px; padding-left: 20%;">
                                        <%

                                        String item_id = (String)session.getAttribute("ItemID");
                                        String item_name = (String)session.getAttribute("ItemName");
                                        String buy_price = (String)session.getAttribute("BuyPrice");
                                        String card_number = (String)session.getAttribute("credit_card_no");
                                        String time_tran = (String) session.getAttribute("time_transaction");
                                        %>
                                        ITEM ID : <%=item_id%>
                                        <br />
                                        ITEM NAME : <%=item_name%>
                                        <br />
                                        BUY PRICE : <%=buy_price%>
                                        <br />
                                        CREDIT CARD : <%=card_number%>
                                        <br>
                                        TRANSACTION TIME : <%=time_tran%>
                                        
                                    </div>
                                        <%
                                            }
                                            else{
                                        out.println("Illegal Acccess");
                                            }
                                        
                                        %>
                                </div>
                        </div>
    </div>
</body>
</html>