import required.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class accountchange extends JPanel implements ActionListener,MouseListener
{
	String namespace;
	JLabel propic;
	int count = 0;
	JLabel name;
	JLabel age;
	JLabel phno;
	JLabel email;
	JButton submit;
	JButton reset;
	int type1=1;
	JTextField user;
	JTextField a;
	JTextField phone;
	JTextField email1;
	String USER = "root";
	String query;
  	String PASS = "root";
  	Connection conn = null;
  	Statement stmt;
	{
    		String DB_URL = "jdbc:mysql://localhost/college";     //databasse name 
    		//  Database credentials
    		try
		{
      			//STEP 2: Register JDBC driver
      			Class.forName("com.mysql.jdbc.Driver");
      			//STEP 3: Open a connection
      			System.out.println("Connecting...");
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
	accountchange(String user1)
	{
		namespace = user1;
		setLayout(null);
		submit = new JButton("SUBMIT");
		submit.setBounds(300,100,110,20);
		add(submit);
		reset = new JButton("Reset");
		reset.setBounds(500,100,110,20);
		reset.addActionListener(this);
		add(reset);
		user = new JTextField("Correct Name");
		user.addMouseListener(this);
		user.setBounds(23,23,150,50);
		add(user);
		a = new JTextField("Age");
		a.addMouseListener(this);
		a.setBounds(200,23,150,50);
		add(a);
		phone = new JTextField("Phone");
		phone.addMouseListener(this);
		phone.setBounds(377,23,150,50);
		add(phone);
		email1 = new JTextField("E-Mail");
		email1.addMouseListener(this);
		email1.setBounds(554,23,150,50);
		add(email1);
		submit.addActionListener(this);
		setSize(780,250);
		setVisible(true);
	}
public void mouseClicked(MouseEvent e)
{
	Object oh = e.getSource();
	if(oh == user)
	{
		user.setText("");
	}
	else if(oh == a)
	{
		a.setText("");
	}
	else if(oh == phone)
	{
		phone.setText("");
	}
	else if(oh == email1)
	{
		email1.setText("");
	}
}
public void mouseExited(MouseEvent arg0)
{
}
public void mouseEntered(MouseEvent arg0)
{
}
public void mousePressed(MouseEvent arg0)
{
}
public void mouseReleased(MouseEvent arg0)
{
	
}
public void actionPerformed(ActionEvent e)
{
	Object ob = e.getSource();
	if(ob == reset)
	{
		user.setText("Correct Name");
		a.setText("Age");
		phone.setText("Phone");
		email1.setText("E-Mail");
	}
	else if(ob == submit)
	{
		String namegt = "";
		String getuser = user.getText();
		String geta = a.getText();
		String getphone = phone.getText();
		String getemail = email1.getText();
		String query = "update login set name = '"+getuser+"',age = '"+geta+"',phone = '"+getphone+"',email = '"+getemail+"' where name = '"+namespace+"'";
		try
		{
			stmt.executeUpdate(query);
			String query1 = "Select * from login where name = 				'"+getuser+"' and age = '"+geta+"' and phone = '"+getphone+"' and email = '"+getemail+"'";
				Statement st = conn.createStatement();
				// execute the query, and get a java resultset
				ResultSet rs = st.executeQuery(query1);
				// iterate through the java resultset
				while (rs.next())
				{
					count = 1;
					namegt = rs.getString("name");
					
				}
				if(count == 0)
				{
					JOptionPane.showMessageDialog(null,"Error in Details.No record matched with the name!");
				}
				if(namegt.equals(getuser))
				{
					namespace = getuser;
					JOptionPane.showMessageDialog(null,"Details changed 					successfully!");
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Can not update details!");
				}
				st.close();
		}
		catch(Exception error)
		{
			JOptionPane.showMessageDialog(null,error);
		}
	}
}
public static void main(String z[])
{
}
}