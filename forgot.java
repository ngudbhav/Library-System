import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
class forgot implements ActionListener,ItemListener
{
	JFrame f;
	int rand1,rand2,ansrand;
	int type1 = 1;
	Random rand;
	JLabel head;
	JLabel name;
	JLabel id = new JLabel("Enter your ID");
	JLabel phno;
	JLabel email;
	JLabel CAPTCHA;
	JButton submit;
	JButton back;
	JTextField user;
	JTextField ide;
	JTextField phone;
	JTextField email1;
	JTextField captcha;
	JRadioButton std;
	JRadioButton teacher;	
	ButtonGroup classify;
  	String USER = "root";
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
      			System.out.println("Connecting... Please wait!");
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
	
	forgot()
	{
		f = new JFrame("Match Your Credentials");
		f.setLayout(null);
		phno = new JLabel("Enter phone number:");
		phno.setBounds(30,190,180,30);
		f.add(phno);
		email = new JLabel("Enter your current email:");
		email.setBounds(25,230,180,30);
		f.add(email);
		rand = new Random();
		rand1 = rand.nextInt(50)+1;
		rand2 = rand.nextInt(50)+1;
		ansrand = rand1 + rand2;
		CAPTCHA = new JLabel("What is the result of " + rand1 + " + " + rand2);
		CAPTCHA.setBounds(30,270,180,30);
		f.add(CAPTCHA);
		classify = new ButtonGroup();
		std = new JRadioButton("Student");
		std.setBounds(40,70,70,40);
		std.addActionListener(this);
		std.setSelected(true);
		std.addItemListener(this);
		classify.add(std);
		f.add(std);
		teacher = new JRadioButton("Teacher");
		teacher.setBounds(230,70,110,40);
		teacher.addItemListener(this);
		classify.add(teacher);
		f.add(teacher);
		head = new JLabel("<html><body><font size=10>Library Portal</font></body></html>");
		head.setBounds(10,10,275,50);
		f.add(head);
		name = new JLabel("Enter your first name:");
		name.setBounds(40,100,150,40);
		f.add(name);
		submit = new JButton("SUBMIT");
		submit.setBounds(60,320,250,40);
		f.add(submit);
		back = new JButton("Login ->");
		back.setBounds(100,370,200,30);
		back.addActionListener(this);
		f.add(back);
		user = new JTextField();
		user.setBounds(230,110,100,30);
		f.add(user);
		ide = new JTextField();
		ide.setBounds(230,150,100,30);
		f.add(ide);
		phone = new JTextField();
		phone.setBounds(230,190,100,30);
		f.add(phone);
		email1 = new JTextField();
		email1.setBounds(230,230,100,30);
		f.add(email1);
		id.setBounds(30,150,180,30);
		f.add(id);
		captcha = new JTextField();
		captcha.setBounds(230,270,100,30);
		f.add(captcha);
		submit.addActionListener(this);
		f.setSize(400,475);
		f.setLocation(500,50);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
public void itemStateChanged(ItemEvent event1)
{
	Object oh = event1.getSource();
	if(oh == teacher)
	{
		id.setText("Enter your Username");
		type1 = 2;
	}
	else if(oh == std)
	{
		id.setText("Enter your Unique ID");
		type1 = 1;
	}
}
public void actionPerformed(ActionEvent ek)
{
	/*JTextField user;//Namea
	JTextField p;//Password
	JTextField a;//Age
	JTextField ide;//ID
	JTextField phone;
	JTextField email1;
	JTextField captcha;*/
int count = 0;
Object oh = ek.getSource();
if(oh == submit)
{
	String a1,d,e,r,namegt,passwordgt;
	a1=user.getText();
	d=ide.getText();
	e=phone.getText();
	r=email1.getText();
	String g=captcha.getText();
	int cap	= Integer.parseInt(g);
	if(cap == ansrand)
	{
			try
			{
				// create the java statement
				String query = "Select * from login where name = '"+a1+"' and type 					= '"+type1+"' and ID = '"+d+"' and phone = '"+e+"' and email = '"+r+"'";
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
					JOptionPane.showMessageDialog(null,"Error! Credentials not 					matched!");
					
				}
				else if(count == 1)
				{
					
					JOptionPane.showMessageDialog(null,"Credentials matched!");
					//System.out.println(result);
					f.setVisible(false);
					newpassword gh = new newpassword(a1,d,e,r,type1);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Error in updating 							password!");
					user.setText("");
					ide.setText("");
					phone.setText("");
					email1.setText("");
					captcha.setText("");
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
		JOptionPane.showMessageDialog(null,"Enter the correct CAPTCHA!");
	}
}
else if(oh == back)
{
	f.setVisible(false);
	login ghj = new login();
}
}
public static void main(String z[])
{
	forgot ghw = new forgot();
}
}