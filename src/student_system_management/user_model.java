package student_system_management;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;


public class user_model extends AbstractTableModel{
	
	Vector rowData,columnNames;
	String[] colname;
	String selname;
	int column;
	PreparedStatement ps=null;
	Connection con1=null;
	ResultSet rs=null;
	String url="jdbc:mysql://45.78.18.79:3306/stu?useSSL=false";
	String user="";
	String code="";
	String SQL_Default="select u.num,u.id,u.sex,u.gr,u.class,s.期中成绩,s.期末成绩,u.pw from user u left join student s on u.num=s.num order by num";
	int ifchange;
	FileReader fr=null;
	BufferedReader bf=null;
	

	//整表查询  构造函数
	public user_model()
	{
		this.gettable(SQL_Default);
	}
	//executeQuery 构造函数
	public user_model(String sqlselect)
	{
		this.gettable(sqlselect);
	}

	//连接数据库模块
	public void gettable(String sql)
	{
	try {
			fr=new FileReader("src/db1/db_user.txt");
			bf=new BufferedReader(fr);

			user=bf.readLine();
			code=bf.readLine();
			Class.forName("com.mysql.jdbc.Driver");
			con1 = DriverManager.getConnection(url,user,code);	
			ps=con1.prepareStatement(sql);
			String e_or_u=sql.substring(0,6);
			//判断是查询还是增删改
			if(e_or_u.equals("select"))
			{
				rs=ps.executeQuery();				
			}else{
				ifchange=ps.executeUpdate();
			}
			
			columnNames=new Vector();
			rowData=new Vector();
			//获取列数
			column=rs.getMetaData().getColumnCount();
			//获取列名,添加列名到table
			colname=new String[column];
			for(int coi=0;coi<column;coi++)
			{
				colname[coi]=rs.getMetaData().getColumnName(coi+1);
				columnNames.add(colname[coi]);
			}	
			while(rs.next())
			{
				Vector hang=new Vector();
				//获取信息
				for(int n=0;n<column;n++)
				{
					hang.add(rs.getString(n+1));
				}
				
				rowData.add(hang);
				
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
	}
	
	//得到列数
	@Override
	public int getRowCount() {
		return this.rowData.size();
	}
	//得到行数
	@Override
	public int getColumnCount() {
		return this.column;
	}
	//得到列名
	@Override
	public String getColumnName(int column1) {
		return (String)this.columnNames.get(column1);

	}

	//得到某行某列的数据
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
	}

}
