/* CS144
 *
 * Parser skeleton for processing item-???.xml files. Must be compiled in
 * JDK 1.5 or above.
 *
 * Instructions:
 *
 * This program processes all files passed on the command line (to parse
 * an entire diectory, type "java MyParser myFiles/*.xml" at the shell).
 *
 * At the point noted below, an individual XML file has been parsed into a
 * DOM Document node. You should fill in code to process the node. Java's
 * interface for the Document Object Model (DOM) is in package
 * org.w3c.dom. The documentation is available online at
 *
 * http://java.sun.com/j2se/1.5.0/docs/api/index.html
 *
 * A tutorial of Java's XML Parsing can be found at:
 *
 * http://java.sun.com/webservices/jaxp/
 *
 * Some auxiliary methods have been written for you. You may find them
 * useful.
 */

package edu.ucla.cs.cs144;



import java.io.*;
import java.text.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;


class MyParser 
{
    static Item_interf iteminfo = new Item_interf();
    MyParser(){
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(true);      
            builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new MyErrorHandler());
            //File f = new File(xml);
            
            //processFile(xml);
            
        }
        catch (FactoryConfigurationError e) {
            System.out.println("unable to get a document builder factory");
            System.exit(2);
        } 
        catch (ParserConfigurationException e) {
            System.out.println("parser was unable to be configured");
            System.exit(2);
        }
    }
    
    static final String columnSeparator = "|*|";
    static DocumentBuilder builder;   
    static final String[] typeName = {
	"none",
	"Element",
	"Attr",
	"Text",
	"CDATA",
	"EntityRef",
	"Entity",
	"ProcInstr",
	"Comment",
	"Document",
	"DocType",
	"DocFragment",
	"Notation",
    };
    /*private static String item_id;
    private static String Name_content;
    private static String Category_content[];
    private static String Currently_content;
    private static String BuyPrice_content="\\N";
    private static String First_Bid_content;
    private static String Number_of_Bids_content;
    private static String UserID;
    private static String Rating;
    private static String Bidder_Location_content="\\N";
    private static String Bidder_Country_content="\\N";
    private static String Time_content;
    private static String Amount_content;
    private static String Item_Location_content;
    private static String Item_Loc_Latitude="\\N";;
    private static String Item_Loc_Longitude="\\N";;
    private static String Item_Country_content;
    private static String Item_Started_content;
    private static String Item_Ends_content;
    private static String Item_Seller_UserID;
    private static String Item_Seller_Rating;
    private static String Item_Description_content_old;
    private static String Item_Description_content;*/
    private static Element Category[];
    private static File file;
    private static FileWriter writer;
    
    static class MyErrorHandler implements ErrorHandler {
        
        public void warning(SAXParseException exception)
        throws SAXException {
            fatalError(exception);
        }
        
        public void error(SAXParseException exception)
        throws SAXException {
            fatalError(exception);
        }
        
        public void fatalError(SAXParseException exception)
        throws SAXException {
            exception.printStackTrace();
            System.out.println("There should be no errors " +
                               "in the supplied XML files.");
            System.exit(3);
        }
        
    }
    
    /* Non-recursive (NR) version of Node.getElementsByTagName(...)
     */
    static Element[] getElementsByTagNameNR(Element e, String tagName) {
        Vector< Element > elements = new Vector< Element >();
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
            {
                elements.add( (Element)child );
            }
            child = child.getNextSibling();
        }
        Element[] result = new Element[elements.size()];
        elements.copyInto(result);
        return result;
    }
    
    /* Returns the first subelement of e matching the given tagName, or
     * null if one does not exist. NR means Non-Recursive.
     */
    static Element getElementByTagNameNR(Element e, String tagName) {
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
                return (Element) child;
            child = child.getNextSibling();
        }
        return null;
    }
    
    /* Returns the text associated with the given element (which must have
     * type #PCDATA) as child, or "" if it contains no text.
     */
    static String getElementText(Element e) {
        if (e.getChildNodes().getLength() == 1) {
            Text elementText = (Text) e.getFirstChild();
            return elementText.getNodeValue();
        }
        else
            return "";
    }
    
    /* Returns the text (#PCDATA) associated with the first subelement X
     * of e with the given tagName. If no such X exists or X contains no
     * text, "" is returned. NR means Non-Recursive.
     */
    static String getElementTextByTagNameNR(Element e, String tagName) {
        Element elem = getElementByTagNameNR(e, tagName);
        if (elem != null)
            return getElementText(elem);
        else
            return "";
    }
    
    /* Returns the amount (in XXXXX.xx format) denoted by a money-string
     * like $3,453.23. Returns the input if the input is an empty string.
     */
    static String strip(String money) {
        if (money.equals(""))
            return money;
        else {
            double am = 0.0;
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
            try { am = nf.parse(money).doubleValue(); }
            catch (ParseException e) {
                System.out.println("This method should work for all " +
                                   "money values you find in our data.");
                System.exit(20);
            }
            nf.setGroupingUsed(false);
            return nf.format(am).substring(1);
        }
    }
    
    /* Process one items-???.xml file.
     */
    
    
     private static String timestamp(String date)
    {
            SimpleDateFormat format_in = new SimpleDateFormat("MMM-dd-yy HH:mm:ss");
            
            SimpleDateFormat format_out = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           
            StringBuffer buffer = new StringBuffer();
               
            try
            {
            	Date parsedDate = format_in.parse(date);
            
            	return "" + format_out.format(parsedDate);
            }
            catch(ParseException pe) {
                System.err.println("Parse error");
                return "Parse error";
            }
    }
    
    
    static Item_interf processFile(String xmlFile) {
        Document doc = null;
        
        try {
            doc = builder.parse(new InputSource(new StringReader( xmlFile )));
            /*TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
        		
            StringWriter s1 = new StringWriter();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult s = new StreamResult(s1);
            transformer.transform(source, s);*/
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(3);
        }
        
        
        /* At this point 'doc' contains a DOM representation of an 'Items' XML
         * file. Use doc.getDocumentElement() to get the root Element. */
        //System.out.println("Successfully parsed - " + xmlFile);
        
        /* Fill in code here (you will probably need to write auxiliary
            methods). */
        try
        {
            Element item=doc.getDocumentElement();
            //Element item[]=MyParser.getElementsByTagNameNR(root,"Item");
            int i=0;
            //System.out.println("Length "+item.length);
                String a =item.getAttribute("ItemID");
                if(a==null||a.length()<1)
                    return null;
                iteminfo.item_id=item.getAttribute("ItemID");
                
                 //System.out.println(" Item ID : " +iteminfo.item_id); 

                Element Name=MyParser.getElementByTagNameNR(item,"Name");
                iteminfo.Name_content=MyParser.getElementText(Name);

                //System.out.println(" Item Name : " +iteminfo.Name_content); 

                Category=MyParser.getElementsByTagNameNR(item,"Category");
                iteminfo.Category_content = new String[Category.length];
                for(int j=0;j<Category.length;j++)
                {
                iteminfo.Category_content[j]=MyParser.getElementText(Category[j]);                
                // System.out.println(" Category : " +Category_content); 
                }


                Element Currently=MyParser.getElementByTagNameNR(item,"Currently");
                iteminfo.Currently_content=MyParser.getElementText(Currently);
                String temp = iteminfo.Currently_content.toString();
                iteminfo.Currently_content = temp;

                // System.out.println(" Currently " +Currently_content); 

                // System.out.println("here");
                
                if(MyParser.getElementByTagNameNR(item,"Buy_Price")!=null)
                {
                Element BuyPrice=MyParser.getElementByTagNameNR(item,"Buy_Price");
                iteminfo.BuyPrice_content=MyParser.getElementText(BuyPrice);
                //temp = BuyPrice_content.toString();
                //BuyPrice_content = strip(temp);
                 //System.out.println(" BuyPrice : " +iteminfo.BuyPrice_content); 
                }
                

                Element First_Bid=MyParser.getElementByTagNameNR(item,"First_Bid");
                iteminfo.First_Bid_content=MyParser.getElementText(First_Bid);
               // temp = First_Bid_content.toString();
                //First_Bid_content = strip(temp);

                // System.out.println(" First Bid : " +First_Bid_content);

                Element Number_of_Bids=MyParser.getElementByTagNameNR(item,"Number_of_Bids");
                iteminfo.Number_of_Bids_content=MyParser.getElementText(Number_of_Bids);

               // System.out.println(" Number of Bids : " +Number_of_Bids_content);         

                if(!(iteminfo.Number_of_Bids_content.equals("0")))
                {
                Element Bids=MyParser.getElementByTagNameNR(item,"Bids");
                int n = Integer.parseInt(iteminfo.Number_of_Bids_content);
                    Bid b[] = new Bid[n];
                    
                    Element Bid[]=MyParser.getElementsByTagNameNR(Bids,"Bid");
                    boolean Binsert = false;
                    iteminfo.UserID = new String[Bid.length];
                    iteminfo.Rating = new String[Bid.length];
                    iteminfo.Bidder_Location_content = new String[Bid.length];
                    iteminfo.Bidder_Country_content = new String[Bid.length];
                    iteminfo.Amount_content = new String[Bid.length];
                    iteminfo.Time_content = new String[Bid.length];
                    
                    
                    for(int z=0;z<Bid.length;z++)
                    {
                        Bid b1 = new Bid();
                         Element Bidder=MyParser.getElementByTagNameNR(Bid[z],"Bidder");
                        iteminfo.UserID[z]=Bidder.getAttribute("UserID");
                        iteminfo.Rating[z]=Bidder.getAttribute("Rating");
                        b1.UserID=Bidder.getAttribute("UserID");
                        b1.Rating=Bidder.getAttribute("Rating");
                        
                       // System.out.println(" UsreID : " +UserID);         
                        // System.out.println(" Rating : " +Rating); 
                        if(MyParser.getElementByTagNameNR(Bidder,"Location")!=null){
                            Element Bidder_Location=MyParser.getElementByTagNameNR(Bidder,"Location");
                            iteminfo.Bidder_Location_content[z]=MyParser.getElementText(Bidder_Location);
                            b1.Bidder_Location_content=MyParser.getElementText(Bidder_Location);
                           // System.out.println(" Bidder_Location : " +Bidder_Location_content);         
                            Binsert = true;
                            
                            //create_UserTable();
                        }
                        else{
                            iteminfo.Bidder_Location_content[z] = null;
                            b1.Bidder_Location_content = null;
                        }
                        if(MyParser.getElementByTagNameNR(Bidder,"Country")!=null){
                            Element Bidder_Country=MyParser.getElementByTagNameNR(Bidder,"Country");
                            iteminfo.Bidder_Country_content[z]=MyParser.getElementText(Bidder_Country);
                            b1.Bidder_Country_content=MyParser.getElementText(Bidder_Country);
                            Binsert = true;
                            //System.out.println(" Bidder_Country : " +Bidder_Country_content); 
                        }      
                        else{
                            iteminfo.Bidder_Country_content[z] = null;
                            b1.Bidder_Country_content = null;
                        }

                        Element Time=MyParser.getElementByTagNameNR(Bid[z],"Time");    
                        String tim =MyParser.getElementText(Time);
                            try{
                                
                                iteminfo.Time_content[z] = tim; //timestamp(tim);
                                b1.Time_content = tim; //timestamp(tim);
                                
                            }catch(Exception e){   			  
                                    System.out.println("DATE PARSING ERROR");
                            }

                        // System.out.println(" Time Content : " +Time_content);         

                        Element Amount=MyParser.getElementByTagNameNR(Bid[z],"Amount");    
                        iteminfo.Amount_content[z]=MyParser.getElementText(Amount);
                        b1.Amount_content=MyParser.getElementText(Amount);
                        //temp = Amount_content.toString();
                        //Amount_content = strip(temp);

                        // System.out.println(" Amount  : " +Amount_content); 
                        if(Binsert)
                        {
                            //create_BidderlocTable();
                            Binsert = false;
                            //iteminfo.Bidder_Location_content="\\N";
                            //Bidder_Country_content="\\N";
                            
                            
                        }
                        //create_BidderTable();
                        //create_BidItemsTable();
                        b[z] = b1;
                    }
                               

                               

                        iteminfo.bids = b;
                     /* End Of Element -- BID -- */
                 /* End Of Element -- BIDS -- */
                }    

              Element Item_Location=MyParser.getElementByTagNameNR(item,"Location");
              iteminfo.Item_Location_content=MyParser.getElementText(Item_Location);  
              // System.out.println(" Item_Location : " +Item_Location_content);         
              boolean Iinsert = false;
              if(!(Item_Location.getAttribute("Latitude").isEmpty()))
              {
                iteminfo.Item_Loc_Latitude=Item_Location.getAttribute("Latitude");
                Iinsert = true;
                // System.out.println(" Item_Lat : " +Item_Loc_Latitude+"f");         
              }
              
              if(!(Item_Location.getAttribute("Longitude").isEmpty()))
              {
              iteminfo.Item_Loc_Longitude=Item_Location.getAttribute("Longitude");  
              Iinsert = true;
              // System.out.println(" Item_Long : " +Item_Loc_Longitude);   
              }
              
              if(Iinsert)
              {
              //create_ItemLocationTable();
              Iinsert=false;
              iteminfo.Item_Loc_Latitude=null;
              iteminfo.Item_Loc_Latitude=null;
              }

              /* End of Element -- Location ( ITEM ) -- */
              Element Item_Country=MyParser.getElementByTagNameNR(item,"Country");
              iteminfo.Item_Country_content=MyParser.getElementText(Item_Country);  

              // System.out.println(" Item_Country : " +Item_Country_content);         

              Element Item_Started=MyParser.getElementByTagNameNR(item,"Started");
              String st =MyParser.getElementText(Item_Started); 
              iteminfo.Item_Started_content = st; //timestamp(st);
                
              // System.out.println(" Item_Started : " +Item_Started_content);         

              Element Item_Ends=MyParser.getElementByTagNameNR(item,"Ends");
              String end =MyParser.getElementText(Item_Ends);  
              iteminfo.Item_Ends_content = end;// timestamp(end);    

              // System.out.println(" Item_Ends : " +Item_Ends_content); 

              Element Item_Seller=MyParser.getElementByTagNameNR(item,"Seller");
              iteminfo.Item_Seller_UserID=Item_Seller.getAttribute("UserID");
              iteminfo.Item_Seller_Rating=Item_Seller.getAttribute("Rating");

              // System.out.println(" Item_Seller_UserID : " +Item_Seller_UserID); 
              // System.out.println(" Item_Seller_Rating : " +Item_Seller_Rating); 


              Element Item_Description=MyParser.getElementByTagNameNR(item,"Description");
              
              iteminfo.Item_Description_content=MyParser.getElementText(Item_Description);  
              /*
              if(Item_Description_content_old.length() > 4000)
              {
                  Item_Description_content=Item_Description_content_old.substring(0,4000);
              }
              else
              {
                  Item_Description_content=Item_Description_content_old;
              }
              */
              // System.out.println(" Item_Description : " +Item_Description_content); 

              //System.out.println(" "); 
              //create_SellerTable();
              //create_ItemCategoryTable();
              
              //create_ItemsTable();
             
              //i++;
           // end of while
        }
        catch(Exception e)
        {
        }
 
        
        return iteminfo;
       
        /**************************************************************/
        
    }
    
    public static void test (String[] args) {
       /* if (args.length == 0) {
            System.out.println("Usage: java MyParser [file] [file] ...");
            System.exit(1);
        }*/
        String x= "<Items>" +
"  <Item ItemID=\"1494286865\">" +
"    <Name>Fred Astair Follow the Fleet Studio 8x10 1936</Name>" +
"    <Category>Movies &amp; Television</Category>" +
"    <Category>Memorabilia</Category>" +
"    <Category>Movie</Category>" +
"    <Category>Photos</Category>" +
"    <Category>Antique, Vintage (Pre-1940)</Category>" +
"    <Currently>$9.95</Currently>" +
"    <First_Bid>$9.95</First_Bid>" +
"    <Number_of_Bids>0</Number_of_Bids>" +
"    <Bids />" +
"    <Location>Vancouver</Location>" +
"    <Country>Canada</Country>" +
"    <Started>Dec-06-01 19:15:51</Started>" +
"    <Ends>Dec-16-01 19:15:51</Ends>" +
"    <Seller Rating=\"2186\" UserID=\"americanfilms\" />" +
"    <Description>The greatest dancer in the history of Hollywood is captured at the peak of his style. This studio publicity shot glows with the Astair charm.. Photograph is from the original RKO negative and in fine condition. It's much brighter and sharper than the low res scan. Additional wins will ship for free so feel free to browse my classic photographs and rare films. Many hundreds of satsified customers are so thrilled with my original photographs that they say They are the Best on Ebay . Your satisfaction is always 100% guaranteed, no questions, no hassels, so bid with confidence. Paypal always welcome. Thanks for bidding. Chris</Description>\n" +
"  </Item></Items>";
        //MyParser m = new MyParser(x);
        /* Initialize parser. */
        /*try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(true);      
            builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new MyErrorHandler());
        }
        catch (FactoryConfigurationError e) {
            System.out.println("unable to get a document builder factory");
            System.exit(2);
        } 
        catch (ParserConfigurationException e) {
            System.out.println("parser was unable to be configured");
            System.exit(2);
        }*/
        
        /* Process all files listed on command line. */
        /*for (int i = 0; i < args.length; i++) {
            File currentFile = new File(args[i]);
            processFile(currentFile);
        }*/
    }
}