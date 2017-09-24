import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
class signup implements ActionListener,ItemListener
{
	JFrame f;
	int rand1,rand2,ansrand;
	Random rand;
	JLabel head;
	JLabel propic;
	JLabel name;
	JLabel pass;
	JLabel gender;
	JLabel age;
	JLabel id = new JLabel("Create your Unique ID");
	JLabel phno;
	JLabel email;
	JLabel CAPTCHA;
	JButton submit;
	JButton reset;
	JButton back;
	int type1=1;
	JTextField user;
	JPasswordField p;
	JTextField a;
	JTextField ide;
	JTextField phone;
	JTextField email1;
	JTextField captcha;
	JRadioButton male;
	JRadioButton female;
	ButtonGroup group;
	JRadioButton std;
	JRadioButton teacher;	
	ButtonGroup classify;
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
	signup()
	{
		f = new JFrame("Library Account");
		f.setLayout(null);
		group = new ButtonGroup();
		gender = new JLabel("Gender:");
		gender.setBounds(100,200,70,40);
		f.add(gender);
		age = new JLabel("Age:");
		age.setBounds(115,240,70,40);
		f.add(age);
		phno = new JLabel("Enter phone number:");
		phno.setBounds(30,320,180,30);
		f.add(phno);
		email = new JLabel("Enter your current email:");
		email.setBounds(25,360,180,30);
		f.add(email);
		rand = new Random();
		rand1 = rand.nextInt(50)+1;
		rand2 = rand.nextInt(50)+1;
		ansrand = rand1 + rand2;
		CAPTCHA = new JLabel("What is the result of " + rand1 + " + " + rand2);
		CAPTCHA.setBounds(30,400,180,30);
		f.add(CAPTCHA);
		male = new JRadioButton("Male");
		male.setBounds(210,200,60,40);
		male.setSelected(true);
		group.add(male);
		f.add(male);
		female = new JRadioButton("Female");
		female.setBounds(270,200,110,40);
		group.add(female);
		f.add(female);
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
		pass = new JLabel("Create a PassWord");
		pass.setBounds(40,150,150,40);
		f.add(pass);
		submit = new JButton("SUBMIT");
		submit.setBounds(60,450,110,20);
		f.add(submit);
		reset = new JButton("Reset");
		reset.setBounds(200,450,110,20);
		reset.addActionListener(this);
		f.add(reset);
		back = new JButton("Login ->");
		back.setBounds(100,500,200,30);
		back.addActionListener(this);
		f.add(back);
		user = new JTextField();
		user.setBounds(230,110,100,30);
		f.add(user);
		p = new JPasswordField();
		p.setBounds(230,160,100,30);
		f.add(p);
		a = new JTextField();
		a.setBounds(230,240,100,30);
		f.add(a);
		ide = new JTextField();
		ide.setBounds(230,280,100,30);
		f.add(ide);
		phone = new JTextField();
		phone.setBounds(230,320,100,30);
		f.add(phone);
		email1 = new JTextField();
		email1.setBounds(230,360,100,30);
		f.add(email1);
		id.setBounds(30,280,180,30);
		f.add(id);
		captcha = new JTextField();
		captcha.setBounds(230,400,100,30);
		f.add(captcha);
		submit.addActionListener(this);
		f.setSize(400,600);///400
		f.setLocation(500,50);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
public void itemStateChanged(ItemEvent event1)
{
	Object oh = event1.getSource();
	if(oh == teacher)
	{
		id.setText("Create your Username");
		type1 = 2;
	}
	else if(oh == std)
	{
		id.setText("Create your Unique ID");
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
Object oh = ek.getSource();
if(oh == submit)
{
	int output = JOptionPane.showConfirmDialog(null,"Do you want to add a profile picture?","Profile Picture!",JOptionPane.YES_NO_OPTION);
	if(output == 1)
	{
	char[] gth = p.getPassword();
	String b = new String(gth);
	String a1,c,d,e,f,h;
	a1=user.getText();
	c=a.getText();
	d=ide.getText();
	e=phone.getText();
	f=email1.getText();
	String g=captcha.getText();
	int cap	= Integer.parseInt(g);
	if(cap == ansrand)
	{
		if(male.isSelected())
		{
			h = male.getLabel();
			try
			{
 				String sql = "insert into login values('"+type1+"','"+a1+"','"+b+"','"+h+"','"+c+"','"+d+"','"+e+"','"+f+"')";
      				stmt.execute(sql);
				String sql1 = "create table "+ a1 +"_books (Sr_No int, Book_Name varchar(50), issue_date date, return_date date)";
				stmt.execute(sql1);
     				JOptionPane.showMessageDialog(null,"Sign-up Successful. Please go to login page");
				user.setText("");
				p.setText("");
				a.setText("");
				ide.setText("");
				phone.setText("");
				email1.setText("");
				captcha.setText("");
			}
			catch(Exception e1)
			{
     				JOptionPane.showMessageDialog(null,e1); 
			}
		}
		else
		{
			h = female.getLabel();
			try
			{
 				String sql = "insert into login values('"+type1+"','"+a1+"','"+b+"','"+h+"','"+c+"','"+d+"','"+e+"','"+f+"')";
      				stmt.execute(sql);
				String sql1 = "create table "+ a1 +"_books (Sr_No int, Book_Name varchar(50), issue_date date, return_date date)";
				stmt.execute(sql1);
     				JOptionPane.showMessageDialog(null,"Sign-up Successful. Please go to login page");
			user.setText("");
	p.setText("");
	a.setText("");
	ide.setText("");
	phone.setText("");
	email1.setText("");
	captcha.setText("");
			}
			catch(Exception e1)
			{
     				JOptionPane.showMessageDialog(null,e1); 
			}
		}
	}
	else
	{
		JOptionPane.showMessageDialog(null,"Please enter the correct captcha");
	}
	}
	else if(output == 0)
	{
		char[] gth = p.getPassword();
		String b = new String(gth);
		String a1,c,d,e,f,h;
		a1=user.getText();
		c=a.getText();
		d=ide.getText();
		e=phone.getText();
		f=email1.getText();
		String g=captcha.getText();
		int cap	= Integer.parseInt(g);
		try{
		JFileChooser jfc = new JFileChooser();
		jfc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
		int confirmoutput = jfc.showOpenDialog(null);
		if(confirmoutput==JFileChooser.APPROVE_OPTION){    
		File fgh=jfc.getSelectedFile();  
		String filepath=fgh.getPath();
		System.out.println(filepath);
		FileInputStream is = new FileInputStream(filepath);
        	FileOutputStream os = new FileOutputStream("D://NGUdbhav Works/JAVA/College Library System/profile/" +d+ ".jpg");
        	byte[] buffer = new byte[8192];
        	int length;
        	while ((length = is.read(buffer)) > 0) {
            		os.write(buffer, 0, length);
        	}
		}}catch(Exception bh){System.out.println(bh);}
		if(cap == ansrand)
		{
		if(male.isSelected())
		{
			h = male.getLabel();
			try
			{
 				String sql = "insert into login values('"+type1+"','"+a1+"','"+b+"','"+h+"','"+c+"','"+d+"','"+e+"','"+f+"')";
      				stmt.execute(sql);
				String sql1 = "create table "+ a1 +"_books (Sr_No int, Book_Name varchar(50), issue_date date, return_date date)";
				stmt.execute(sql1);
     				JOptionPane.showMessageDialog(null,"Sign-up Successful. Please go to login page");
				user.setText("");
				p.setText("");
				a.setText("");
				ide.setText("");
				phone.setText("");
				email1.setText("");
				captcha.setText("");
			}
			catch(Exception e1)
			{
     				JOptionPane.showMessageDialog(null,e1); 
			}
		}
		else
		{
			h = female.getLabel();
			try
			{
 				String sql = "insert into login values('"+type1+"','"+a1+"','"+b+"','"+h+"','"+c+"','"+d+"','"+e+"','"+f+"')";
      				stmt.execute(sql);
				String sql1 = "create table "+ a1 +"_books (Sr_No int, Book_Name varchar(50), issue_date date, return_date date)";
				stmt.execute(sql1);
     				JOptionPane.showMessageDialog(null,"Sign-up Successful. Please go to login page");
			user.setText("");
	p.setText("");
	a.setText("");
	ide.setText("");
	phone.setText("");
	email1.setText("");
	captcha.setText("");
			}
			catch(Exception e1)
			{
     				JOptionPane.showMessageDialog(null,e1); 
			}
		}
	}
	else
	{
		JOptionPane.showMessageDialog(null,"Please enter the correct captcha");
	}
	}
}
else if(oh == reset)
{
	user.setText("");
	p.setText("");
	a.setText("");
	ide.setText("");
	phone.setText("");
	email1.setText("");
	captcha.setText("");
}
else if(oh == back)
{
	f.setVisible(false);
	login ghj = new login();
}
}
public static void main (String z[])
{
}
}