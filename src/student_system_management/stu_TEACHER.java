package student_system_management;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;

public class stu_TEACHER extends JFrame implements ActionListener,KeyListener{
	JButton jb1,jb2,jb3,jb4,jb5;
	JPanel jp1,jp2,jp3,jp4;
	JLabel jl1,jl2,jl3,jl4;
	JTextField jtf1;
	JTable jtb=null;
	JScrollPane jsp=null;
	Vector rowData,columnNames;
	String[] colname;
	int row;
	user_model um;
	String sql="select * from student";
	
	public stu_TEACHER()
	{
		this.setSize(800,600);
		this.setLocation(250, 100);
		this.setTitle("学生管理系统--教师界面");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLayout(new GridLayout(4,1));
		
		//组件添加
		//上:
		jp1=new JPanel();
		jtf1=new JTextField(20);
		jtf1.addKeyListener(this);
		jb1=new JButton("查询");
		jb2=new JButton("返回");
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jl1=new JLabel("请输入名字:");
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jb1);
		jp1.add(jb2);
		//下
		//jb2=new JButton("添加");
		jb3=new JButton("修改成绩");
		jb3.addActionListener(this);
		jb4=new JButton("返回登录界面");
		jp2=new JPanel();
		//jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//注册监听
		
		//----下
		jb4.addActionListener(this);
		jb4.setActionCommand("re_login");
		
		
		//中
		//调用model
	
			
			um=new user_model(sql);
			jtb=new JTable(um);
			jsp=new JScrollPane(jtb);
		
		this.add(jp1,"North");
		this.add(jsp,"Center");
		this.add(jp2,"South");

	}
	
	
	public void actionPerformed(ActionEvent e) {
		//返回主界面
		if(e.getActionCommand().equals("re_login"))
		{
			stu_login sl=new stu_login();
			sl.setVisible(true);
			this.dispose();
			
		}
		//查询
		else if(e.getSource()==jb1){
			String getname=jtf1.getText();
			String sqlselect="select * from student where id like'"+getname+"%' or id like '%"+getname+"'or id like '%"+getname+"%'" ;
			um=new user_model(sqlselect);
			jtb.setModel(um);
		}
		//返回
		else if(e.getSource()==jb2)
		{
			um=new user_model(sql);
			jtb.setModel(um);
		}
		//修改
		else if(e.getSource()==jb3)
		{
			int rowNum=this.jtb.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "请选择其中一条记录");
				//必须要有return,否则出错
				return;
			}
			//因为无论是哪个按钮的操作，返回主界面后都是最新更新的um值，所以这里可以直接调用um,当然如果你新建一个um也是可以的。
			String[] rowdata = new String[um.column];
			for(int i=0;i<um.column;i++){
				String selectNum=(String) um.getValueAt(rowNum, i);
				rowdata[i]=selectNum;
			}
			int col=um.column;
			String[] colname=um.colname;
			edit_model ae=new edit_model(this, "修改学生信息", true,col,colname,rowdata,"TEA");
			//添加完成后更新表格
			um=new user_model(sql);
			jtb.setModel(um);
		}
	}

	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		//定义enter快速激活查询
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			jb1.doClick();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
