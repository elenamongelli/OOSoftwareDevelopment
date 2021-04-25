Feature: Monitor internal status of container
 
 Background: 
    Given logistic company "Mærsk" have a client "Netto"
    And the client have a container going from from "Copenhagen" to "New York"
  
  
  Scenario Outline: Successfull reading of temperature
    When the container is reading a temperature of <temp> C°
    Then display the temperature of <temp> C°

    Examples: 
      | temp |
      |   25 |
      |   -1 |
      | 10.5 |

  Scenario Outline: Display history of internal measurments
    Given the container have a history of <temp1> C°, <temp2> C°, <temp3> C°, <temp4> C°
    When reading a request for history display
    Then display the history of <temp1> C°, <temp2> C°, <temp3> C°, <temp4> C°

    Examples: 
      | temp1 | temp2 | temp3 | temp4 |
      |    25 |    10 |     2 |    30 |
      |    -1 |   -10 |    50 |    23 |
      |   1.5 |  26.3 | 23    |     4 |
