package org.julien.beerapp.beerservlet;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.julien.beerapp.beerservlet.dao.BeerMongoDAO;
import org.julien.beerapp.model.Beer;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class BeerCreate
 */
@WebServlet("/BeerCreate")
public class BeerCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeerCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedReader beerReader = request.getReader();
		StringBuilder beerBuffer = new StringBuilder();
		String line;
		while((line = beerReader.readLine())!=null){
			beerBuffer.append(line);
		}
		String beerJSON = beerBuffer.toString();
		
		
		System.out.println("i got a beer : "+beerJSON);
		ObjectMapper mapper = new ObjectMapper();
		Beer beer = mapper.readValue(beerJSON, Beer.class);

		if(BeerMongoDAO.getBeerMongoDAOInstance().beerAlreadyExist(beer.getId())){
		   response.setStatus(418);
		   response.getWriter().append("La biere existe deja");
		   return;
		}
		
		Document doc = new Document();
		
		doc = BeerMongoDAO.getBeerMongoDAOInstance().generateBeerDocument(beer);
		BeerMongoDAO.getBeerMongoDAOInstance().insertBeer(doc);
		
	}

}
