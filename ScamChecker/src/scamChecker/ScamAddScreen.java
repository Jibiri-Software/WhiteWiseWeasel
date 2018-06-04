package scamChecker;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ScamAddScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField user = new JTextField(35);
	private JLabel userLabel = new JLabel("*User:");
	
	private JTextField title = new JTextField(35);
	private JLabel titleLabel = new JLabel("*Title:");	
	
	private JTextArea description = new JTextArea(10, 10);
	private JLabel descriptionLabel = new JLabel("Description of the scam");
	
	private JTextField url = new JTextField(35);
	private JLabel urlLabel = new JLabel("Url:");
	private JButton addScam = new JButton("Add new scam");
	
	private JLabel message = new JLabel("");

	public ScamAddScreen () {
		
		//TOP
		
		JPanel toptop = new JPanel(new BorderLayout(15, 5));
		toptop.add(user, BorderLayout.CENTER);
		toptop.add(userLabel, BorderLayout.LINE_START);
		
		JPanel topbottom = new JPanel(new BorderLayout(15, 5));
		topbottom.add(title, BorderLayout.CENTER);
		topbottom.add(titleLabel, BorderLayout.LINE_START);
		
		JPanel top = new JPanel(new BorderLayout(15, 5));	
		top.add(toptop, BorderLayout.PAGE_START);
		top.add(topbottom, BorderLayout.PAGE_END);
		
		
		// CENTER
		JPanel center = new JPanel(new BorderLayout(15, 5));		
		center.add(description, BorderLayout.CENTER);
		center.add(descriptionLabel, BorderLayout.PAGE_START);
		
		//BOTTOM
		/*
		JPanel bottom = new JPanel(new BorderLayout(15, 5));
		bottom.add(url, BorderLayout.CENTER);
		bottom.add(urlLabel, BorderLayout.LINE_START);
		bottom.add(addScam, BorderLayout.PAGE_END);*/
		
		JPanel bottombottom = new JPanel(new BorderLayout(15, 5));
		bottombottom.add(addScam, BorderLayout.PAGE_START);
		bottombottom.add(message, BorderLayout.PAGE_END);
		
		JPanel bottom = new JPanel(new BorderLayout(15, 5));
		bottom.add(url, BorderLayout.CENTER);
		bottom.add(urlLabel, BorderLayout.LINE_START);
		bottom.add(bottombottom, BorderLayout.PAGE_END);
		
		//SET FRAME
		
		setIconImage(new ImageIcon("icon.jpg").getImage());
		setTitle("Jibiri Scam Adder");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLayout(new BorderLayout(15, 5));
		add(top, BorderLayout.PAGE_START);
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.PAGE_END);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void Controller(ActionListener ctr) {
		
		addScam.addActionListener(ctr);
		addScam.setActionCommand("ADDSCAM");
	}
	
	public String getUser() {
		return user.getText();
	}
	
	public String gettheTitle() {
		return title.getText();
	}
	
	public String getDescription() {
		return description.getText();
	}
	
	public String getUrl() {
		return url.getText();
	}
	public void printMessage(String msg) {
		
		message.setText(msg);

	}
}
