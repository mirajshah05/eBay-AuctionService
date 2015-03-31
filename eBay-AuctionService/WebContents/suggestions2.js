
/**
 * Provides suggestions for state names (USA).
 * @class
 * @scope public
 */
function StateSuggestions() {
    this.states = [
        "Alabama", "Alaska", "Arizona", "Arkansas",
        "California", "Colorado", "Connecticut",
        "Delaware", "Florida", "Georgia", "Hawaii",
        "Idaho", "Illinois", "Indiana", "Iowa",
        "Kansas", "Kentucky", "Louisiana",
        "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", 
        "Mississippi", "Missouri", "Montana",
        "Nebraska", "Nevada", "New Hampshire", "New Mexico", "New York",
        "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", 
        "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
        "Tennessee", "Texas", "Utah", "Vermont", "Virginia", 
        "Washington", "West Virginia", "Wisconsin", "Wyoming"  
    ];
    //this.states = new Array();
}

/**
 * Request suggestions for the given autosuggest control. 
 * @scope protected
 * @param oAutoSuggestControl The autosuggest control to provide suggestions for.
 */
StateSuggestions.prototype.requestSuggestions = function (oAutoSuggestControl /*:AutoSuggestControl*/,
                                                          bTypeAhead /*:boolean*/) {
    var aSuggestions = [];
    var sTextboxValue = oAutoSuggestControl.textbox.value;
    var input = sTextboxValue;
    
    
    
    if (sTextboxValue.length > 0){
        if (window.XMLHttpRequest)
        {
            xmlhttp=new XMLHttpRequest();       
        }
        else
        {
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function()
        {
             if (xmlhttp.readyState==4 && xmlhttp.status==200)
             {
                     xmlDoc=xmlhttp.responseXML;
                     txt="";                   
                     xyz=xmlDoc.getElementsByTagName("suggestion");
                     for (var i=0;i<xyz.length;i++)
                     {               
                         //var temp = new Array();
                         //this.states[i] = new Array();
                         if (xyz[i].getAttribute("data").indexOf(sTextboxValue) == 0) {
                            aSuggestions.push(xyz[i].getAttribute("data"));
                        } 
                        //this.states.push( xyz[i].getAttribute("data"));  
                     }
                     oAutoSuggestControl.autosuggest(aSuggestions, bTypeAhead);
                    /*document.getElementById("result").innerHTML=txt;
                    for (var i=0; i < this.states.length; i++) { 
                        if (this.states[i].indexOf(sTextboxValue) == 0) {
                            aSuggestions.push(this.states[i]);
                        } 
                    }*/
             }
        }
        xmlhttp.open("GET","suggest?keyword_entered="+input,true);
        xmlhttp.send(); 
    
        //search for matching states
        
    }

    //provide suggestions to the control
    
};