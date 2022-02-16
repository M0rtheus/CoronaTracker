## Built With

* JAVA
* Spring Boot
* Hibernate
* MySQL
* Apache Commons CSV Library
* IntelliJ

<p align="right">(<a href="#top">back to top</a>)</p>

## About the project

This application shows the statistics of Coronavirus cases around the globe.
The CSV data is pulled from https://github.com/CSSEGISandData/COVID-19 using HTTPClient and then parsed using Apache Commons CSV Library. The initial data is saved in the database using hibernate for easier manipulation of data.
The data is represented as a table in the home page. The separate rows are clickable and lead to a separate page with a js chart that shows the particular country's change of total covid cases through time.

<p align="right">(<a href="#top">back to top</a>)</p>

## Screenshots

<p float="left">
<img src="https://github.com/M0rtheus/CoronaTracker/blob/master/images/home.png">
<img src="https://github.com/M0rtheus/CoronaTracker/blob/master/images/countries.png">
</p>

