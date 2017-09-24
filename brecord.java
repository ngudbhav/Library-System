import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class brecord implements ActionListener
{
	JFrame f;
	JLabel head;
	JLabel title;
	JLabel id;
	JLabel author;
	JLabel ql;
	JTextField t1;
	JTextField t2;
	JTextField t3;
	JTextField t4;
	JButton submit;
	JButton done;
	String USER = "root";
  	String PASS = "root";
  	Connection conn = null;
  	Statement stmt,stmt1;
	{
    		String DB_URL = "jdbc:mysql://localhost/college";     //databasse name 
    		//  Database credentials
    		try{
      			//STEP 2: Register JDBC driver
      			Class.forName("com.mysql.jdbc.Driver");
      			//STEP 3: Open a connection
      			System.out.println("Connecting to Sign-up Portal...");
      			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Connection Successful");
			stmt = conn.createStatement(); 
    		}
    		catch(Exception e)
    		{
      			JOptionPane.showMessageDialog(null,e);
			System.out.println("Error Occured in the connection! Terminating...");
			System.exit(0);
    		}
	}

	brecord()
	{
	f = new JFrame("Save the book");
	f.setLayout(null);
	head = new JLabel("<html><body><font size=10>Save the book</font></body></html>");
	head.setBounds(10,10,275,50);
	f.add(head);
	title = new JLabel("Enter title");
	title.setBounds(10,100,100,30);
	f.add(title);
	id = new JLabel("Enter a unique ID");
	id.setBounds(10,150,100,30);
	f.add(id);
	author = new JLabel("Enter the author");
	author.setBounds(10,200,100,30);
	ql = new JLabel("Enter Total no.of books");
	ql.setBounds(10,250,100,30);
	f.add(ql);
	t1 = new JTextField();
	t1.setBounds(150,100,100,30);
	f.add(t1);
	t2 = new JTextField();
	t2.setBounds(150,150,100,30);
	f.add(t2);
	t3 = new JTextField();
	t3.setBounds(150,200,100,30);
	f.add(t3);
	t4 = new JTextField();
	t4.setBounds(150,250,100,30);
	f.add(t4);
	f.add(author);
	submit = new JButton("SUBMIT");
	submit.setBounds(40,330,110,20);
	submit.addActionListener(this);
	f.add(submit);
	done = new JButton("Done");
	done.setBounds(160,330,110,20);
	done.addActionListener(this);
	f.add(done);
	f.setSize(300,430);
	f.setLocation(450,250);
	f.setVisible(true);
	}

public void actionPerformed(ActionEvent e)
{
	Object ob = e.getSource();
	if(ob == submit)
	{
		String idcopy = t2.getText();
		String titcopy = t1.getText();
		String autcopy = t3.getText();
		String qlcopy = t4.getText();
		try
		{
			String query = "Insert into all_books values('"+idcopy+"','"+titcopy+"','"+autcopy+"','"+qlcopy+"')";
			stmt.execute(query);
			JOptionPane.showMessageDialog(null,"Book added Successfully to the book- shelf.");
		}
		catch(Exception error)
		{
			JOptionPane.showMessageDialog(null,error);
		}
	}
	else if(ob == done)
	{
		f.setVisible(false);
	}
}
public static void main(String z[])
{
	brecord ob = new brecord();
}

}