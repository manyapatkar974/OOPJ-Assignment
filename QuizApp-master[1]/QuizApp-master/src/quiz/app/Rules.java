package quiz.app;import javax.swing.*;import java.awt.*;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;public class Rules extends JFrame implements ActionListener {    JButton start, back;    String name;    Rules(String name){        this.name = name;        // Using HTML to underline the text "Code Warriors Presents"        JLabel heading1 = new JLabel("<html><u>Code Warriors Presents</u></html>");        heading1.setBounds(230,100,700,30);        heading1.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));        heading1.setForeground(new Color(22,99,54));        add(heading1);        JLabel heading = new JLabel("Welcome "+ name +" to QUIZ TEST");        heading.setBounds(200,150,700,30);        heading.setFont( new Font("Viner Hand ITC", Font.BOLD, 28));        heading.setForeground(new Color(22,99,54));        add(heading);        JLabel rules = new JLabel();        rules.setBounds(70,150,700,350);        rules.setFont( new Font("Tahoma", Font.PLAIN, 16));        rules.setForeground(new Color(22,99,54));        rules.setText(                "<html>"+                        "1. Participation in the quiz is free and open to all." + "<br><br>" +                        "2. There are a total 10 questions. " + "<br><br>" +                        "3. You only have 15 seconds to answer the questions." + "<br><br>" +                        "4. No cell phones or other secondary devices in the room or test area." + "<br><br>" +                        "5. No talking." + "<br><br>" +                        "</html>"        );        add(rules);        back = new JButton("Back");        back.setBounds(300,500,100,30);        back.setBackground(new Color(22,99,54));        back.setForeground(Color.WHITE);        back.addActionListener(this);        add(back);        start = new JButton("Start");        start.setBounds(450,500,100,30);        start.setBackground(new Color(22,99,54));        start.setForeground(Color.WHITE);        start.addActionListener(this);        add(start);        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/back.png"));        Image i = i1.getImage().getScaledInstance(800,650,Image.SCALE_DEFAULT);        ImageIcon i2 = new ImageIcon(i);        JLabel image = new JLabel(i2);        image.setBounds(0,0,800,650);        add(image);        setSize(800,650);        setLocation(350,100);        setLayout(null);        setUndecorated(true);        setVisible(true);    }    @Override    public void actionPerformed(ActionEvent e) {        if (e.getSource() == start){            setVisible(false);            new Quiz(name);        }else {            setVisible(false);            new Login();        }    }    public static void main(String[] args) {        new Rules("User");    }}