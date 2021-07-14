package com.exampermodule.students;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/EditStudent")
public class EditStudent extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<h1>Update Student</h1>");
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		
		Student e=StudentDao.getStudentById(id);
		
		out.print("<form action='EditStudent2' method='post'>");
		out.print("<table>");
		out.print("<tr><td></td><td><input type='hidden' name='id' value='"+e.getStudentId()+"'/></td></tr>");
		out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+e.getName()+"'/></td></tr>");
		out.print("<tr><td>SEM:</td><td><input type='text' name='sem' value='"+e.getSem()+"'/></td></tr>");
		out.print("<tr><td>Division:</td><td><input type='text' name='division' value='"+e.getDivision()+"'/></td></tr>");
		out.print("<tr><td>Email:</td><td><input type='text' name='email_id' value='"+e.getEmailId()+"'/></td></tr>");
		out.print("<tr><td>Mobile No:</td><td><input type='text' name='mob_no' value='"+e.getMobNo()+"'/></td></tr>");
		
		out.print("<tr><td colspan='2'><input type='submit' value='Edit &amp; Save '/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		
		out.close();
	}
}
