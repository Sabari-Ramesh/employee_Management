package employee.management.system;

import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField tusername;
    JPasswordField tpassword;
    JButton login,back;

    Login(){

        JLabel username=new JLabel("Username"); //to display the user name
        username.setBounds(40,20,100,30);
        add(username);

        tusername=new JTextField();   //username textbox
        tusername.setBounds(150,20,150,30);
        add(tusername);

        JLabel password=new JLabel("Password"); //to display the password
        password.setBounds(40,70,100,30);
        add(password);

        tpassword=new JPasswordField(); //password textbox
        tpassword.setBounds(150,70,150,30);
        add(tpassword);

        login =new JButton("LOGIN");
        login.setBounds(150,140,150,30);
        login.setBackground(Color.blue);
        login.setForeground(Color.white);
        login.addActionListener(this); //to navigate to next page
        add(login);

        back =new JButton("BACK");
        back.setBounds(150,180,150,30);
        back.setBackground(Color.blue);
        back.setForeground(Color.white);
        back.addActionListener(this);//to navigate to next page
        add(back);

        ImageIcon i11=new ImageIcon(ClassLoader.getSystemResource("icons/second1.jpg"));
        Image i22=i11.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
        ImageIcon i33=new ImageIcon(i22);
        JLabel imgg=new JLabel(i33);
        imgg.setBounds(350,10,200,250);
        add(imgg);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/LoginB.jpg")); //background image
        Image i2=i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel img=new JLabel(i3);
        img.setBounds(0,0,600,300);
        add(img);

        setSize(600,300);
        setLocation(450,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login){
            try{
                String username=tusername.getText();
                String password=tpassword.getText();

                comm comm=new comm();
                String query="select * from login where username='"+username+"' and password ='"+password+"'";
                ResultSet resultset=comm.statement.executeQuery(query);
                if(resultset.next()){
                    setVisible(false);
                    new Main_class();
                }else{
                    JOptionPane.showMessageDialog(null,"INVALID USERNAME OR PASSWORD");
                }


            }catch(Exception E){
                E.printStackTrace();
            }
        }
        else if(e.getSource()==back){
            System.exit(90);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
