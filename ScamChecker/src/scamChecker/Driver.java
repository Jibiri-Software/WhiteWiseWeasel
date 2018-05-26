package scamChecker;

public class Driver {
	
	public static void main(String[] args) {
		
		String userMysql = "root"; //INTRODUCE USER OF LOCAL MYSQL
		String passwordMysql = ""; //INTRODUCE PASSWORD OF LOCAL MYSQL
		
		BDController bd = new BDController(userMysql, passwordMysql);
		View v = new View();
		GUIController ctr = new GUIController(bd, v);
		v.Controller(ctr);		
	}
	
}
