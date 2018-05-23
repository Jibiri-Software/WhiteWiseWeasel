# Requires MySQL 15.1

## Importing the database into a system

1. Launch mysql and `create database wwwDatabase;`
2. Download the wwwDatabaseDump.sql file
3. Run the next command from the terminal `mysql -u root -p wwwDatabase < /path/to/wwwDatabaseDump.sql`

In windows you need to run the mysql commands from the bin folder inside your mysql installation folder. i.e. `C:/Program Files/MySQL/MySQL Server 15.1/bin`

![Pic](/tables/tables.png)
