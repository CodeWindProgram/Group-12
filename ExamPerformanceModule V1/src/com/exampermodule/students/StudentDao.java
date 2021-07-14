package com.exampermodule.students;

import java.util.*;
import java.sql.*;

public class StudentDao {

	public static Connection getConnection(){
		Connection con=null;
		try{
			 Class.forName("com.mysql.jdbc.Driver");
			 System.out.println("Driver loaded");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam_per_module","root","");
			 //jdbc:mysql://localhost:3306/ncip?serverTimezone=IST
			 System.out.println("Database is successfully connected");			
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(Student e){
		int status=0;
		try{
			Connection con=StudentDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into student(name,sem,division,email_id,mob_no) values (?,?,?,?,?)");
			ps.setString(1,e.getName());
			ps.setString(2,e.getSem());
			ps.setString(3,e.getDivision());
			ps.setString(4,e.getEmailId());
			ps.setString(5,e.getMobNo());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int update(Student e){
		int status=0;
		try{
			Connection con=StudentDao.getConnection();
			PreparedStatement ps=con.prepareStatement("update student set name=?,sem=?,division=?,email_id=?,mob_no=? where student_id=?");
			ps.setString(1,e.getName());
			ps.setString(2,e.getSem());
			ps.setString(3,e.getDivision());
			ps.setString(4,e.getEmailId());
			ps.setString(5,e.getMobNo());
			ps.setInt(6, e.getStudentId());
			System.out.println(ps.toString());
			status=ps.executeUpdate();
			System.out.println("status :: "+status);
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int delete(int id){
		int status=0;
		try{
			Connection con=StudentDao.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from student where student_id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	public static Student getStudentById(int id){
		Student e=new Student();
		
		try{
			Connection con=StudentDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from student where student_id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setStudentId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSem(rs.getString(3));
				e.setDivision(rs.getString(4));
				e.setEmailId(rs.getString(5));
				e.setMobNo(rs.getString(6));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e;
	}
	public static List<Student> getAllStudent(){
		List<Student> list=new ArrayList<Student>();
		
		try{
			Connection con=StudentDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from student");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Student e=new Student();
				e.setStudentId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSem(rs.getString(3));
				e.setDivision(rs.getString(4));
				e.setEmailId(rs.getString(5));
				e.setMobNo(rs.getString(6));
				list.add(e);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
}
