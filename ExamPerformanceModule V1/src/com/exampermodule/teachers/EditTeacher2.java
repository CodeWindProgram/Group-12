package com.exampermodule.teachers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/EditTeacher2")
public class EditTeacher2 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		String name=request.getParameter("name");
		String subId=request.getParameter("subId");
		String email=request.getParameter("email");
		String mobNo=request.getParameter("mobNo");
		
		
		Teacher e=new Teacher();
		e.setId(id);
		e.setName(name);
		e.setSubjectId(subId);
		e.setEmailId(email);
		e.setMobNo(mobNo);
		
		int status=TeacherDao.update(e);
		if(status>0){
			response.sendRedirect("ViewTeacher");
		}else{
			out.println("Sorry! unable to update record");
		}
		
		out.close();
	}

}
