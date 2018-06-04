package scamChecker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import scamChecker.BDController.Columns;
import scamChecker.AbstractBDController.ExceptionInjection;

public class GUIController implements ActionListener {
	
	private BDController bd;
	private MainScreen ms;
	private ScamAddScreen sas;
	private int totalScams;
	
	
	public GUIController(BDController bdc, MainScreen view) {
		bd = bdc;
		ms = view;
		
		String[][] data = bd.allData();
		totalScams = data[0].length;
		printResults(data);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
				
		try {
		
			boolean desc, asc, author;
			
			if (event.getActionCommand().equals("CONTENT")) {
				
				ms.clearOrderOptions();
				
			} else if (event.getActionCommand().equals("SEARCH")) {
				
				ms.clearScreen();
				asc = ms.orderAsc();
				desc = ms.orderDesc();
				author = ms.searchOption();
				ms.clearOrderOptions();
				String msg = ms.getMessage();
				
				long start;
				long end;
				
				
				String[][] data;
				if (author) {
					start = new Date().getTime();			
					data = bd.searchForAuthor(msg, desc);
					end = new Date().getTime();
				} else if (!msg.equals("")) {
					start = new Date().getTime();
					data = bd.searchForCoincidence(msg, desc, asc);
					end = new Date().getTime();
				} else {
					start = new Date().getTime();
					data = bd.allData();
					end = new Date().getTime();
				}
				
				printResults(data);
				ms.setResultLabel(data[0].length, totalScams, end - start);
				
			} else if (event.getActionCommand().equals("NEWSCAM")) {	
			
				sas = new ScamAddScreen();
				sas.Controller(this);
				
			} else if (event.getActionCommand().equals("ADDSCAM")) {
				
				
				String[] args = new String[4];
				
				args[0] = sas.getUser();
				args[1] = sas.gettheTitle();
				args[2] = sas.getDescription();
				args[3] = sas.getUrl();
				
				sas.printMessage(bd.addScam(args));				
			}
			
		} catch (ExceptionInjection e) {
			
			ms.printScreen(e.getMessage());
			
		}
		
	}

	private void printResults(String[][] results) {
		
		for(int i = 0; i < results[0].length; i++) {
			ms.printScreen(results[Columns.TITLE.ordinal()][i]);
			ms.printScreen(results[Columns.USERNAME.ordinal()][i] + "\t" + results[Columns.DATE.ordinal()][i]);
			ms.printScreen(results[Columns.DESCRIPTION.ordinal()][i]);
			ms.printScreen(results[Columns.URL.ordinal()][i]);
			ms.printScreen("-------------------------");
		}
		
	}
	
	
	
	
}
