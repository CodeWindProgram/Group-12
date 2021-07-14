package com.exampermodule.marks;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SaveMarks")
public class SaveMarks extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int outOfMarks=Integer.parseInt(request.getParameter("outOfMarks"));
		int obtainMarks=Integer.parseInt(request.getParameter("obtainMarks"));
		int sem=Integer.parseInt(request.getParameter("sem"));
		int studId=Integer.parseInt(request.getParameter("studId"));
		int subId=Integer.parseInt(request.getParameter("subId"));
		
		
		Marks e=new Marks();
		e.setObtainMarks(obtainMarks);
		e.setOutOfMarks(outOfMarks);
		e.setSem(sem);
		e.setStduentId(studId);
		e.setSubjectId(subId);
		
		int status=MarksDao.save(e);
		if(status>0){
			out.print("<p>Record saved successfully!</p>");
			request.getRequestDispatcher("AddMarks.html").include(request, response);
		}else{
			out.println("Sorry! unable to save record");
		}
		
		out.close();
	}

}
