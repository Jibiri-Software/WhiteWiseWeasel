package scamChecker;


import java.sql.*;



public class BDController extends AbstractBDController{
	
			
	private String url = "jdbc:mysql://localhost:3306/wwwDatabase";
	private String username;	
	private String password;	

		
	public enum Columns { TITLE, USERNAME, DATE, DESCRIPTION, URL }
	

	
	public BDController(String user, String pass) {
		this.username = user;
		this.password = pass;		
		initialize();
	}


	public void initialize() {
		try {
			String connectionUrl = url;
			conn = DriverManager.getConnection(connectionUrl, username, password);
			System.out.println("Conectado.");
		}
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public String[][] allData() {
		
		String query = "select * "
				+ "from scam s, user u "
				+ "where s.idauthor = u.id";
		
		query = addOrder(query, true);
		
		query = query.concat(";");
		
		return retrieveData(query);
	}
	
	public String[][] searchForCoincidence(String message, boolean descendentOrder, boolean ascendentOrder) throws ExceptionInjection {
		
		checkInjection(message);
		
		message = checkExactSearch(message);
		
		String query = "select s.title, u.username, s.description, s.url, s.dateadded, ( "
							+ "select (((length(title) - length(replace(upper(title),'" + message + "','')))/length('" + message + "'))*3 + (((length(description) - length(replace(upper(description),'" + message + "','')))/length('" + message + "')))) as numTimes "
							+ "from scam s2 "
							+ "where s2.id = s.id) as numTimes "
					+ "from scam s, user u "
					+ "where s.idauthor = u.id and (upper(s.title) like '%" + message + "%' or upper(s.description) like '%" + message + "%')";
		
		
		if (ascendentOrder || descendentOrder ) {
			
			query = addOrder(query, descendentOrder);
			
		} else {
			
			query.concat(" order by numTimes ");
			
		}
				
		query = query.concat(";");
		
		return retrieveData(query);
	}
	

	public String[][] searchForAuthor(String user, boolean descendentOrder) throws ExceptionInjection {
		
		checkInjection(user);
		
		String query = "select * "
				+ "from scam s, user u "
				+ "where s.idauthor = u.id and u.username = '" + user + "'";
		
		query = addOrder(query, descendentOrder);
		
		query = query.concat(";");
		
		return retrieveData(query);
	}
	
	private String[][] retrieveData(String query) {
		
		String[][] result = null;
					
		try ( Statement stmt = conn.createStatement() ) {
			
			ResultSet rset = stmt.executeQuery(query);			
			
			int numRows = numberOfRows(rset);
			result = new String[Columns.values().length][numRows];
		
			int i = 0;
			while(rset.next()) {
				result[Columns.USERNAME.ordinal()][i] = rset.getString("username");
				result[Columns.TITLE.ordinal()][i] = rset.getString("title");
				result[Columns.DATE.ordinal()][i] = rset.getString("dateadded");
				result[Columns.DESCRIPTION.ordinal()][i] = rset.getString("description");
				result[Columns.URL.ordinal()][i] = rset.getString("url");
				if (result[Columns.URL.ordinal()][i] == null) result[Columns.URL.ordinal()][i] = "No URL provided";
				i++;
			}
			
		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
		}	
		
		return result;
	}
	

		
}
