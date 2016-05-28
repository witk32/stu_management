package student_system_management;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class stu_login extends JFrame implements ActionListener,KeyListener{
	JLabel jl1,jl2,jl3;
	JButton jb1,jb2;
	JTextField jtf1,jtf2;
	JPasswordField jpf;
	JPanel jp1,jp2,jp3,jp4;

	public stu_login()
	{
		this.setTitle("学生管理系统 github v1.2");
		this.setSize(800, 600);
		this.setLocation(250, 100);
		this.setLayout(new GridLayout(4,1));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//初始化组件

		jl1=new JLabel("学生管理系统");
		jl2=new JLabel("用户名:");
		jl3=new JLabel("密  码:");
		Font font=new Font("Arial",Font.BOLD,25);
		jl1.setFont(font);

		jtf1=new JTextField(20);
		//添加enter激活
		jtf1.addKeyListener(this);
		jpf=new JPasswordField(20);
		//添加enter激活
		jpf.addKeyListener(this);

		jb1=new JButton("登录");
		jb2=new JButton("退出");

		//加入JPanel 和JFrame

		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();

		jp1.add(jl1);
		jp2.add(jl2);
		jp2.add(jtf1);
		jp3.add(jl3);
		jp3.add(jpf);
		jp4.add(jb1);
		jp4.add(jb2);




		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		//注册监听
		jb1.addActionListener(this);
		jb1.setActionCommand("log");
		jb2.addActionListener(this);
		jb2.setActionCommand("esc");
	}


	public static void main(String[] args) {

		stu_login sl=new stu_login();
		sl.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("log"))
		{
			String log_id=jtf1.getText();
			String log_pw=new String(jpf.getPassword());
			db1 db=new db1();
			//获得返回值
			String db1_re=db.db1(log_id, log_pw);
			//判断返回值得到帐号密码情况
			switch(db1_re)
			{
			case "error_pw":
				JOptionPane.showMessageDialog(null,"密码输入错误，请重新输入", "密码错误", JOptionPane.ERROR_MESSAGE);
				break;
			case "error_user_notfound":
				JOptionPane.showMessageDialog(null,"用户不存在，请重新输入", "用户不存在", JOptionPane.ERROR_MESSAGE);
				break;
			case "AD":
				JOptionPane.showMessageDialog(null,"欢迎您："+log_id+"管理员！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
				//打开AD表单  关闭当前窗口
				stu_AD sl=new stu_AD();
				sl.setVisible(true);
				this.dispose();

				break;
			case "TEACHER":
				JOptionPane.showMessageDialog(null,"欢迎您："+log_id+"老师！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
				//打开TEACHER表单 关闭当前窗口
				stu_TEACHER st=new stu_TEACHER();
				st.setVisible(true);
				this.dispose();
				break;
			case "STUDENT":
				JOptionPane.showMessageDialog(null,"欢迎您："+log_id+"同学！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
				//打开STUDENT表单 关闭当前窗口
				stu_STUDENT ss=new stu_STUDENT(log_id);
				ss.setVisible(true);
				this.dispose();
				break;
			}

		}
		else if(e.getActionCommand().equals("esc"))
		{
			System.exit(0);
		}

	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
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
