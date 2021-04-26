package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.service.regista.RegistaService;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteUpdateRegistaServlet")
public class ExecuteUpdateRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idRegistaParameter = request.getParameter("idToUpdate");
		String nomeRegistaParamter = request.getParameter("nome");
		String cognomeRegistaParamter = request.getParameter("cognome");
		String dataDiNascitaRegistaParamter = request.getParameter("dataDiNascita");
		String sessoRegistaParameter = request.getParameter("sesso");
		String nickNameRegistaParamter = request.getParameter("nickName");

		Regista registaInstance = UtilityForm.createRegistaFromParams(nomeRegistaParamter, cognomeRegistaParamter,
				nickNameRegistaParamter, dataDiNascitaRegistaParamter, sessoRegistaParameter);

		if (!UtilityForm.validateRegistaBean(registaInstance)) {
			request.setAttribute("update_regista_attr", registaInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/regista/update.jsp").forward(request, response);
			return;
		}

		try {
			RegistaService registaServiceInstance = MyServiceFactory.getRegistaServiceInstance();

			Regista registaUpdateInstance = registaServiceInstance
					.caricaSingoloElemento(Long.parseLong(idRegistaParameter));

			registaUpdateInstance.setNome(registaInstance.getNome());
			registaUpdateInstance.setCognome(registaInstance.getCognome());
			registaUpdateInstance.setNickName(registaInstance.getNickName());
			registaUpdateInstance.setDataDiNascita(registaInstance.getDataDiNascita());
			registaUpdateInstance.setSesso(registaInstance.getSesso());

			registaServiceInstance.aggiorna(registaUpdateInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/list.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListRegistaServlet?operationResult=SUCCESS");

	}

}
