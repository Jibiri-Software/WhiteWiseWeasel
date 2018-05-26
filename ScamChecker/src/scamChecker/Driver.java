package scamChecker;

public class Driver {
	
	public static void main(String[] args) {
		
		if (args.length < 2) {
			System.err.println("Pass as parameters MySQL user and password");
			System.exit(1);
		}

		String userMysql = args[0]; //INTRODUCE USER OF LOCAL MYSQL
		String passwordMysql = args[1]; //INTRODUCE PASSWORD OF LOCAL MYSQL
		
		BDController bd = new BDController(userMysql, passwordMysql);
		View v = new View();
		GUIController ctr = new GUIController(bd, v);
		v.Controller(ctr);		
	}
	
}
