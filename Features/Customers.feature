Feature: Customers

 Background: These are common steps for each scenario
  Given User launch chrome browser
  When User opens URL "https://admin-demo.nopcommerce.com/login"
  And User enters Email as "admin@yourstore.com" and Password as "admin"
  And User Clicks on Login
  Then User can view Dashboard
  When User clicks on Customers Menu
  And click on customers menu item
 
 @sanity
 Scenario: Add new Customer
  And click on Add new button
  Then User can view Add new customer page
  When User enter customer info
  And click on Save button
  Then User can view confirmation message "The new customer has been added successfully."
  And close browser  
  
  @regression
  Scenario: Search Customer by EmailID
  And Enter Customer email
  And Click on search button
  Then user should get email in the search table
  And close browser  
  
  @regression
  Scenario: Search Customer by EmailID
  And Enter Customer FirstName
  And Enter Customer LastName
  And Click on search button
  Then user should get name in the search table
  And close browser  
  

  
 