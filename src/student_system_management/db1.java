package student_system_management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class db1 {
	//定义re为返回值
	String id,pw,re;
	public String db1(String id1,String pw1)
	{
			id=id1;
			pw=pw1;
			PreparedStatement ps=null;
			Connection con1=null;
			ResultSet rs=null;
			String url="jdbc:mysql://45.78.18.79:3306/stu?useSSL=false";
			String user="";
			String code="";
			FileReader fr=null;
			BufferedReader bf=null;
			try {
				fr=new FileReader("src/db1/db_user.txt");
				bf=new BufferedReader(fr);
				user=bf.readLine();
				code=bf.readLine();
				Class.forName("com.mysql.jdbc.Driver");
				con1 = DriverManager.getConnection(url,user,code);	
				ps=con1.prepareStatement("select * from user");
				rs=ps.executeQuery();
				boolean found=false;
				while(rs.next())
				{
					String sl_id=rs.getString("id");
					String sl_pw=rs.getString("pw");
					if(sl_id.equals(id))
					{
						//ID得到匹配
						found=true;
						//判断密码是否正确
						if(sl_pw.equals(pw1))
						{
							String sl_gr=rs.getString("gr");
							//判断用户组
							switch(sl_gr)
							{
							case "AD":
								re="AD";
								break;
							case "TEACHER":
								re="TEACHER";
								break;
							case "STUDENT":
								re="STUDENT";
								break;
							
							}
							
						}else{
							//密码错误
							re="error_pw";
							break;
						}
						break;
					}

					
				}
				//用户ID没有得到匹配
				if(!found){
					re="error_user_notfound";
				}
				
			} catch (Exception e) {
			}finally{
				try {
					bf.close();
					fr.close();
					if(rs!=null){
						rs.close();
					}
					if(ps!=null){
						ps.close();
					}
					if(con1!=null){
						con1.close();
					}
				} catch (Exception e2) {
				}
			}
		
		return re;
		
	}

}
