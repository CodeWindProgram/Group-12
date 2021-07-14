package com.exampermodule.students;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ViewStudent")
public class ViewStudent extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<a href='AddStudent.html'>Add New Student</a>");
		out.println("<h1>Student List</h1>");
		
		List<Student> list=StudentDao.getAllStudent();
		
		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Student ID </th><th>Name</th><th>Email Id</th><th>SEM</th><th>Division</th><th>Mobile No.</th><th>Edit</th><th>Delete</th></tr>");
		for(Student stud:list){
			out.print("<tr><td>"+stud.getStudentId()+"</td><td>"+stud.getName()+"</td><td>"+stud.getEmailId()+"</td><td>"+stud.getSem()+"</td><td>"+stud.getDivision()+"</td><td>"+stud.getMobNo()+"</td><td><a href='EditStudent?id="+stud.getStudentId()+"'>edit</a></td><td><a href='DeleteStudent?id="+stud.getStudentId()+"'>delete</a></td></tr>");
		}
		out.print("</table>");
		
		out.close();
	}
}
