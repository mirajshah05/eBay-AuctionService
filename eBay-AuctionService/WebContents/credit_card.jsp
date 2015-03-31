

<html>
<head>
	<title>
	Credit Card Info Page
	</title>
    <link rel="stylesheet" type="text/css" href="styling.css">
    <script type="text/javascript">
        function check(form){
            var num = form.credit_card_no.value;//document.getElementById("card_num");
            
            if(form.credit_card_no.value!="" && form.credit_card_no.value !=null && length(credit_card_no.value)>4){
                return true;
            }
            else{
                alert("Enter Valid Credit Card Number");
            }
            return false;
        }
    </script>
    
</head>
<body>
	
        
         <div class="divWholePage">
                <div class="divSection">
                    <%
                                        String name1 = request.getServletContext().getContextPath();
                                        String sname1 = request.getServerName();

                                        %>
                                        <%String name = "http://"+sname1+":1448"+name1+"/";%>
                                        <%String getItem = "http://"+sname1+":1448"+name1+"/";%>

                        <div class="header" style="font-size: 24px; color: appworkspace;">
                           <a href="<%=name%>" style="background-color:rgba(115, 112, 201, 0.6);">Home</a>
                           <a href="<%=getItem%>getItem.html" style="background-color:rgba(115, 112, 201, 0.6);">Item Search</a>

                       </div>
                    
                    <div class="displayleft" id="forming">
                        
                    </div>
                    <div class="displayright tsearch" style="padding-left: 20%">
                        <%
                        
                        String item_id = (String)session.getAttribute("ItemID");
                        String item_name = (String)session.getAttribute("ItemName");
                        String buy_price = (String)session.getAttribute("BuyPrice");
                        
                        %>
                                    ITEM ID : <%=item_id%>
                    <br />
                    ITEM NAME : <%=item_name%>
                    <br />
                    BUY PRICE : <%=buy_price%>
                    <br />
                    <%
                    
                    String name_link = "https://"+sname1+":8443"+name1+"/Payment";
                    %>
                    <form method="POST" action="<%=name_link%>" onsubmit="return check(this)">
                          Credit Card Number:<input type="text" name="credit_card_no" id="credit_card_no"></input>
                        <input type="submit"></input>
                    </form>
                 
                    </div>
                </div>
         </div>
</body>
</html>