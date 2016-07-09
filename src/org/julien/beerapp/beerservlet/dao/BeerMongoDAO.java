package org.julien.beerapp.beerservlet.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.julien.beerapp.model.Beer;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class BeerMongoDAO {

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private static BeerMongoDAO beerMongoDAO;
    
    private BeerMongoDAO(){
    	this.client = new MongoClient("localhost");
    	this.database = this.client.getDatabase("jonsnow");
    	this.collection = this.database.getCollection("beers");
    }
     
    public static BeerMongoDAO getBeerMongoDAOInstance(){
    	if (null == beerMongoDAO){
    		beerMongoDAO = new BeerMongoDAO();
    	}
    	
    	return beerMongoDAO;
    }
    
    public void removeBeer(String id){
    	System.out.print("delete : "+id);
    	collection.findOneAndDelete(Filters.eq("id", id));
    }
    
    public boolean beerAlreadyExist(String id){
    	
		boolean exist = false;
    	Document doc = collection.find(Filters.eq("id", id)).first();
    	if (null!=doc){
    		exist=true;
    	}
		return exist;
	}
    
    public Beer getBeer(String id){
    	
    	Beer beer = new Beer();
    	
    	Document doc = collection.find(Filters.eq("id", id)).first();
    	
		beer.setName(doc.getString("name"));
		beer.setDescription(doc.getString("description"));
		beer.setImg(doc.getString("img"));	
		Object alcohol = doc.get("alcohol");
		
		if (alcohol instanceof Double){
			beer.setAlcohol((Double) alcohol);
		} else {
			beer.setAlcohol(((Integer)alcohol).doubleValue());
		}
		
		beer.setAvailability(doc.getString("availability"));
		beer.setBrewery(doc.getString("brewery"));
		beer.setLabel(doc.getString("label"));
		beer.setServing(doc.getString("serving"));
		beer.setStyle(doc.getString("style"));

		return beer;
	}
    	
    
    public List<Beer> getBeerList(){
    	
    	List<Beer> beers = new ArrayList<>();
    	
    	MongoCursor<Document> cursor = this.collection.find().iterator();
    	
    	while (cursor.hasNext()){
    		Document doc = cursor.next();
    		Beer beer = new Beer();
    		beer.setName(doc.getString("name"));
    		beer.setDescription(doc.getString("description"));
    		beer.setImg(doc.getString("img"));	
    		Object alcohol = doc.get("alcohol");
    		
    		if (alcohol instanceof Double){
    			beer.setAlcohol((Double) alcohol);
    		} else {
    			beer.setAlcohol(((Integer)alcohol).doubleValue());
    		}
    		System.out.println(beer.getId());
    		beers.add(beer);	
    	}	
    	return beers;
    }
    
    public void insertBeer(Document doc){
    	collection.insertOne(doc);
    }
    
    public void modifyBeer(String id, Document doc){
    	collection.findOneAndReplace(Filters.eq("id", id), doc);
    }
    
    public Document generateBeerDocument (Beer beer){
    	
    	Document doc = new Document("name", beer.getName())
    			.append("id", beer.getId())
    			.append("alcohol", beer.getAlcohol())
    			.append("description", beer.getDescription())
    			.append("img", beer.getImg())
    			.append("label", beer.getLabel())
    			.append("availability", beer.getAvailability())
    			.append("serving", beer.getServing())
    			.append("style", beer.getStyle())
    			.append("brewery", beer.getBrewery());
  	
    	return doc;
    }
    
}