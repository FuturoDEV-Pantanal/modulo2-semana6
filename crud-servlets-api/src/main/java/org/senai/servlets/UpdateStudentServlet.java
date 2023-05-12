package org.senai.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.senai.model.Student;
import org.senai.repository.Database;

import com.google.gson.Gson;

@WebServlet("/update-student")
public class UpdateStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* Pega o identificador */
		Integer registration = Integer.valueOf(request.getParameter("registration"));

		/* Pega o GSON, lê as linhas e converte em um estudante */
		Gson gson = new Gson();
		BufferedReader reader = request.getReader();
		String objectAsJson = reader.lines().collect(Collectors.joining());
		Student student = gson.fromJson(objectAsJson, Student.class);

		Student studentFromDatabase = Database.getStudent(registration);
		studentFromDatabase.setEmail(student.getEmail());
		studentFromDatabase.setName(student.getName());
	}
}
