package org.julien.beerapp.beerservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.julien.beerapp.beerservlet.dao.BeerMongoDAO;
import org.julien.beerapp.model.Beer;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class BeerDetail
 */
@WebServlet("/BeerDetail")
public class BeerDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeerDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(null==request.getParameter("id")|| ""==request.getParameter("id")){
		   response.setStatus(402);
		   response.getWriter().append(" Missing id parameter");
		   return;
		}
		String id = request.getParameter("id");
		Beer beer = BeerMongoDAO.getBeerMongoDAOInstance().getBeer(id);
			
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonList = mapper.writeValueAsString(beer);
		
		response.getWriter().append(jsonList);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
