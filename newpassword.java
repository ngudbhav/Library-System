import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
class newpassword implements ActionListener
{
	JFrame f;
	int typeo=1;
	JLabel head;
	String copy1,copy2,copy3,copy4;
	int copy5;
	JLabel pass;
	JLabel confirm;
	JButton save;
	JPasswordField p;
	JPasswordField confirmp;
	String namegt,passwordgt;	
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
      			System.out.println("Connecting... Please Wait!");
      			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Connection Successful!");
			stmt = conn.createStatement(); 
    		}
    		catch(Exception e)
    		{
      			JOptionPane.showMessageDialog(null,e);
			System.out.println("Error Occured in the connection! Terminating...");
			System.exit(0);
    		}
	}
	newpassword(String user1,String idcopy,String phcopy,String emcopy, int type)
	{
		copy1 = user1;
		copy2 = idcopy;
		copy3 = phcopy;
		copy4 = emcopy;
		copy5 = type;
		f = new JFrame("Library Account");
		f.setLayout(null);
		head = new JLabel("<html><body><font size=10>Library Portal</font></body></html>");
		head.setBounds(10,10,275,50);
		f.add(head);
		pass = new JLabel("Enter the new Password");
		pass.setBounds(40,100,150,40);
		f.add(pass);
		confirm = new JLabel("Confirm your password");
		confirm.setBounds(40,150,150,40);
		f.add(confirm);
		save = new JButton("Save!");
		save.setBounds(90,230,210,20);
		save.addActionListener(this);
		f.add(save);
		p = new JPasswordField();
		p.setBounds(230,110,100,30);
		f.add(p);
		confirmp = new JPasswordField();
		confirmp.setBounds(230,160,100,30);
		f.add(confirmp);
		f.setSize(400,330);
		f.setLocation(500,250);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
public void actionPerformed(ActionEvent e)
{
	Object oh = e.getSource();
	int count=0;
	String namegt="s",passwordgt="s";
	if(oh == save)
	{
		char[] ghg = p.getPassword();
		String pass = new String(ghg);
		char[] ghgas = confirmp.getPassword();
		String cpass = new String(ghgas);
		if(pass.equals(cpass))
		{
			try
			{
				query = "Update login set password = '"+pass+"' where name = 				'"+copy1+"' and type = '"+copy5+"' and ID = '"+copy2+"' and phone = '"+copy3+"' and email = '"+copy4+"'";
				stmt.execute(query);
				// create the java statement
				String query1 = "Select * from login where name = 				'"+copy1+"' and type = '"+copy5+"' and ID = '"+copy2+"' and phone = '"+copy3+"' and email = '"+copy4+"'";
				Statement st = conn.createStatement();
				// execute the query, and get a java resultset
				ResultSet rs = st.executeQuery(query1);
				// iterate through the java resultset
				while (rs.next())
				{
					count = 1;
					namegt = rs.getString("name");
					passwordgt = rs.getString("password");				
				}
				if(count == 0)
				{
					JOptionPane.showMessageDialog(null,"Error in updating 					password!");
				}
				if(pass.equals(passwordgt))
				{
					
					JOptionPane.showMessageDialog(null,"Password changed 					successfully! Redirecting to login page!");
					//System.out.println(result);
					f.setVisible(false);
					login jh = new login();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Error in updating 					password!");
				}
				st.close();
			}
			catch(Exception e1)
			{
     				JOptionPane.showMessageDialog(null,e1);
				System.out.println("An error occurred!");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Passwords do not match!");
			p.setText("");
			confirmp.setText("");
		}
	}
}
public static void main (String z[])
{
	//newpassword ob = new newpassword("ngudbhav",3);
}
}