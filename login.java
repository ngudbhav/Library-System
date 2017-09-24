import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
class login implements ActionListener,ItemListener
{
	JFrame f;
	int typeo=1;
	JLabel head;
	JLabel id;
	JLabel pass;
	JButton blogin;
	JButton bsign;
	JButton forget;
	JTextField user;
	JPasswordField p;
	JRadioButton std;
	JRadioButton teacher;
	JRadioButton admin;	
	ButtonGroup group;
	String USER = "root";
	String query;
  	String PASS = "root";
  	Connection conn = null;
  	Statement stmt;
	{
    		String DB_URL = "jdbc:mysql://localhost/college";     //databasse name 
    		//  Database credentials
    		try{
      			//STEP 2: Register JDBC driver
      			Class.forName("com.mysql.jdbc.Driver");
      			//STEP 3: Open a connection
      			System.out.println("Connecting to Sign-in Portal...");
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
	login()
	{
		f = new JFrame("Library Account");
		f.setLayout(null);
		group = new ButtonGroup();
		std = new JRadioButton("Student");
		std.setBounds(40,70,70,40);
		std.setSelected(true);
		std.addItemListener(this);
		group.add(std);
		f.add(std);
		teacher = new JRadioButton("Teacher");
		teacher.setBounds(140,70,110,40);
		group.add(teacher);
		teacher.addItemListener(this);
		f.add(teacher);
		admin = new JRadioButton("Admin");
		admin.setBounds(250,70,125,40);
		group.add(admin);
		admin.addItemListener(this);
		f.add(admin);
		head = new JLabel("<html><body><font size=10>Library Portal</font></body></html>");
		head.setBounds(10,10,275,50);
		f.add(head);
		id = new JLabel("Enter the Unique ID");
		id.setBounds(40,100,150,40);
		f.add(id);
		pass = new JLabel("PassWord");
		pass.setBounds(40,150,150,40);
		f.add(pass);
		blogin = new JButton("LOGIN");
		blogin.setBounds(60,230,110,20);
		blogin.addActionListener(this);
		f.add(blogin);
		bsign = new JButton("SIGN UP");
		bsign.setBounds(200,230,110,20);
		bsign.addActionListener(this);
		f.add(bsign);
		forget = new JButton("Forgot Password?");
		forget.setBounds(100,260,200,30);
		forget.addActionListener(this);
		f.add(forget);
		user = new JTextField();
		user.setBounds(230,110,100,30);
		f.add(user);
		p = new JPasswordField();
		p.setBounds(230,160,100,30);
		f.add(p);
		f.setSize(400,340);
		f.setLocation(500,250);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
public void itemStateChanged(ItemEvent event1)
{
	Object oh = event1.getSource();
	if(oh == teacher)
	{
		id.setText("Enter your Username");
		typeo = 2;
	}
	else if(oh == std)
	{
		id.setText("Enter your Unique ID");
		typeo = 1;
	}
	else
	{
		id.setText("Enter the Username");
		typeo = 3;
	}
}
public void actionPerformed(ActionEvent e)
{
	Object oh = e.getSource();
	int count=0;
	String namegt="s",passwordgt="s";
	if(oh == blogin)
	{
		
		String usern;
		usern = user.getText();
		char[] ghg = p.getPassword();
		String pass = new String(ghg);
		try
		{
			if(typeo == 1)
			{
				query = "Select * from login where type = 1 and ID = '"+usern+"' and binary password = '"+pass+"'";
			}
			else if(typeo == 2)
			{
				query = "Select * from login where type = 2 and ID = '"+usern+"' and binary password = '"+pass+"'";
			}
			else if(typeo == 3)
			{
				query = "Select * from login where type = 3 and name = '"+usern+"' and binary password = '"+pass+"'";
			}
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next())
			{
				count = 1;
				namegt = rs.getString("name");
				passwordgt = rs.getString("password");				
			}
			if(count == 0)
			{
				JOptionPane.showMessageDialog(null,"Error! Username or password incorrect!");
				p.setText("");
			}
			else
			{
				System.out.format("\nHello! %s \n",namegt);
				JOptionPane.showMessageDialog(null,"Login Successful!");
				f.setVisible(false);
				main_page jh = new main_page(namegt);
			}
			st.close();
		}
		catch(Exception e1)
		{
     			JOptionPane.showMessageDialog(null,e1);
			System.out.println("Login Error!");
		}
	}
	else if(oh == bsign)
	{
		f.setVisible(false);
		signup jh = new signup();
	}
	else if(oh == forget)
	{
		f.setVisible(false);
		forgot jh = new forgot();
	}
}
public static void main (String z[])
{
	login ob = new login();
}

}