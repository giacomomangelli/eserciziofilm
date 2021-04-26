package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Regista;

@WebServlet("/PrepareInsertRegistaServlet")
public class PrepareInsertRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// metto un bean 'vuoto' in request perché per la pagina risulta necessario
		request.setAttribute("insert_regista_attr", new Regista());
		request.getRequestDispatcher("/regista/insert.jsp").forward(request, response);
	}

}
