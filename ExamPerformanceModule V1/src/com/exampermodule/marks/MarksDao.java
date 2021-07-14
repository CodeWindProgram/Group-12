package com.exampermodule.marks;

import java.util.*;
import java.sql.*;

public class MarksDao {

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
	public static int save(Marks e){
		int status=0;
		try{
			Connection con=MarksDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into exam_marks(student_id,subject_id,out_of_mark,obtain_mark,sem) values (?,?,?,?,?)");
			ps.setInt(1,e.getStduentId());
			ps.setInt(2,e.getSubjectId());
			ps.setInt(3,e.getOutOfMarks());
			ps.setInt(4,e.getObtainMarks());
			ps.setInt(5,e.getSem());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int update(Marks e){
		int status=0;
		try{
			Connection con=MarksDao.getConnection();
			PreparedStatement ps=con.prepareStatement("update exam_marks set out_of_mark=?,obtain_mark=? where student_id=? and sem=? and subject_id=?");
			ps.setInt(1,e.getOutOfMarks());
			ps.setInt(2,e.getObtainMarks());
			
			ps.setInt(4,e.getStduentId());
			ps.setInt(5,e.getSem());
			ps.setInt(6,e.getSubjectId());
			
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	
	public static Marks getMarksById(int studId, int sem, int subId){
		Marks e=new Marks();
		
		try{
			Connection con=MarksDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select out_of_mark,obtain_mark from exam_marks where student_id=? and sem=? and subject_id=?");
			ps.setInt(1,studId);
			ps.setInt(2,sem);
			ps.setInt(3,subId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setOutOfMarks(rs.getInt(1));
				e.setObtainMarks(rs.getInt(2));

			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e;
	}
	public static List<Marks> getAllTeacher(){
		List<Marks> list=new ArrayList<Marks>();
		
		try{
			Connection con=MarksDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from exam_marks");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Marks e=new Marks();
				e.setStduentId(rs.getInt(1));
				e.setOutOfMarks(rs.getInt(2));
				e.setObtainMarks(rs.getInt(3));
				e.setSem(rs.getInt(4));
				e.setSubjectId(rs.getInt(5));
				
				list.add(e);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
}
