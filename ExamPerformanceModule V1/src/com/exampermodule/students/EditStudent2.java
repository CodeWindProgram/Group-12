package com.exampermodule.students;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/EditStudent2")
public class EditStudent2 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		String name=request.getParameter("name");
		String sem=request.getParameter("sem");
		String div=request.getParameter("division");
		String emailId=request.getParameter("email_id");
		String mobNo=request.getParameter("mob_no");
		
		
		Student e=new Student();
		e.setName(name);
		e.setSem(sem);
		e.setDivision(div);
		e.setEmailId(emailId);
		e.setMobNo(mobNo);
		e.setStudentId(id);
		
		int status=StudentDao.update(e);
		if(status>0){
			response.sendRedirect("ViewStudent");
		}else{
			out.println("Sorry! unable to update record");
		}
		
		out.close();
	}

}
