import javax.swing.*;
class mgzrecord
{
	JFrame f;
	JLabel head;
	JLabel title;
	JLabel id;
	JTextField t1;
	JTextField i1;
	JButton submit;
	ButtonGroup g;
	JRadioButton free;
	JRadioButton purchased;
	mgzrecord()
	{
	f = new JFrame("Save the book");
	f.setLayout(null);
	head = new JLabel("<html><body><font size=10>Save the book</font></body></html>");
	head.setBounds(10,10,275,50);
	f.add(head);
	title = new JLabel("Enter name:");
	title.setBounds(10,100,100,30);
	f.add(title);
	id = new JLabel("Enter a unique ID");
	id.setBounds(10,150,100,30);
	f.add(id);
	t1 = new JTextField();
	t1.setBounds(150,100,100,30);
	f.add(t1);
	i1 = new JTextField();
	i1.setBounds(150,150,100,30);
	f.add(i1);
	submit = new JButton("SUBMIT");
	submit.setBounds(90,280,110,20);
	f.add(submit);
	g = new ButtonGroup();
	free = new JRadioButton("Free");
	free.setBounds(150,200,50,15);
	g.add(free);
	f.add(free);
	purchased = new JRadioButton("Purchased");
	purchased.setBounds(150,215,100,30);
	purchased.setSelected(true);
	g.add(purchased);
	f.add(purchased);
	f.setSize(300,350);
	f.setLocation(450,250);
	f.setVisible(true);
	JLabel title;
	JLabel id;
	JLabel author;
	}





public static void main(String z[])
{
	mgzrecord ob = new mgzrecord();
}

}