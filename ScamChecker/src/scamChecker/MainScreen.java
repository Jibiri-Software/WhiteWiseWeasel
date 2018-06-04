package scamChecker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainScreen extends JFrame {
	
	public enum Fields { TITLE, DATE, DESCRIPTION, URL }
	
	private static final long serialVersionUID = 1L;
	
	private ButtonGroup selectSearch = new ButtonGroup();
	private JRadioButton content = new JRadioButton("Content");
	private JRadioButton author = new JRadioButton("Author");
	private JLabel searchSelectLabel = new JLabel("Search by: ");
	
	private JTextField searchBox = new JTextField(35);
	private JLabel searchBoxLabel = new JLabel("Introduce your search:");
	
	private JButton addScam = new JButton("Add new scam");
	
	private ButtonGroup selectOrder = new ButtonGroup();
	private JRadioButton asc = new JRadioButton("Ascendent");
	private JRadioButton desc = new JRadioButton("Descendent");
	private JLabel orderLabel = new JLabel("Order by date:");
		
	private JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JTextArea results = new JTextArea(25,50);
	
	private JLabel labelResults = new JLabel();
		
	public MainScreen() {
		
		// TOP
		
		JPanel top = new JPanel(new BorderLayout(15, 0));
		 
		selectSearch.add(content);
		selectSearch.add(author);
		content.setSelected(true);
		JPanel left = new JPanel(new GridLayout(3,1));
		left.add(searchSelectLabel);
		left.add(content);
		left.add(author);	
		
		
		JPanel centerTopLeft = new JPanel(new FlowLayout());
		centerTopLeft.add(searchBoxLabel);	
		JPanel centerTopRight = new JPanel(new FlowLayout());
		centerTopRight.add(addScam);
		
		JPanel centerTop = new JPanel(new BorderLayout());
		centerTop.add(centerTopLeft, BorderLayout.LINE_START);
		centerTop.add(centerTopRight, BorderLayout.LINE_END);		
		JPanel centerBottom = new JPanel(new FlowLayout());
		centerBottom.add(searchBox);
		
		JPanel center = new JPanel(new GridLayout(2,1));
		center.add(centerTop);
		center.add(centerBottom);
		
		selectOrder.add(asc);
		selectOrder.add(desc);
		JPanel right = new JPanel(new GridLayout(3,1));
		right.add(orderLabel);
		right.add(asc);
		right.add(desc);
		
		
		top.add(left, BorderLayout.LINE_START);
		top.add(center, BorderLayout.CENTER);
		top.add(right, BorderLayout.LINE_END);
		
		//CENTER
		
		results.setEditable(false);
		results.setLineWrap(true);
		scroll.setViewportView(results);
		
		
		// BOTTOM
		
		JPanel bottom = new JPanel(new FlowLayout());
		bottom.add(labelResults);
		
		
		// CREATE LAYOUT
		
		setIconImage(new ImageIcon("icon.jpg").getImage());
		setTitle("Jibiri Scam Checker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		setLayout(new BorderLayout(5,5));
		add(top, BorderLayout.PAGE_START);
		add(scroll, BorderLayout.CENTER);
		add(bottom, BorderLayout.PAGE_END);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);	
		
	}
	
	public void Controller(ActionListener ctr) {
		
		searchBox.addActionListener(ctr);
		searchBox.setActionCommand("SEARCH");
		
		content.addActionListener(ctr);
		content.setActionCommand("CONTENT");
		
		addScam.addActionListener(ctr);
		addScam.setActionCommand("NEWSCAM");
	}
	
	public String getMessage() {
		return searchBox.getText();
	}
	
	public boolean orderAsc() {
		return asc.isSelected();
	}
	
	public boolean orderDesc() {
		return desc.isSelected();
	}
	
	public boolean searchOption() {
		return author.isSelected();
	}
	
	public void printScreen(String msg) {
		results.append(msg + "\n");
	}

	public void clearScreen() {
		results.setText(null);
	}
	public void clearOrderOptions() {
		selectOrder.clearSelection();
	}
	public void setResultLabel(int results, int total, long time) {
		labelResults.setText(results + " scams found out of " + total + " registered (" + time + " milliseconds)");
	}
}
