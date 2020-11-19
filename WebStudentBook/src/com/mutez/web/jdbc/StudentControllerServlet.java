package com.mutez.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private StudentDBUtil studentDbUtil;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		studentDbUtil = new StudentDBUtil(dataSource);
	}

	@Resource(name="jdbc/studentdb") 
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		try {
			listStudents(request,response);
			} catch (Exception e) {
			// TODO Auto-generated catch block 
				e.printStackTrace();
			}
	}
	
	private void listStudents(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
			//on créer une liste de student a partir de la methode dans studentdbutil
			List<Student> students = studentDbUtil.getStudents();
			//setAttribute("name", a "value")
			//permet d'avoir acces dans le jsp
			request.setAttribute("STUDENT_LIST", students);
			// construction d'un request dispatcher sur la page JSP, qui doit exister
		    // dans la web application courante
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp"); 
			// redirection de la requête vers cette ressource
			dispatcher.forward(request, response);
			}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//permet de rediriger vers AddStudentServlet quand on appuie sur le bouton addstudent 
		response.sendRedirect("AddStudentServlet");
	}

}
