package com.mutez.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class EditStudentServlet
 */
@WebServlet("/EditStudentServlet")
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private StudentDBUtil studentDbUtil;
	
	@Resource(name="jdbc/studentdb") 
	private DataSource dataSource;
	
	int id;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		studentDbUtil = new StudentDBUtil(dataSource);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	/**public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // execute on lancement de la page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// on recupère l'id
		id = Integer.parseInt(request.getParameter("studentId"));
		// on créer le student a partir de l'id
		Student student= studentDbUtil.fetchStudent(id);
		// setAttribute("name", a "value")
		// permet d'avoir acces dans le jsp
		request.setAttribute("Student", student); 
		// construction d'un request dispatcher sur la page JSP, qui doit exister
	    // dans la web application courante
		// redirection de la requête vers cette ressource
		request.getRequestDispatcher("edit-student.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// executer quand on appuie sur le bouton save
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// on recupere les données rentrée a partir du jsp
		String fn= request.getParameter("firstName"); 
		String ln= request.getParameter("lastName"); 
		String email = request.getParameter("email");
		// on créer le student
		Student student = new Student(id,fn,ln,email); 
		// on update le student a l'aide de la fonction dans dbutil
		studentDbUtil.updateStudent(student); 
		// on redirige vers la page de base
		response.sendRedirect("StudentControllerServlet");
	}

}
