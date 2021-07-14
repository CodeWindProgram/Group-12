package com.exampermodule.marks;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ViewMarks")
public class ViewMarks extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<a href='AddMarks.html'>Add Marks</a>");
		out.println("<h1>List of Marks</h1>");
		
		List<Marks> list=MarksDao.getAllTeacher();
		
		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Student ID</th><th>SubjectId</th><th>SEM</th><th>Out Of Marks</th><th>Obtain Marks</th><th>Edit</th></tr>");
		for(Marks e:list){
																																						//int studId, int sem, int subId
			out.print("<tr><td>"+e.getStduentId()+"</td><td>"+e.getSubjectId()+"</td><td>"+e.getSem()+"</td><td>"+e.getOutOfMarks()+"</td><td>"+e.getObtainMarks()+"</td><td><a href='EditTeacher?studId="+e.getStduentId()+"'>edit</a></td></tr>");
		}
		out.print("</table>");
		
		out.close();
	}
}
