package scamChecker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractBDController {

    protected Connection conn;



    /*
        This method stablish a connection with the database
        and saves the connection in the Connection conn variable
     */
    public abstract void initialize();

    /*
        Returns a 2D-Array with all information from the database
     */
    public abstract String[][] allData();

    /*
        Returns a 2D-Array with all information from the database that contains @param message
        If both booleans are off it should use a heuristic to order the results, only one of them
        should be set to true at once. This will order the results by date in said order
     */
    public abstract String[][] searchForCoincidence(String message, boolean descendentOrder, boolean ascendentOrder) throws ExceptionInjection;

    /*
        Returns a 2D-Array with all information from the database from @param user
        Can only be ordered by ascendant or descendant by date
     */
    public abstract String[][] searchForAuthor(String user, boolean descendentOrder) throws ExceptionInjection;
    
    
    /*
	    Adds a scam into the database;
	    It must receive 
	    	0 - a user,
	    	1 - a title,
	    	2 - a description
	    	3 - a url 
     */
    
    public abstract String addScam(String[] args);


    protected String checkExactSearch(String s) {

        if (s.startsWith("\"") && s.endsWith("\"")) {
            s = s.replaceAll("\"", " ");
        }

        return s;
    }


    protected String addOrder(String query, boolean descendentOrder) {


        query = query.concat(" order by dateadded");

        if (descendentOrder) {
            query = query.concat(" desc");
        }

        return query;
    }

    protected int numberOfRows(ResultSet rset) {

        int total = 0;
        try {
            rset.last();
            total = rset.getRow();
            rset.beforeFirst();

        } catch (SQLException ex) {

            System.out.println("SQLException: " + ex.getMessage());
        }
        return total;
    }


    protected void checkInjection(String s) throws ExceptionInjection {

        if (s.contains(";")) {
            throw new ExceptionInjection("Sorry, we don't accept searches with character ;");
        }

    }

    /*
        Exception to be thrown if there is any attempt for SQL Injection
    */
    public class ExceptionInjection extends Exception {
        private static final long serialVersionUID = 1L;

        public ExceptionInjection(String msg) {
            super(msg);
        }
    }
}
