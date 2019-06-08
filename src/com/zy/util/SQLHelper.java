package com.zy.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import com.zy.model.User;

public class SQLHelper {

	//打开与数据源的连接
	private static Connection conn=null;
	/*PreparedStatement 实例包含已编译的 SQL 语句。
	包含于 PreparedStatement 对象中的 SQL 语句可具有一个或多个 IN 参数。
	IN参数的值在 SQL 语句创建时未被指定。相反的，该语句为每个 IN 参数保留一个问号（“？”）作为占位符。
	每个问号的值必须在该语句执行之前，通过适当的setXXX 方法来提供。
	用于设置发送给数据库以取代 IN 参数占位符的值。
	同时，三种方法 execute、 executeQuery 和 executeUpdate 已被更改以使之不再需要参数。*/
	private static PreparedStatement ps=null;
	/*数据库结果集的数据表，通常通过执行查询数据库的语句生成。
	 * ResultSet 对象具有指向其当前数据行的指针
	 * next 方法将指针移动到下一行；
	 * 因为该方法在 ResultSet 对象中没有下一行时返回 false，所以可以在 while 循环中使用它来迭代结果集。*/
	private static ResultSet rs=null;
	private static String url="";
	private static String user="";
	private static String password="";
	private static String driver="";
	/*使用一种键值对的形式来保存属性集 它的键和值都是字符串类型。*/
	private static Properties pp=null;
	/*标志那些从不同数据起源产生输入的类*/
	private static InputStream fis=null;
	static {
			try {//try catch快捷键Alt+shift+z
				pp=new Properties();
				//读取属性文件//从dbinfo.propertis文件中读取配置信息
				fis=SQLHelper.class.getClassLoader().getResourceAsStream("dbinfo.properties");
				//加载属性列表。从.properties属性文件对应的文件输入流中，加载属性列表到Properties类对象。
				pp.load(fis);
				url=pp.getProperty("url");//value=key
				user=pp.getProperty("user");
				password=pp.getProperty("password");
				driver=pp.getProperty("driver");
				//加载驱动类
				Class.forName(driver);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(fis!=null) {
					try {
						fis.close();            
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	}
	//得到连接
	public static Connection getConnection() {
		try {
			//获取数据连接
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//统一的select,要程序员后续代码实现关闭数据资源
	public static ResultSet executeQuery(String sql,String []parameters) {
		try {
			//创建一个ps
			conn=getConnection();
			//SQl语句
			//PreparedStatement对象允许数据库预编译SQL语句
			ps=conn.prepareStatement(sql);
			if(parameters!=null) {
				for(int i=0;i<parameters.length;i++) {
					//给？赋值
					ps.setString(i+1, parameters[i]);		
				}
			}
			//结果传给rs
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//数据资源可以及时关闭
	public static ArrayList<User> executeQueryUser(String sql,String []parameters){
		ArrayList<User> arraylist = new ArrayList<User>();
		//<User>为泛型，arraylistt只能放入User对象
		try {
			//创建一个ps
			conn=getConnection();
			//SQl语句
			//PreparedStatement对象允许数据库预编译SQL语句
			ps=conn.prepareStatement(sql);
			if(parameters!=null) {
				for(int i=0;i<parameters.length;i++) {
					//给？赋值
					ps.setString(i+1, parameters[i]);		
				}
			}
			
			//方法成功执行SELECT语句  后，将返回一个包含有结果数据的ResultSet对象，要从该对象中才能获取数据
			rs=ps.executeQuery();
			//遍历结果集，迭代器
		    //rs.next()方法作用：循环将结果集游标往下移动，到达末尾返回false  
			while(rs.next()) {
				//创建User对象
				User user = new User();
				//从数据库得到相应字段的值，放入User对象中
				user.setId(rs.getInt(1));//rs.getInt(1)根据列的序号进行查询
				//可改为user.setId(rs.getInt("id"));//rs.getInt("id")根据列名进行查询
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setGrade(rs.getInt(4));
				user.setEmail(rs.getString(5));	
				//将User对象放入ArrayList中
				arraylist.add(user);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//及时关闭数据资源
			SQLHelper.close(SQLHelper.getRs(),SQLHelper.getPs(),SQLHelper.getConn());
		}
		return arraylist;
	}
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		// TODO Auto-generated method stub
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs=null;
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps=null;
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn=null;
		}
	}
	public static Connection getConn() {
		// TODO Auto-generated method stub
		return null;
	}
	public static PreparedStatement getPs() {
		// TODO Auto-generated method stub
		return null;
	}
	public static ResultSet getRs() {
		// TODO Auto-generated method stub
		return null;
	}
	//update insert delete
	public static void exectueUpdate(String sql,String[] parameters) {
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			if(parameters!=null) {
				for(int i=0;i<parameters.length;i++) {
					ps.setString(i+1, parameters[i]);					
				}
				ps.executeUpdate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,ps,conn);
		}
	}
	public static void executeUpdate2(String sql) {
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,ps,conn);
		}
	}
}
