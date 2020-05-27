Assignment ONE
-

Create a Service Layer
=

> - Het F1, type 'Open Workspace' and select /service directory
> - Set the timer to 60 minutes. Press F1 (yes, the most important key) and type ' timer' ;-)

The person *service* obtains persons from the database (h2).
It provides two services/methods
* Create Person
* Get Filtered Persons

The Service and Person classes are already setup.   
Also the pom.xml, but you may change, add, replace, delete anything you want to make it your own java project.

Create Person
-
ALL Persons require a name with at least 2 characters and a birthdate. Duplicate Person names are **not** allowed.    
When a Person does not meet the requirement, throw an exception with a message.

Get Filtered Persons
-
Return all Persons which are **not** born in the banned years (see banned-years file).
