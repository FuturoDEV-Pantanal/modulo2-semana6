package org.senai.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.senai.model.Student;
import org.senai.repository.Database;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * Verifica se a matrícula não está nula, para identificar se se trata de uma
		 * deleção
		 **/
		String registration = request.getParameter("registration");
		if (registration != null) {
			this.doDelete(request, response);
			return;
		}

		/**
		 * Verifica se a requisição foi realizada pós-criação de estudante, para exibir
		 * mensagem no JSP
		 **/
		boolean isRegisterRequest = Boolean.valueOf(request.getParameter("registered"));
		request.setAttribute("isRegisterRequest", isRegisterRequest);
		/**
		 * Listando os estudantes da base de dados e adicionando-os como atributo da
		 * requisição
		 **/
		List<Student> students = Database.listAll();
		request.setAttribute("students", students);
		/**
		 * Transferindo o controle da requisição para o JSP de listagem, e adicionando
		 * os estudantes na requisição, para que o JSP acesse-os
		 **/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/students-list.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/** Recupera a matrícula do estudante e o remove da lista de cadastrados **/
		Integer registration = Integer.valueOf(request.getParameter("registration"));
		Database.remove(registration);
		response.sendRedirect("student");
	}
}
