package com.exampermodule.teachers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ViewTeacher")
public class ViewTeacher extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<a href='AddTeacher.html'>Add New Teacher</a>");
		out.println("<h1>Teacher List</h1>");
		
		List<Teacher> list=TeacherDao.getAllTeacher();
		
		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Id</th><th>Name</th><th>SubjectId</th><th>EmailId</th><th>Mobile No.</th><th>Edit</th><th>Delete</th></tr>");
		for(Teacher e:list){
			out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td><td>"+e.getSubjectId()+"</td><td>"+e.getEmailId()+"</td><td>"+e.getMobNo()+"</td><td><a href='EditTeacher?id="+e.getId()+"'>edit</a></td><td><a href='DeleteTeacher?id="+e.getId()+"'>delete</a></td></tr>");
		}
		out.print("</table>");
		
		out.close();
	}
}
