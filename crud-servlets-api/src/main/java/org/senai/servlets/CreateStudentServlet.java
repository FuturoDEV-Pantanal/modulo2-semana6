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

@WebServlet("/new-student")
public class CreateStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* Pega o leitor (reader) presente na requisição */
		BufferedReader reader = request.getReader();

		/* Lê todas as linhas escritas e as concatena */
		String objectAsJson = reader.lines().collect(Collectors.joining());

		/* Instancia o Gson e converte o JSON em um objeto */
		Gson gson = new Gson();
		Student student = gson.fromJson(objectAsJson, Student.class);

		Database.add(student);
	}
}
