/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucla.cs.cs144;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author miraj
 */
public class Item_interf {

    static void sort(Comparator<Item_interf> comparator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String item_id;
    public String Name_content;
    public String Category_content[];
    public String Currently_content;
    public String BuyPrice_content=null;
    public String First_Bid_content;
    public String Number_of_Bids_content;
    public String[] UserID;
    public String[] Rating;
    public String[] Bidder_Location_content=null;
    public String[] Bidder_Country_content=null;
    public String[] Time_content;
    public String[] Amount_content;
    public String Item_Location_content;
    public String Item_Loc_Latitude=null;;
    public String Item_Loc_Longitude=null;;
    public String Item_Country_content;
    public String Item_Started_content;
    public String Item_Ends_content;
    public String Item_Seller_UserID;
    public String Item_Seller_Rating;
    public String Item_Description_content_old;
    public String Item_Description_content;
    public Bid bids[];
        public void sort(){
               Arrays.sort(bids,new Comparator<Bid>() {
                    @Override
                    public int compare(Bid b1, Bid b2) {
                        if(b1.Time_content.equals(b2.Time_content)) return 0;
                        try {
                             SimpleDateFormat format_in = new SimpleDateFormat("MMM-dd-yy HH:mm:ss");
            
            SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date bidTime1 = timestampFormat.parse(b1.Time_content);
                             Date bidTime2 = timestampFormat.parse(b2.Time_content);
                             if(bidTime1.before(bidTime2)) return -1;
                            return 1;
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                           // ErrorDispatcher.redirectOnError();
                        }               
                        return 0;
                    }
                });
        }
    
}

