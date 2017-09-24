import javax.swing.*;
class newsrecord extends JPanel
{
	JLabel head;
	JLabel title;
	JLabel id;
	JTextField t1;
	JTextField i1;
	JButton submit;
	newsrecord()
	{
	setLayout(null);
	head = new JLabel("<html><body><font size=10>Save the book</font></body></html>");
	head.setBounds(10,10,275,50);
	add(head);
	title = new JLabel("Enter Date");
	title.setBounds(10,100,100,30);
	add(title);
	id = new JLabel("Enter The name");
	id.setBounds(10,150,100,30);
	add(id);
	t1 = new JTextField();
	t1.setBounds(150,100,100,30);
	add(t1);
	i1 = new JTextField();
	i1.setBounds(150,150,100,30);
	add(i1);
	submit = new JButton("SUBMIT");
	submit.setBounds(90,280,110,20);
	add(submit);
	setSize(300,350);
	setLocation(450,250);
	setVisible(true);
	JLabel title;
	JLabel id;
	JLabel author;
	}





public static void main(String z[])
{
	newsrecord ob = new newsrecord();
}

}