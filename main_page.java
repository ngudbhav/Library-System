import javax.swing.*;
import java.text.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.io.*;
class main_page implements ActionListener,MenuListener,MouseListener,InternalFrameListener
{
	JFrame f;
	JDesktopPane dp;
	JInternalFrame jpi;
	SimpleDateFormat issuedate;
	String  asd;
	int output;
	java.util.Date d;
	JScrollPane sp;
	JScrollPane sp1;
	String namespace;
	JInternalFrame ji;
	JInternalFrame jki;
	int results=0;
	JLabel head;
	String data[][] = new String[50][4];
	String data1[][] = new String[50][6];
	String column[] = {"Book ID","BOOK NAME","Author NAME","Quantites left"};
	String column1[] = {"Name","Gender","Age","ID","Phone","Email"};
	JTextField s;
	int rowsel = -5;
	int colsel = -5;
	JButton add1;
	JButton search;
	JButton issue;
	JButton returnbook;
	/*List t;
	DefaultListModel listmodel;
	ListItem tit;
	ListItem aut;*/
	int i,j=0;
	JMenuBar mb;
	JMenu book;
	JMenu account;
	JMenu logout;
	String tdata = "";
	JMenu record;
	JMenu feedbackc;
	JMenuItem std;
	JTable table;
	JTable sandtr;
	JMenuItem teacher;
	JMenuItem book1;
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
	
