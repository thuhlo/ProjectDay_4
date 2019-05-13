Feature: Transfer Artist and Album data from Chinook to JSON db

  As a user, I want to read data from the Chinook database, validate it and post it to the
  JSON db, so that I can manage it from JSon database

  Scenario: Transfer Data from Chinook to JSON db
    Given The JSON db and Chinook db
    When I read Artist data from Chinook
    And extract the albums for the artist
    And transfer data from chinook to JSON db
    And Delete data from Chinook
    Then the transfer and delete are both succesful