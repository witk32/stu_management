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

public class edit_model extends JDialog implements ActionListener,KeyListener{
	Vector Vjl=new Vector();
	Vector<JTextField> Vjtf=new Vector<JTextField>();
	JPanel jp1,jp2,jp3;
	int column;
	JButton jb1;
	String add_gr;
	user_model um;
	user_model um2;
	String[] getrowdata=null;
	String from;

	
	public edit_model(Frame owner,String tittle,boolean modal,int col,String[] colname,String[] rowdata,String from)
	{
		super(owner,tittle,modal);//调用父类构造方法，达到模式对话框效果
		column=col;
		getrowdata=rowdata;
		this.from=from;
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
			jtf.setText(rowdata[i]);
			jtf.addKeyListener(this);
			Vjtf.add(jtf);
			this.add((Component) Vjtf.get(i));
		}
		for(int i=0;i<col-1;i++)
		{
			JLabel jl=new JLabel(" ");
			this.add(jl);
		}
		jb1=new JButton("确认修改");
		jb1.addActionListener(this);
		this.add(jb1);

		
		if(this.from.equals("TEA"))
		{
			Vjtf.get(0).setEditable(false);
			Vjtf.get(1).setEditable(false);
			Vjtf.get(2).setEditable(false);
			Vjtf.get(3).setEditable(false);
		}

		this.setSize(800, 150);
		this.setLocation(250, 300);
		this.setLayout(new GridLayout(3,col));
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource()==jb1)
		{
			if(from.equals("AD")){
				String num=Vjtf.get(0).getText();
				String id=Vjtf.get(1).getText();
				String sex=Vjtf.get(2).getText();
				String gr=Vjtf.get(3).getText();
				String class1=Vjtf.get(4).getText();
				String qizhong=Vjtf.get(5).getText();
				String qimo=Vjtf.get(6).getText();
				String pw=Vjtf.get(7).getText();
				if(gr.equals("STUDENT"))
				{
					
					String sql1="update student set num='"+num+"',id='"+id+"',class='"+class1+"',sex='"+sex+"',期中成绩="+qizhong+",期末成绩="+qimo+" where num='"+getrowdata[0]+"'";
					um=new user_model(sql1);
					String sql="update user set num='"+num+"',id='"+id+"',sex='"+sex+"',gr='"+gr+"',class='"+class1+"',pw='"+pw+"'"+" where num='"+getrowdata[0]+"'";
					um2=new user_model(sql);
					if(um2.ifchange!=0)
					{
						JOptionPane.showMessageDialog(null,"在user表修改成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
					}
					
					if(um.ifchange!=0)
					{
						JOptionPane.showMessageDialog(null,"在学生表修改成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
					}
					
				}
				else if(gr.equals("TEACHER")){
					
				//用where检索必须要用到调用这个类时传进来的参数。
					String sql="update user set num='"+num+"',id='"+id+"',sex='"+sex+"',gr='"+gr+"',class='"+class1+"',pw='"+pw+"'"+" where num='"+getrowdata[0]+"'";
					um=new user_model(sql);
					if(um.ifchange!=0)
					{
						JOptionPane.showMessageDialog(null,"在user表修改成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
					}
				}
			}
			else if(from.equals("TEA"))
			{
				String num=Vjtf.get(0).getText();
				String id=Vjtf.get(1).getText();
				String class1=Vjtf.get(2).getText();
				String sex=Vjtf.get(3).getText();
				String qizhong=Vjtf.get(4).getText();
				String qimo=Vjtf.get(5).getText();

				String sql1="update student set num='"+num+"',id='"+id+"',class='"+class1+"',sex='"+sex+"',期中成绩="+qizhong+",期末成绩="+qimo+" where num='"+getrowdata[0]+"'";
				um=new user_model(sql1);
				if(um.ifchange!=0)
				{
					JOptionPane.showMessageDialog(null,"在学生表修改成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
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
