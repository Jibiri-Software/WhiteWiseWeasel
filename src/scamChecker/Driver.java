package scamChecker;

public class Driver {
	
	public static void main(String[] args) {
		
		BDController bd = new BDController();
		View v = new View();
		GUIController ctr = new GUIController(bd, v);
		v.Controller(ctr);		
	}
	
}
