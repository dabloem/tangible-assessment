Assignment ONE
-

Create a Service Layer
=

The person *service* obtains persons from the database (h2).
It provides two services/methods
* Create Person
* Get Filtered Persons

Create Person
-
ALL Persons require a name with at least 2 characters and a birthdate.
Duplicate Person names are **not** allowed. When a Person does not meet the requirement, throw an exception with a message.

Get Filtered Persons
-
Return all Persons which are **not** born in the banned years (see banned-years file).
