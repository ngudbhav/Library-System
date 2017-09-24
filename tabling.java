import java.awt.*;
import javax.swing.*;
class tabling
{
	JFrame f;
	JTable table;
	tabling()
	{
		JFrame f = new JFrame("Hello");
		f.setLayout(null);
		Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3","Row1-Column4" },
        				{ "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column4" } };
		Object columnNames[] = {"columnone", "columntwo", "columnthree", "columnfour"};
		JTable table = new JTable(rowData, columnNames);
		table.setBounds(10,10,260,260);
		f.add(table);
		f.setVisible(true);
		f.setSize(300,300);
		f.setLocation(600,250);	
	}



public static void main(String z[])
{
tabling ob = new tabling();
}
}