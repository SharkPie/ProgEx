package de.compuglobalhypermeganet.studentadministration.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class JdbcTemplate {
	private static JdbcTemplate instance;
	private MysqlDataSource dataSource;
	
	public static JdbcTemplate getInstance(){
		if(instance == null){
			instance = new JdbcTemplate();
		}
		return instance;
	}
	//No more Instance from jdbc template editable from 
	private JdbcTemplate(){
		try{
			
			//File reading jdbc.properties to hide connection details 
			Properties jdbcProperties = new Properties();
			InputStream in = new FileInputStream("jdbc.properties");
			jdbcProperties.load(in);
			
			//Mysql datasource for connecting to the database
			dataSource = new MysqlDataSource();
			dataSource.setURL(jdbcProperties.getProperty("url"));
			dataSource.setUser(jdbcProperties.getProperty("username"));
			dataSource.setPassword(jdbcProperties.getProperty("password"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Funktion für Connection Test (Statuslabel)
public Collection<String[]> querys(String eusername, String epassword) {
		
	List<String[]> row = new ArrayList<String[]>();
	if(eusername == null || eusername.length() < 1 || epassword == null || epassword.length()<1){
		return row;
	}	//SQL Statement 
		String sql = "select Student_ID from Student where Student_ID=1";
		Connection connection = null;
		ResultSet resultSet = null; 
		try{
		Connection con = getDataSource().getConnection();
		Statement stmt = con.createStatement(); 
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData metaData = rs.getMetaData();
		int n = metaData.getColumnCount();
				while(rs.next()){
					String[] column = new String[n];
					for (int i = 0; i < column.length; i++) {
						column[i] = rs.getString(i+1);
					}
					row.add(column);
		}
		rs.close();
		con.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	//Function for logging into the database
	public Collection<String[]> query(String username, String password) {
		
		List<String[]> row = new ArrayList<String[]>();
		if(username == null || username.length() < 1 || password == null || password.length()<1){
			return row;
		}
		//SQL Statement. Checking username, password and matrikation status
		String sql = "select Student_ID from Student where Last_Name='" + username + "' and Password='" + password + "' and Matrikulation_Status=1";
		Connection connection = null;
		ResultSet resultSet = null; 
		try{
		Connection con = getDataSource().getConnection();
		Statement stmt = con.createStatement(); 
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData metaData = rs.getMetaData();
		int n = metaData.getColumnCount();
				while(rs.next()){
					String[] column = new String[n];
					for (int i = 0; i < column.length; i++) {
						column[i] = rs.getString(i+1);
					}
					row.add(column);
		}
		rs.close();
		con.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return row;
		
	}


	public synchronized String[][] getEnroledExam(String username, String password) {
		String[][] row = new String[20][2];
		if(username == null || username.length() < 1 || password == null || password.length()<1){
			return row;
		}
		
		
		
		String sql = "select s.Last_Name, c.course_name from course as c, student as s join  exam_current as ec on s.student_id = ec.Student_FK_ID where s.Last_Name='"+username+"' and s.Password='"+password+"' and ec.Course_FK_ID = c.Course_ID;"; 
					
		Connection connection = null;
		ResultSet resultSet = null; 
		try{
		Connection con = getDataSource().getConnection();
		Statement stmt = con.createStatement(); 
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData metaData = rs.getMetaData();
		int n = metaData.getColumnCount();
		int j = 0;
				while(rs.next()){
					String[] column = new String[n];
					for (int i = 0; i < column.length; i++) {
						column[i] = rs.getString(i+1);
					}
					row[j++] = column;
		}
		rs.close();
		con.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public synchronized String[][] getviewGrades(String vGusername, String vGpassword) {
		String[][] row = new String[20][2];
		
		String sql = "select c.Course_Name, ec.Grade_Value from course as c, exam_current as ec join student as s on ec.Student_FK_ID=s.Student_ID where s.Last_Name='"+vGusername+"' and s.Password='"+vGpassword+"' and ec.Course_FK_ID = c.Course_ID and ec.Grade_Value!='Null';"; 
					
		Connection connection = null;
		ResultSet resultSet = null; 
		try{
		Connection con = getDataSource().getConnection();
		Statement stmt = con.createStatement(); 
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData metaData = rs.getMetaData();
		int n = metaData.getColumnCount();
		int j = 0;
				while(rs.next()){
					String[] column = new String[n];
					for (int i = 0; i < column.length; i++) {
						column[i] = rs.getString(i+1);
					}
					row[j++] = column;
		}
		rs.close();
		con.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	
	//enroll to courses
	public synchronized String[][] getEnrollWindow(String eWusername, String eWpassword) {
		String[][] row = new String[40][3];
		
		String sql = "SELECT Course_Name, Course_ECTS ,Course_Exam_Type FROM student_administration.Course WHERE (Course_ID NOT IN (SELECT Distinct Course_FK_ID FROM student_administration.Prerequisite_Exam) OR Course_ID IN (Select e.Course_FK_ID from student as s left join exam_current as e on s.Student_ID=e.Student_FK_ID where e.Grade_Value > 4 and s.Last_Name='"+eWusername+"' and s.Password='"+eWpassword+"') OR Course_ID IN (SELECT Distinct Course_fk_ID FROM student_administration.Prerequisite_Exam  where precondition_value in (Select e.Course_FK_ID from student as s left join exam_current as e on s.Student_ID=e.Student_FK_ID where e.Grade_Value < 5 and s.Last_Name='"+eWusername+"' and s.Password='"+eWpassword+"'))) AND (Course_ID NOT IN (SELECT Course_FK_ID FROM student_administration.Exam_Current where Student_FK_ID=1 and Grade_Value < 5))"; 
					
		Connection connection = null;
		ResultSet resultSet = null; 
		try{
		Connection con = getDataSource().getConnection();
		Statement stmt = con.createStatement(); 
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData metaData = rs.getMetaData();
		int n = metaData.getColumnCount();
		int j = 0;
				while(rs.next()){
					String[] column = new String[n];
					for (int i = 0; i < column.length; i++) {
						column[i] = rs.getString(i+1);
					}
					row[j++] = column;
		}
		rs.close();
		con.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	
	
	public MysqlDataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(MysqlDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
