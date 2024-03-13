###Basic Database system in Java with heavy use of reflection.

Implementation is with tables meant for a Database that could be used in a Pet shop, but the underlying systems don't rely on this and creating other databases using this is just a matter of creating simple java classes and a matching MySQL database structure.\
Only types of databases implemented right now is "List", which is In-Memory and uses ArrayLists to store data, "MySQL" which connects to a MySQL server for persistence and SQL queries, and "ListSQL", which copies all data from a MySQL server and stores it in an in-memory list database (to avoid deleting prod).\
Can use a CLI or HTTP requests to interface with databases.\
\
User manual and (almost) complete documentation is in Petshop.pdf
