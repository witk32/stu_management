package student_system_management;

import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.*;

public class AD_add extends JDialog implements ActionListener,KeyListener{
	Vector Vjl=new Vector();
	Vector<JTextField> Vjtf=new Vector<JTextField>();
	JPanel jp1,jp2,jp3;
	int column;
	JButton jb1;
	String add_gr;
	user_model um;
	user_model um1;

	
	public AD_add(Frame owner,String tittle,boolean modal,int col,String[] colname)
	{
		super(owner,tittle,modal);//调用父类构造方法，达到模式对话框效果
		column=col;
//		for(int i=0;i<col;i++)
//		{
//			JLabel jl=new JLabel(colname[i]);
//			Vjl.add(jl);
//		}
//		jp1=new JPanel();
//		for(int i=0;i<Vjl.size();i++)
//		{
//			jp1.add((Component) Vjl.get(i));	
//		}	
//		this.add(jp1);
//		
//		for(int i=0;i<col;i++)
//		{
//			JTextField jtf=new JTextField(10);
//			Vjtf.add(jtf);
//		}
//		
//		jp2=new JPanel();
//		for(int i=0;i<Vjtf.size();i++)
//		{
//			jp2.add((Component) Vjtf.get(i));	
//		}	
//		this.add(jp2);
		
		

		for(int i=0;i<col;i++)
		{
			JLabel jl=new JLabel(colname[i],JLabel.CENTER);
			this.add(jl);
		}
		for(int i=0;i<col;i++)
		{
			JTextField jtf=new JTextField(10);
			jtf.addKeyListener(this);
			Vjtf.add(jtf);
			this.add((Component) Vjtf.get(i));
		}
		for(int i=0;i<col-1;i++)
		{
			JLabel jl=new JLabel(" ");
			this.add(jl);
		}
		jb1=new JButton("确定增加");
		jb1.addActionListener(this);
		this.add(jb1);

		
		

		this.setSize(800, 150);
		this.setLocation(250, 300);
		this.setLayout(new GridLayout(3,col));
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String num=Vjtf.get(0).getText();
		String id=Vjtf.get(1).getText();
		String sex=Vjtf.get(2).getText();
		String gr=Vjtf.get(3).getText();
		String class1=Vjtf.get(4).getText();
		String qizhong=Vjtf.get(5).getText();
		if(qizhong.length()==0){
			qizhong="0";
		}
		String qimo=Vjtf.get(6).getText();
		if(qimo.length()==0){
			qimo="0";
		}
		String pw=Vjtf.get(7).getText();
		String values ="";
		for(int i=0;i<column-3;i++)
		{
			String a="'"+Vjtf.get(i).getText()+"'";
			values+=(a+",");
		}
		values=values+"'"+Vjtf.get(7).getText()+"'";
	
		
		
		if(e.getSource()==jb1)
		{
			if(gr.equals("STUDENT"))
			{
				String a_teacher="'"+num+"','"+id+"','"+class1+"','"+sex+"',"+qizhong+","+qimo;
				String sql1="insert into student values("+a_teacher+")";
				String sql="insert into user values("+values+")";
				um=new user_model(sql1);
				if(um.ifchange!=0)
				{
					JOptionPane.showMessageDialog(null,"在学生表添加成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
				}
				um1=new user_model(sql);
				if(um1.ifchange!=0)
				{
					JOptionPane.showMessageDialog(null,"在user添加成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}
			}
			else if(gr.equals("TEACHER")){
				String sql="insert into user values("+values+")";
				um=new user_model(sql);
				if(um.ifchange!=0)
				{
					JOptionPane.showMessageDialog(null,"在user表添加成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}
			}
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
