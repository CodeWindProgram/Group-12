package com.exampermodule.teachers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SaveTeacher")
public class SaveTeacher extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		String subId=request.getParameter("subId");
		String email=request.getParameter("email");
		String mobNo=request.getParameter("mobNo");
		
		Teacher e=new Teacher();
		e.setName(name);
		e.setSubjectId(subId);
		e.setEmailId(email);
		e.setMobNo(mobNo);
		
		int status=TeacherDao.save(e);
		if(status>0){
			out.print("<p>Record saved successfully!</p>");
			request.getRequestDispatcher("AddTeacher.html").include(request, response);
		}else{
			out.println("Sorry! unable to save record");
		}
		
		out.close();
	}

}
