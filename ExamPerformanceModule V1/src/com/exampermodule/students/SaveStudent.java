package com.exampermodule.students;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SaveStudent")
public class SaveStudent extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
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
		
		int status=StudentDao.save(e);
		if(status>0){
			out.print("<p>Record saved successfully!</p>");
			request.getRequestDispatcher("AddStudent.html").include(request, response);
		}else{
			out.println("Sorry! unable to save record");
		}
		
		out.close();
	}

}
