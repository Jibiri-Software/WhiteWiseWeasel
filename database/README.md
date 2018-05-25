# Can be imported on any sql database manager

## Importing the database into a system with MySQL

1. Launch mysql and `create database wwwDatabase;`
2. Download the wwwDatabaseDump.sql file
3. Run the next command from the terminal `mysql -u root -p wwwDatabase < /path/to/wwwDatabaseDump.sql`

In windows you need to run the mysql commands from the bin folder inside your mysql installation folder. i.e. `C:/Program Files/MySQL/MySQL Server 15.1/bin`

### Alternative
1. Launch mysql and `create database wwwDatabase;` (only first time) and `use wwwDatabase;`
2. Run the command `source /path/to/wwwDatabaseDump.sql`

![ ](https://github.com/Jibiri-Software/WhiteWiseWeasel/blob/master/database/tables/tables.png?raw=true "Tables on MySQL")
