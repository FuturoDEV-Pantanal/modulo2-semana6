package org.senai.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.senai.model.Student;
import org.senai.repository.Database;

@WebServlet("/new-student")
public class NewStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * Despachando a requisição para o JSP, que receberá também os valores de
		 * request e response
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/new-student.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/** Recuperando os dados enviados pelo formulário **/
		Integer registration = Integer.valueOf(request.getParameter("registration"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		/** Montando o objeto e salvando-o **/
		Student student = new Student(registration, name, email);
		Database.add(student);

		/** Redirecionando a requisição para a listagem de estudantes **/
		response.sendRedirect("student?registered=true");
	}
}
