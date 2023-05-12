package org.senai.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.senai.model.Student;
import org.senai.repository.Database;

import com.google.gson.Gson;

@WebServlet("/students")
public class ListStudentsServlets extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Student> students = Database.getStudents();

		/* Verifica se a lista está vazia e caso positivo, retorna no_content */
		if (students.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return;
		}

		/* Retorna a lista como um JSON */
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();

		String studentsAsJson = new Gson().toJson(students);
		writer.print(studentsAsJson);
	}
}
