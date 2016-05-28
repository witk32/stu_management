package student_system_management;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;

public class stu_AD extends JFrame implements ActionListener,KeyListener{
	JButton jb1,jb1_1,jb2,jb3,jb4,jb5;
	JPanel jp1,jp2,jp3,jp4;
	JLabel jl1,jl2,jl3,jl4;
	JTextField jtf1;
	JTable jtb=null;
	JScrollPane jsp=null;
	String selname;
	user_model um;

	
	public stu_AD()
	{
		this.setSize(800,600);
		this.setLocation(250, 100);
		this.setTitle("学生管理系统--管理员界面");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLayout(new GridLayout(4,1));
		
		//组件添加
		//上:
		jp1=new JPanel();
		jtf1=new JTextField(20);
		jb1=new JButton("查询");
		jb1_1=new JButton("返回");
		jl1=new JLabel("请输入名字:");
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jb1);
		jp1.add(jb1_1);
		//下
		jb2=new JButton("添加");
		jb3=new JButton("修改");
		jb4=new JButton("删除");
		jb5=new JButton("返回登录界面");
		jp2=new JPanel();
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.add(jb5);
		//注册监听
		//---上----查询
		jb1.addActionListener(this);
		jb1_1.addActionListener(this);
//		jb1.setActionCommand("sel");
		jtf1.addKeyListener(this);
		
		//下
		jb2.addActionListener(this);
//		jb2.setActionCommand("add");
		jb3.addActionListener(this);
//		jb3.setActionCommand("edit");
		jb4.addActionListener(this);
//		jb4.setActionCommand("del");
		jb5.addActionListener(this);
//		jb5.setActionCommand("re_login");
		
		
		
		//------组建添加 中
		//数据库连接模块
		um=new user_model();
		jtb=new JTable(um);
		jsp=new JScrollPane(jtb);
		
		this.add(jp1,"North");
		this.add(jsp,"Center");
		this.add(jp2,"South");

		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		//根据姓名查询
		if(e.getSource()==jb1)
		{
			selname=jtf1.getText().trim();
			String sqlselect1="(select u.num,u.id,u.sex,u.gr,u.class,s.期中成绩,s.期末成绩,u.pw from user u left join student s on u.num=s.num) lj ";
			String sqlselect="select * from "+sqlselect1+"where id like'"+selname+"%' or id like '%"+selname+"'or id like '%"+selname+"%'" ;

			um=new user_model(sqlselect);
			jtb.setModel(um);
			
			
		}
		//返回
		else if(e.getSource()==jb1_1)
		{
			um=new user_model();
			jtb.setModel(um);
		}
		//返回登录界面
		else if(e.getSource()==jb5)
		{
			stu_login sl=new stu_login();
			sl.setVisible(true);
			this.dispose();
		}
		//添加学生
		else if(e.getSource()==jb2)
		{
			int col=um.column;
			String[] colname=um.colname;
			AD_add aa=new AD_add(this, "增加成员信息", true,col,colname);
			//添加完成后更新表格
			um=new user_model();
			jtb.setModel(um);
			
		}
		//删除信息
		else if(e.getSource()==jb4)
		{
			int rowNum=this.jtb.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "请选择其中一条记录");
				return;
			}
			//因为无论是哪个按钮的操作，返回主界面后都是最新更新的um值，所以这里可以直接调用um,当然如果你新建一个um也是可以的。
			String selectNum=(String) um.getValueAt(rowNum, 0);
			String getgr=(String) um.getValueAt(rowNum, 3);
			
			delete da=new delete(this,"确认删除",true,selectNum,getgr);
			if(da.ifornot==true){
				String delsql_user="delete from user where num='"+selectNum+"'";
				String delsql_stu="delete from student where num='"+selectNum+"'";
				um=new user_model(delsql_user);
				um=new user_model(delsql_stu);
			}

			um=new user_model();
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
			edit_model ae=new edit_model(this, "修改成员信息", true,col,colname,rowdata,"AD");
			//添加完成后更新表格
			um=new user_model();
			jtb.setModel(um);
		
		}
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			jb1.doClick();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
