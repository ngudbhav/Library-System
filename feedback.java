import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;
import java.lang.*;
class feedback extends JPanel implements ActionListener,Runnable
{
	JLabel name;
	String naamget=null;
	String msgget=null;
	String subject = null;
	String body = null;
	int i=0;
	JLabel msg;
	JProgressBar mailsend;
	JLabel inmfo;
	JButton submit;
	JTextField naam;
	JTextField message1;
	feedback(String l,String m)
	{
		msgget = m;
		naamget = l;
	}
	feedback()
	{
		setLayout(null);
		name = new JLabel("Name");
		name.setBounds(50,30,90,30);
		add(name);
		inmfo = new JLabel("Leave the name field blank to submit feedback as anonymous");
		inmfo.setBounds(220,2,500,30);
		add(inmfo);
		msg = new JLabel("Enter your message");
		msg.setBounds(50,120,150,30);
		add(msg);
		naam = new JTextField();
		naam.setBounds(50,70,250,30);
		add(naam);
		message1 = new JTextField();
		message1.setBounds(50,170,250,70);
		add(message1);
		submit = new JButton("SUBMIT");
		submit.setBounds(390,120,150,20);
		submit.addActionListener(this);
		add(submit);
		setSize(600,500);
		setLocation(100,100);	
		mailsend=new JProgressBar(1,100);
		mailsend.setBounds(400,40,160,30);
		mailsend.setValue(0);
		mailsend.setVisible(true);
		mailsend.setStringPainted(true);
		add(mailsend);
		setVisible(true);	
		}
//RunnableClass rc = new RunnableClass();
//Thread thread = new Thread(new feedback())
public void run()
	{
		if(naamget.equals(""))
		{
			naamget = "Anonymous";
		}
		else
		{
			//naamget = naam.getText();
		}
	System.out.println("Sending mail...");
	String USER_NAME = "ngudbhav05@hotmail.com";  // GMail user name (just the part before "@gmail.com")
    String PASSWORD = "UdbhavKarishma"; // GMail password
    String RECIPIENT = "ngudbhav05@gmail.com";

   	String from = USER_NAME;
        String pass = PASSWORD;
        String to = RECIPIENT; // list of recipient email addresses
	String subject = ("Feedback by "+naamget+" ");
	System.out.println(subject);
	String body = msgget;
	System.out.println(body);
	//subject.equals(");
        Properties props = System.getProperties();
        String host = "smtp.live.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try 
		{
	            message.setFrom(new InternetAddress(from));
        	    InternetAddress toAddress = new InternetAddress(to);
	           // To get the array of addresses
        	    //for( int i = 0; i < to.length; i++ ) {
                	//toAddress[i] = new InternetAddress(to[i]);
           		//}
            		message.addRecipient(Message.RecipientType.TO, toAddress);
            		message.setSubject(subject);
            		message.setText(body);
            		Transport transport = session.getTransport("smtp");
            		transport.connect(host, 587, from, pass);
            		transport.sendMessage(message, message.getAllRecipients());
			body.equals("");
			subject.equals("");
            		transport.close();
			JOptionPane.showMessageDialog(null,"Mail-Sent");
        	}
        	catch(Exception newerror)
		{
			body.equals("");
			subject.equals("");
			JOptionPane.showMessageDialog(null,"Can not send mail");
			System.out.println(newerror);
		}
}
public void actionPerformed(ActionEvent e)
{
		String k=naam.getText();
		String o=message1.getText();//System.out.println(naamget);
		Thread thread = new Thread(new feedback(k,o));
		thread.start();
		iterate();
		i=0;		//String naamget, String msgget
}
		//};
public void iterate(){    
    while(i<=100){ 
	mailsend.paintImmediately(0, 0, 200, 200);   
	mailsend.setValue(i);    
	i=i+1;    
	try{Thread.sleep(270);}catch(Exception e){}    
    }
}
public static void main(String z[])
{
}
}
