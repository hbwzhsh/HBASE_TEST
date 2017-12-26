package com.ande.temp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * mysql驱动
 * 类功能说明：
 *
 * @author Ande,微博@阿里七哥，微信:Alilangman
 * @version 
 *       1.0 , 2016年3月7日 下午9:03:44
 * 
 *  教师是人类灵魂的工程师
 */
public class MysqlUtil {
	private static final String URL = "jdbc:mysql://192.168.1.11:3306/HFStats?useUnicode=true&characterEncoding=UTF8";

	public static Connection getconn(){
		Connection conn = null ;
		//加载mysql驱动
		try {
			Class.forName("com.mysql.jdbc.Driver") ;
			//创建mysql的数据库连接
			conn = DriverManager.getConnection(URL,"root","hfwl@2109") ;
		} catch (Exception e) {			
			e.printStackTrace();
		}	
		return conn ;
	}
	
	public static void closeConn(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
	}
	
	public static List<TempBean> querySql(String sql){
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Connection conn = MysqlUtil.getconn() ;
		Statement stat = null;
		List<TempBean> res = new ArrayList<TempBean>() ;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);	
			while(rs.next()){
				TempBean tb = new TempBean() ;
				tb.setId(rs.getInt(1));
				tb.setHeight(rs.getString(2));
				res.add(tb) ;
			}			
		} catch (Exception e) {			
			e.printStackTrace();
		}finally{
			try {
				if(stat != null)stat.close();
				closeConn(conn) ;
			} catch (Exception e) {				
			}
		}
		return res ;
	}
	
	public static void main(String[] args) {
//		 // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
//         // 避免中文乱码要指定useUnicode和characterEncoding
//		 // 数据库这里已经创建好了，就是pros
//		String url = "jdbc:mysql://192.168.186.15:3306/pros?" +
//					"user=root&password=123&useUnicode=true&characterEncoding=UTF8";
//		try {
//			//加载mysql驱动
//			Class.forName("com.mysql.jdbc.Driver") ;
//			
//			//创建mysql的数据库连接
//			conn = DriverManager.getConnection(url) ;
//			
//			// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
//			Statement stat = conn.createStatement() ;
//			
//			String sql = "select * from temps" ;
//			ResultSet rs = stat.executeQuery(sql);	
//			while(rs.next()){
//				System.out.println(rs.getInt(1)+"========"+rs.getString(2));
//			}
//			
//			//关闭资源
//			stat.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String sql = "select id,hight from temp limit 20" ;
		List<TempBean> res = querySql(sql) ;
		for(TempBean tb : res){
			System.out.println("气象站ID：\t" + tb.getId());
			System.out.println("气象站ID：\t" + tb.getId());
			System.out.println("该气象站的近30年来的平均气温是：\t" + tb.getHight());
		}
	}
}