	main_page(String user)
	{
		namespace = user;
		f = new JFrame("Welcome to the Library Portal " +user+ " Logged in!");
		f.setLayout(null);
		issuedate = new SimpleDateFormat("yyyy-MM-dd");
		String slog = "Close This Window And You Will Be Logged Out Automatically!";
		JLabel slogd = new JLabel(slog +" ");
		slogd.setBounds(400,50,1000,50);
		f.add(slogd);
		if(user.equals("ngudbhav"))
		{
			add1 = new JButton("Add the book");
			add1.setBounds(300,150,120,20);
			add1.addActionListener(this);
			f.add(add1);
			mb = new JMenuBar();
			mb.setBounds(10,5,1070,20);
			record = new JMenu("Records");
			mb.add(record);
			logout = new JMenu("Log Out");
			logout.addMenuListener(this);
			mb.add(logout);
			std = new JMenuItem("Student");
			record.add(std);
			std.addActionListener(this);
			teacher = new JMenuItem("Teacher");
			record.add(teacher);
			teacher.addActionListener(this);
			f.add(mb);
		}
		else
		{
			mb = new JMenuBar();
			mb.setBounds(10,5,1070,20);
			account = new JMenu("Change Profile!");
			account.addMenuListener(this);
			mb.add(account);
			feedbackc = new JMenu("Feedback");
			feedbackc.addMenuListener(this);
			mb.add(feedbackc);
			logout = new JMenu("Log out!");
			logout.addMenuListener(this);
			mb.add(logout);
			f.add(mb);
			issue = new JButton("Issue a book");
			issue.setBounds(160,450,300,60);
			issue.addActionListener(this);
			f.add(issue);
			returnbook = new JButton("Return a book");
			returnbook.setBounds(560,450,300,60);
			returnbook.addActionListener(this);
			f.add(returnbook);
		}
		//t = new ArrayList();
		s = new JTextField("");
		table = new JTable(data,column)
		{
			public boolean isCellEditable(int row,int col)
			{
				return false;
			}
		};
		table.addMouseListener(this);
		sp=new JScrollPane(table);
		sp.setBounds(150,200,700,160);
		f.add(sp);
		sandtr = new JTable(data1,column1)
		{
			public boolean isCellEditable(int row,int col)
			{
				return false;
			}
		};
		sp1 = new JScrollPane(sandtr);
		sp1.setBounds(140,190,710,170);
		sp1.setVisible(false);
		f.add(sp1);
		for(i=0;i<50;i++)
		{
			for(j=0;j<4;j++)
			{
				data[i][j] = "Search Results appear!";
			}
		}
		
		s.setBounds(10,100,1070,30);
		f.add(s);
		search = new JButton("Search");
		search.setBounds(500,150,90,20);
		search.addActionListener(this);
		f.add(search);
		head = new JLabel("<html><body><font size=10>Library Portal</font></body></html>");
		head.setBounds(450,15,580,50);
		f.add(head);
		dp = new JDesktopPane();
		dp.setBackground(Color.black);
		dp.setBounds(150,150,930,400);
		f.setSize(1100,600);
		f.setLocation(100,100);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
public void mouseClicked(MouseEvent arg0)
{

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
	rowsel = table.getSelectedRow();
	tdata = String.valueOf(table.getValueAt(rowsel,3));
	System.out.println(tdata);
	System.out.println(rowsel);
}
public void actionPerformed(ActionEvent e)
{
System.out.print(rowsel);
	Object oh = e.getSource();
	int count = 0;
	int loop = 0;
	if(oh == std)
	{
		int stdint = 1;
		count = 0;
		String column1[] = {"Name","Gender","Age","ID","Phone","Email"};
		int loop1 = 0;
		String[][] srecord = new String[50][6];
		String stdrecord = "Select * from login where type = '"+stdint+"'";
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(stdrecord);
			while(rs.next())
			{
				count = 1;
				srecord[loop1][0] = rs.getString("name");
				srecord[loop1][1] = Integer.toString(rs.getInt("age"));
				srecord[loop1][2] = rs.getString("gender");
				srecord[loop1][3] = rs.getString("ID");
				srecord[loop1][4] = Long.toString(rs.getLong("phone"));
				srecord[loop1][5] = rs.getString("email");
				for(i=0;i<6;i++)
				{
					data1[loop1][i] = srecord[loop1][i];
				}
				loop1 = loop1 + 1;
			}
			for(i=loop1;i<50;i++)
			{
				for(j=0;j<6;j++)
				{
					data1[i][j] = "";
				}
			}
			sp1.setVisible(false);
			sp1.setVisible(true);
			sp.setVisible(false);
			if(count == 0)
			{
				JOptionPane.showMessageDialog(null,"Error!");
				for(i=0;i<50;i++)
				{
					for(j=0;j<6;j++)
					{
						data1[i][j] = "Search Results appear!";
					}
				}
				sp1.setVisible(false);
				sp1.setVisible(true);
				sp.setVisible(false);
			}
			else if(count == 1)
			{
				
			}
		}
		catch(Exception Error)
		{
			JOptionPane.showMessageDialog(null,Error);
		}
	}
	else if(oh == teacher)
	{
		int stdint = 2;
		count = 0;
		String column1[] = {"Name","Gender","Age","ID","Phone","Email"};
		int loop1 = 0;
		String[][] srecord = new String[50][6];
		String stdrecord = "Select * from login where type = '"+stdint+"'";
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(stdrecord);
			while(rs.next())
			{
				count = 1;
				srecord[loop1][0] = rs.getString("name");
				srecord[loop1][1] = Integer.toString(rs.getInt("age"));
				srecord[loop1][2] = rs.getString("gender");
				srecord[loop1][3] = rs.getString("ID");
				srecord[loop1][4] = Long.toString(rs.getLong("phone"));
				srecord[loop1][5] = rs.getString("email");
				for(i=0;i<6;i++)
				{
					data1[loop1][i] = srecord[loop1][i];
				}
				loop1 = loop1 + 1;
			}
			for(i=loop1;i<50;i++)
			{
				for(j=0;j<6;j++)
				{
					data1[i][j] = "";
				}
			}
			sp1.setVisible(false);
			sp1.setVisible(true);
			sp.setVisible(false);
			if(count == 0)
			{
				JOptionPane.showMessageDialog(null,"Error!");
				for(i=0;i<50;i++)
				{
					for(j=0;j<6;j++)
					{
						data1[i][j] = "Search Results appear!";
					}
				}
				sp1.setVisible(false);
				sp1.setVisible(true);
				sp.setVisible(false);
			}
			else if(count == 1)
			{
				
			}
		}
		catch(Exception Error)
		{
			JOptionPane.showMessageDialog(null,Error);
		}
	}
	else if(oh == add1)
	{
		brecord ob = new brecord();
	}
	else if(oh == search)
	{
		String term = s.getText();
		results = 0;
		count = 0;
		String[] nameresult = new String[50];
		String[] idresult = new String[50];
		String[] authorresult = new String[50];
		String[] qleftresult = new String[50];
		if(!term.equals(""))
		{
			try
			{
			String query = "Select * from all_books where book_name = '"+term+"'";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next())
			{
				count = 1;
				nameresult[loop] = rs.getString("book_name");
				idresult[loop] = Integer.toString(rs.getInt("Sr_No"));
				authorresult[loop] = rs.getString("Author");
				qleftresult[loop] = Integer.toString(rs.getInt("Quantites_left"));
				data[results][3] = qleftresult[loop];
				data[results][2] = authorresult[loop];
				data[results][1] = nameresult[loop];
				data[results][0] = idresult[loop];
				System.out.println(nameresult[loop]);
				loop = loop + 1;
				results = results + 1;
				System.out.println(results);
			}
			for(i=results;i<50;i++)
			{
				for(j=0;j<4;j++)
				{
					data[i][j] = "";
				}
			}
			sp.setVisible(false);
			sp1.setVisible(false);
			sp.setVisible(true);
			if(count == 0)
			{
				JOptionPane.showMessageDialog(null,"Error! Book not found!");
				for(i=0;i<50;i++)
				{
					for(j=0;j<4;j++)
					{
						data[i][j] = "Search Results appear!";
					}
				}
				sp.setVisible(false);
				sp1.setVisible(false);
				sp.setVisible(true);
				s.setText("");
			}
			else if(count == 1)
			{
				
			}
			}
			catch(Exception err)
			{
				JOptionPane.showMessageDialog(null,err);
			}
		}
		else if(term.equals(""))
		{
			try
			{
			String query = "Select * from all_books";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next())
			{
				count = 1;
				nameresult[loop] = rs.getString("book_name");
				idresult[loop] = Integer.toString(rs.getInt("Sr_No"));
				authorresult[loop] = rs.getString("Author");
				qleftresult[loop] = Integer.toString(rs.getInt("Quantites_left"));
				data[results][3] = qleftresult[loop];
				data[results][2] = authorresult[loop];
				data[results][1] = nameresult[loop];
				data[results][0] = idresult[loop];
				System.out.println(nameresult[loop]);
				loop = loop + 1;
				results = results + 1;
				System.out.println(results);
			}
			for(i=results;i<50;i++)
			{
				for(j=0;j<4;j++)
				{
					data[i][j] = "";
				}
			}
			sp.setVisible(false);
			sp1.setVisible(false);
			sp.setVisible(true);
			if(count == 0)
			{
				JOptionPane.showMessageDialog(null,"Error! Book not found!");
				for(i=0;i<50;i++)
				{
					for(j=0;j<4;j++)
					{
						data[i][j] = "Search Results appear!";
					}
				}
				sp.setVisible(false);
				sp1.setVisible(false);
				sp.setVisible(true);
				s.setText("");
			}
			else if(count == 1)
			{
				
			}
			}
			catch(Exception err)
			{
				JOptionPane.showMessageDialog(null,err);
			}
		}
	}
	if(oh == issue)
	{
		if(rowsel == -5)
		{
			System.out.println(rowsel);
			JOptionPane.showMessageDialog(null,"Search for a book and then click on a row to issue the book");
		}
		else
		{
			if(tdata.equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please select a valid Row");
			}
			else if(tdata.equals("0"))
			{
				JOptionPane.showMessageDialog(null,"Sorry Stock out!");
			}
			else
			{
				String tdataid = String.valueOf(table.getValueAt(rowsel,0));
				d = new java.util.Date();
				String tdataissue = issuedate.format(d);
				String tdataname = String.valueOf(table.getValueAt(rowsel,1));
				String tdataqleft = String.valueOf(table.getValueAt(rowsel,3));
				int news = Integer.parseInt(tdataqleft);
				int x = news-1;
				String hgjfk = Integer.toString(x);
				String queryinsert = "insert into "+namespace+"_books values('"+tdataid+"','"+tdataname+"','"+issuedate.format(d)+"',null)";
				String modifyq = "update all_books set Quantites_left = '"+hgjfk+"' where Sr_No = '"+data[rowsel][0]+"'";
				try
				{
					Statement stmt = conn.createStatement();
					stmt.execute(queryinsert);
					stmt.executeUpdate(modifyq);
					stmt.close();
					JOptionPane.showMessageDialog(null,"Book Issued");
					sp.setVisible(false);
					sp1.setVisible(false);
					sp.setVisible(true);
				}
				catch(Exception errrrr)
				{
					JOptionPane.showMessageDialog(null,errrrr);
				}
			}
		}	
	}
	else if(oh == returnbook)
	{
		returnb nbnb = new returnb(namespace);
	}
}
public void internalFrameActivated(InternalFrameEvent e){}
public void internalFrameClosed(InternalFrameEvent e){}
public void internalFrameClosing(InternalFrameEvent e)
{
	f.remove(dp);
	search.setVisible(true);
	issue.setVisible(true);
	returnbook.setVisible(true);
	sp.setVisible(true);
	f.setVisible(false);
	f.setVisible(true);
	System.out.println("Window Has been Closed");
}
public void internalFrameDeactivated(InternalFrameEvent e){}
public void internalFrameDeiconified(InternalFrameEvent e){}
public void internalFrameIconified(InternalFrameEvent e){}
public void internalFrameOpened(InternalFrameEvent e){}
public void menuSelected(MenuEvent e)
{
	Object oh = e.getSource();
	if(oh == logout)
	{
		output = JOptionPane.showConfirmDialog(f,"Are You Sure You Want to LOGOUT?","LOGOUT?",JOptionPane.YES_NO_OPTION);
		System.out.println(output);
		menuDeselected(e);
		if(output == 0)
		{
			f.setVisible(false);
			login hg = new login();
		}
		else
		{
			logout.setSelected(false);
			f.setVisible(false);
			f.setVisible(true);
		}
		menuDeselected(e);
	}
	if(oh == account)
	{
		search.setVisible(false);
		issue.setVisible(false);
		returnbook.setVisible(false);
		sp.setVisible(false);
		jpi=new JInternalFrame("AccountChange",true,true,true,true);
		jpi.setBounds(0,0,930,400);
		jpi.setLayout(null);
		jpi.addInternalFrameListener(this);
		accountchange gh = new accountchange(namespace);
		jpi.add(gh);
		dp.add(jpi);
		jpi.setVisible(true);
		f.add(dp);
		f.setVisible(false);
		f.setVisible(true);
		menuDeselected(e);
	}
	else if(oh == feedbackc)
	{
		search.setVisible(false);
		issue.setVisible(false);
		returnbook.setVisible(false);
		sp.setVisible(false);
		jki = new JInternalFrame("Feedback",true,true,true,true);
		jki.setBounds(0,0,930,400);
		jki.setLayout(null);
		jki.addInternalFrameListener(this);
		feedback ty = new feedback();
		jki.add(ty);
		dp.add(jki);
		jki.setVisible(true);
		f.add(dp);
		f.setVisible(false);
		f.setVisible(true);
	}
}
public void menuDeselected(MenuEvent e)
{
}
public void menuCanceled(MenuEvent e)
{
}
public static void main(String z[])
{
}
}
