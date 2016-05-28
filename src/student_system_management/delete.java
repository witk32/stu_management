package student_system_management;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class delete extends JDialog implements ActionListener,KeyListener,FocusListener{
	JLabel jl1;
	JButton jb1,jb2;
	JPanel jp1,jp2;
	String whatbutton;
	boolean ifornot=false;
	public delete(Frame owner,String tittle,boolean modal,String stu,String gr)
	{
		super(owner,tittle,modal);//调用父类构造方法，达到模式对话框效果
		
		//添加按钮组件；
		if(gr.equals("STUDENT")){
			jl1=new JLabel("确定要删除学号为："+stu+"的学生？");
		}else if(gr.equals("TEACHER")){
			jl1=new JLabel("确定要删除工号为："+stu+"的老师？");
		}
		jb1=new JButton("确定");
		jb2=new JButton("取消");
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb1.addKeyListener(this);
		jb2.addKeyListener(this);
		jb1.addFocusListener(this);
		jb2.addFocusListener(this);
		
		jp1=new JPanel();
		jp2=new JPanel();
		
		jp1.add(jl1);
		jp2.add(jb1);
		jp2.add(jb2);
		this.add(jp1,BorderLayout.NORTH);
		this.add(jp2,BorderLayout.CENTER);
		
		this.setSize(300, 100);
		this.setLocation(500,300);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
//		this.setLayout(new GridLayout(1));
		
		
		
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1){
			ifornot=true;
			this.dispose();
		}
		else if(e.getSource()==jb2){
			this.dispose();
		}
		
	}
	public void focusGained(FocusEvent e){
		if(e.getSource()==jb1){
			whatbutton="确定";	
		}else if(e.getSource()==jb2){
			whatbutton="取消";
		}
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(whatbutton.equals("确定"))
			{
				ifornot=true;
				this.dispose();
			}else if(whatbutton.equals("取消")){
				this.dispose();
			}
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

}
