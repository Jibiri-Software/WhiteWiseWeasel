package scamChecker;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractBDController {

    public class ExceptionInjection extends Exception {
        private static final long serialVersionUID = 1L;

        public ExceptionInjection(String msg) {
            super(msg);
        }
    }


    public abstract void initialize(String user, String Password);
    public abstract String[][] allData();
    public abstract String[][] searchForCoincidence(String message, boolean descendentOrder, boolean ascendentOrder) throws ExceptionInjection;


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
}
