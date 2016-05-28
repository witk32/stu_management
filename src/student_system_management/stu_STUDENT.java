package student_system_management;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;

public class stu_STUDENT extends JFrame implements ActionListener{
	JButton jb1,jb2,jb3,jb4;
	JPanel jp1,jp2,jp3,jp4;
	JLabel jl1,jl2,jl3,jl4;
	JTextField jtf1;
	JTable jtb=null;
	JScrollPane jsp=null;
	Vector rowData,columnNames;
	String[] colname;
	int row;
	String myid;
	user_model um;
	
	public stu_STUDENT(String stu_id)
	{
		myid=stu_id;
		this.setSize(800,600);
		this.setLocation(250, 100);
		this.setTitle("学生管理系统--学生界面");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLayout(new GridLayout(4,1));
		
		//组件添加
		//上:
		jp1=new JPanel();
		//jtf1=new JTextField(20);
		//jb1=new JButton("查询");
		jl1=new JLabel(myid+"同学的信息");
		jp1.add(jl1);
		//jp1.add(jtf1);
		//jp1.add(jb1);
		//下
		//jb2=new JButton("添加");
		//jb3=new JButton("修改");
		jb4=new JButton("返回登录界面");
		jp2=new JPanel();
		//jp2.add(jb2);
		//jp2.add(jb3);
		jp2.add(jb4);
		
		//注册监听
		
		//----下
		jb4.addActionListener(this);
		jb4.setActionCommand("re_login");
		
		
		//中
		//数据库连接模块
		
		String sql="select * from student where id="+"'"+myid+"'";
		um=new user_model(sql);
		jtb=new JTable(um);
		jsp=new JScrollPane(jtb);
		
		this.add(jp1,"North");
		this.add(jsp,"Center");
		this.add(jp2,"South");
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("re_login"))
		{
			stu_login sl=new stu_login();
			sl.setVisible(true);
			this.dispose();
			
		}
	}

}
