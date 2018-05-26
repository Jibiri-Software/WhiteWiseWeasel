package scamChecker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import scamChecker.BDController.Columns;
import scamChecker.BDController.ExceptionInjection;

public class GUIController implements ActionListener {
	
	private BDController bd;
	private View v;
	private int totalScams;
	
	public GUIController(BDController bdc, View view) {
		bd = bdc;
		v = view;
		
		String[][] data = bd.allData();
		totalScams = data[0].length;
		printResults(data);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
				
		try {
		
			boolean desc, asc, author;
			
			if (event.getActionCommand().equals("CONTENT")) {
				
				v.clearOrderOptions();
				
			} else if (event.getActionCommand().equals("SEARCH")) {
				
				v.clearScreen();
				asc = v.orderAsc();
				desc = v.orderDesc();
				author = v.searchOption();
				v.clearOrderOptions();
				String msg = v.getMessage();
				
				String[][] data;
				if (author) {
								
					data = bd.seachForAuthor(msg, desc);
					
				} else if (!msg.equals("")) {
					
					data = bd.searchForCoincidence(msg, desc, asc);
				
				} else {
					
					data = bd.allData();
				}
				
				printResults(data);
				v.setResultLabel(data[0].length, totalScams);
				
			}
			
		} catch (ExceptionInjection e) {
			
			v.printScreen(e.getMessage());
			
		}
		
	}

	private void printResults(String[][] results) {
		
		for(int i = 0; i < results[0].length; i++) {
			v.printScreen(results[Columns.TITLE.ordinal()][i]);
			v.printScreen(results[Columns.USERNAME.ordinal()][i] + "\t" + results[Columns.DATE.ordinal()][i]);
			v.printScreen(results[Columns.DESCRIPTION.ordinal()][i]);
			v.printScreen(results[Columns.URL.ordinal()][i]);
			v.printScreen("-------------------------");
		}
		
	}
	
	
	
	
}
