import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
class returnb implements ActionListener,MouseListener
{
	JFrame f;
	SimpleDateFormat issuedt;
	SimpleDateFormat returndt;
	SimpleDateFormat currentdate;
	java.util.Date d;
	JScrollPane sp;
	String data[][]=new String[50][4];
	String column[]={"Book ID","BOOK NAME","Issue date","Return date"};
	int rowsel = -2;
	int i,j=0;
	int rewsel = 100;
	int nullreturn = 0;
	int count,loop = 0;
	JLabel head;
	String namespace;
	JLabel title;
	JLabel id;
	JTable table;
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

	returnb(String user)
	{
	namespace = user;
	f = new JFrame(namespace+" Logged in!");
	f.setLayout(null);
	int results=0;
	String[] nameresult = new String[50];
	issuedt = new SimpleDateFormat("yyyy-MM-dd");
	returndt = new SimpleDateFormat("yyyy-MM-dd");
	String[] issuedate = new String[50];
	String[] returndate = new String[50];
	String[] idcpy = new String[50];
	//String query = "Select * from "+user+"_books";
	try
	{
		String query = "Select * from "+user+"_books";
		// create the java statement
		Statement st = conn.createStatement();
		// execute the query, and get a java resultset
		ResultSet rs = st.executeQuery(query);
		// iterate through the java resultset
		while (rs.next())
		{
			count = 1;
			nameresult[loop] = rs.getString("book_name");
			idcpy[loop] = Integer.toString(rs.getInt("Sr_No"));
			issuedate[loop] = issuedt.format(rs.getDate("issue_date"));
			if(rs.getDate("return_date")!=null)
			{
				returndate[loop] = returndt.format(rs.getDate("return_date"));
				data[results][3] = returndate[loop];
			}
			else
			{
				data[results][3] = "";
				returndate[loop] = "";
			}
			data[results][2] = issuedate[loop];
			data[results][1] = nameresult[loop];
			data[results][0] = idcpy[loop];
			loop = loop + 1;
			results = results + 1;
			for(i=results;i<50;i++)
			{
				for(j=0;j<4;j++)
				{
					data[i][j] = "";
				}
			}
		}
		System.out.println(loop);
		if(count == 0)
		{
			JOptionPane.showMessageDialog(null,"Error! Book not found!");
			f.setVisible(false);
			for(i=0;i<50;i++)
			{
				for(j=0;j<4;j++)
				{
					data[i][j] = "";
				}
			}
		}
		else
		{
			f.setVisible(true);
		}
	}
	catch(Exception err)
	{
		JOptionPane.showMessageDialog(null,err);
	}
	table = new JTable(data,column)
	{
		public boolean isCellEditable(int row,int col)
		{
			return false;
		}
	};
	table.addMouseListener(this);
	sp=new JScrollPane(table);
	sp.setBounds(100,100,700,160);
	f.add(sp);
	head = new JLabel("<html><body><font size=10>Return the book</font></body></html>");
	head.setBounds(10,10,275,50);
	f.add(head);
	submit = new JButton("submit");
	submit.setBounds(240,300,110,20);
	submit.addActionListener(this);
	f.add(submit);
	done = new JButton("Done");
	done.setBounds(460,300,110,20);
	done.addActionListener(this);
	f.add(done);
	f.setSize(850,400);
	f.setLocation(250,150);
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
	int rowsel = table.getSelectedRow();
	int colsel = table.getSelectedColumn();
	System.out.println(rowsel);
	System.out.println(colsel);
	String tdata1;
	String tdata = String.valueOf(table.getValueAt(rowsel,colsel));
	if(colsel!=0)
	{
		tdata1 = String.valueOf(table.getValueAt(rowsel,colsel-1));
	}
	else
	{
		tdata1 = "";
	}
	System.out.println(tdata);
	if(tdata.equals(""))
	{
		submit.setEnabled(true);
	}
	else
	{
		submit.setEnabled(false);
	}
	if(tdata.equals("") && tdata1.equals(""))
	{
		submit.setEnabled(false);
	}
}

public void actionPerformed(ActionEvent e)
{
	Object ob = e.getSource();
	if(ob == submit)
	{
		String qleftcopysql="";
		currentdate = new SimpleDateFormat("yyyy-MM-dd");
		d = new java.util.Date();
		data[loop-1][3] = currentdate.format(d);
		System.out.println(currentdate.format(d));
		sp.setVisible(false);
		sp.setVisible(true);
		String query123 = "update "+namespace+"_books set return_date = '"+currentdate.format(d)+"' where Sr_no = '"+data[loop-1][0]+"'";
		try
		{
		String query = "Select * from all_books where Sr_No = '"+data[loop-1][0]+"'";
		// create the java statement
		Statement st = conn.createStatement();
		// execute the query, and get a java resultset
		ResultSet rs = st.executeQuery(query);
		// iterate through the java resultset
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(query123);
		while (rs.next())
		{
			count = 1;
			qleftcopysql = rs.getString("Quantites_left");
		}
		System.out.println(qleftcopysql);
		int news = Integer.parseInt(qleftcopysql);
		int x = news+1;
		String hgjfk = Integer.toString(x);
		String bookupdate = "update all_books set quantites_left = '"+hgjfk+"' where Sr_No = '"+data[loop-1][0]+"'";
		Statement fgf = conn.createStatement();
		fgf.executeUpdate(bookupdate);
		stmt.close();
		}
		catch(Exception errrrr)
		{
			JOptionPane.showMessageDialog(null,errrrr);
		}
		if(count == 0)
		{
			JOptionPane.showMessageDialog(null,"Error while returning the book!");
		}
	}
	else if(ob == done)
	{
		f.setVisible(false);
	}
}
public static void main(String z[])
{
	returnb ob = new returnb("nidhish");
}

}