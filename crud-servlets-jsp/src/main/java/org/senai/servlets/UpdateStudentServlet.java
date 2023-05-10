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

@WebServlet("/update-student")
public class UpdateStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* Recebe par�metro da requisi��o que passamos na URL */
		Integer registration = Integer.valueOf(request.getParameter("registration"));
		/* Recupera o estudante na base de dados fict�cia */
		Student student = Database.getStudent(registration);

		/*
		 * Adiciona a vari�vel do estudante � requisi��o, para que o JSP consiga acessar
		 * os dados do estudante
		 */
		request.setAttribute("student", student);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/** Recuperando os dados enviados pelo formul�rio **/
		Integer registration = Integer.valueOf(request.getParameter("registration"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		/** Buscando o estudante, atualizando o objeto e salvando-o **/
		Student student = Database.getStudent(registration);
		student.setName(name);
		student.setEmail(email);

		/** Redirecionando a requisi��o para a listagem de estudantes **/
		response.sendRedirect("student");
	}
}
