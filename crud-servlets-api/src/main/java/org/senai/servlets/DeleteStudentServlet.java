package org.senai.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.senai.repository.Database;

@WebServlet("/delete-student")
public class DeleteStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* Recebe o ID por parâmetro e o remove */
		Integer registration = Integer.valueOf(request.getParameter("registration"));
		Database.remove(registration);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}
