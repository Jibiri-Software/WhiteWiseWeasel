package scamChecker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class View extends JFrame {
	
	public enum Fields { TITLE, DATE, DESCRIPTION, URL }
	
	private static final long serialVersionUID = 1L;
	
	private ButtonGroup selectSearch = new ButtonGroup();
	private JRadioButton content = new JRadioButton("Content");
	private JRadioButton author = new JRadioButton("Author");
	private JLabel searchSelectLabel = new JLabel("Search by: ");
	
	private JTextField searchBox = new JTextField(30);
	private JLabel searchBoxLabel = new JLabel("Introduce your search");
	
	private ButtonGroup selectOrder = new ButtonGroup();
	private JRadioButton asc = new JRadioButton("Ascendent");
	private JRadioButton desc = new JRadioButton("Descendent");
	private JLabel orderLabel = new JLabel("Order by date:");
		
	private JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JTextArea results = new JTextArea(25,50);
		
	public View() {
		
		// TOP
		
		JPanel top = new JPanel(new BorderLayout());
		 
		selectSearch.add(content);
		selectSearch.add(author);
		content.setSelected(true);
		JPanel left = new JPanel(new GridLayout(3,1));
		left.add(searchSelectLabel);
		left.add(content);
		left.add(author);
		
		JPanel center = new JPanel(new GridLayout(2,1));
		JPanel topCenter = new JPanel(new FlowLayout());
		topCenter.add(searchBoxLabel);
		JPanel bottomCenter = new JPanel(new FlowLayout());
		bottomCenter.add(searchBox);
		center.add(topCenter);
		center.add(bottomCenter);
		
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

		
		
		// CREATE LAYOUT
		
		setIconImage(new ImageIcon("icon.jpg").getImage());
		setTitle("Jibiri Scam Checker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		setLayout(new BorderLayout());
		add(top, BorderLayout.PAGE_START);
		add(scroll, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);	
		
	}
	
	public void Controller(ActionListener ctr) {
		
		searchBox.addActionListener(ctr);
		searchBox.setActionCommand("SEARCH");
		
		content.addActionListener(ctr);
		content.setActionCommand("CONTENT");
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
}
