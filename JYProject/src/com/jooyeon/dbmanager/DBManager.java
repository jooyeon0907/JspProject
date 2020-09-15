package com.jooyeon.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBManager {
	private static Connection conn;
	public DBManager() {conn = null; }
//	public static Connection getConn() { return conn;}
	
	public Connection getConnection() throws Exception{
		Context initContext = new InitialContext();
		Context evcContext = (Context) initContext.lookup("java:/comp/env");
	//	DataSource db = (DataSource) evcContext.lookup("jdbc/joooo1234");
		DataSource db = (DataSource) evcContext.lookup("jdbc/jyproject");
		conn=db.getConnection();
	/*	try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(								
				//	"jdbc:mysql://localhost:3306/joooo1234?useSSL=false","joooo1234","wn@1004*dusl");
					"jdbc:mysql://localhost:3306/jyproject?useSSL=false","root","1234");
			if(conn!=null) { System.out.println("연동성공!");}
		}catch(Exception e) {e.printStackTrace();}*/
		return conn;
	}
	
	
	
}//end class
