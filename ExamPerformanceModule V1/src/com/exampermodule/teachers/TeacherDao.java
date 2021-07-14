package com.exampermodule.teachers;

import java.util.*;
import java.sql.*;

public class TeacherDao {

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
	public static int save(Teacher e){
		int status=0;
		try{
			Connection con=TeacherDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into teacher(name,subject_id,email_id,mob_no) values (?,?,?,?)");
			ps.setString(1,e.getName());
			ps.setString(2,e.getSubjectId());
			ps.setString(3,e.getEmailId());
			ps.setString(4,e.getMobNo());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int update(Teacher e){
		int status=0;
		try{
			Connection con=TeacherDao.getConnection();
			PreparedStatement ps=con.prepareStatement("update teacher set name=?,subject_id=?,email_id=?,mob_no=? where teacher_id=?");
			ps.setString(1,e.getName());
			ps.setString(2,e.getSubjectId());
			ps.setString(3,e.getEmailId());
			ps.setString(4,e.getMobNo());
			ps.setInt(5,e.getId());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int delete(int id){
		int status=0;
		try{
			Connection con=TeacherDao.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from teacher where teacher_id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	public static Teacher getTeacherById(int id){
		Teacher e=new Teacher();
		
		try{
			Connection con=TeacherDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from teacher where teacher_id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSubjectId(rs.getString(3));
				e.setEmailId(rs.getString(4));
				e.setMobNo(rs.getString(5));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e;
	}
	public static List<Teacher> getAllTeacher(){
		List<Teacher> list=new ArrayList<Teacher>();
		
		try{
			Connection con=TeacherDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from teacher");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Teacher e=new Teacher();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSubjectId(rs.getString(3));
				e.setEmailId(rs.getString(4));
				e.setMobNo(rs.getString(5));
				list.add(e);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
}
