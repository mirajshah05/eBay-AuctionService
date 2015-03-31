TopCoders
Miraj Shah 704406347
Shraddha Chitalia 204426357

Q1: For which communication(s) do you use the SSL encryption? If you are encrypting the communication from (1) to (2) in Figure 2, for example, write (1)?(2) in your answer.
SSL Connection is used to encrypt the communication to prevent eavesdropping of sensitive information and to prevent replay attack. But SSL connection is very expensive so it is used only when passing sensitive information like credit card number.
The credit card information is passed on after link (4) so it is encrypted and all communication after that is secured. 

(4) -> (5) -> (6)

Q2: How do you ensure that the item was purchased exactly at the Buy_Price of that particular item?
The item information can be passed through url or stored in cookie or stored in session. We used session to store the item details information. 
This helps us protect from client-side manipulation of data.
Also, the user is not able to manipulate the item price information or any item details since its stored in session, visible only to browser 
and not prone to manipulation by hacker or user or eavesdropper.
URL information passing is very insecure and easily manipulative by user since he can change the price of item easily though url rewriting