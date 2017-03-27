package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.util.JSON;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin
    @RequestMapping("/Checklist_Template_Requirements")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	
    	long startTime = System.currentTimeMillis();   
    	        MongoClient mongoClient = new MongoClient("localhost", 27017);

    	        MongoDatabase database = mongoClient.getDatabase("ADC");

    	        // print existing databases    	        
    	        
    	        MongoIterable<String> strings=mongoClient.listDatabaseNames();
    	        MongoCursor<String> iterator=strings.iterator();
    	        while (iterator.hasNext()) {
    	          System.out.println("Database: "+iterator.next());
    	        }

    	        MongoCollection collection = database.getCollection("ChecklistTree");

    	        // print all collections in customers database
    	       
    	       MongoIterable<String> stringsdb=database.listCollectionNames();
   	         iterator=stringsdb.iterator();
   	        while (iterator.hasNext()) {
   	          System.out.println("Collection: "+iterator.next());
   	        }
   	        
   	     DBCollection collection_old = (mongoClient.getDB("ADC")).getCollection("ChecklistTree");
   	     
   	  BasicDBObject searchQuery = new BasicDBObject();
      searchQuery.put("checklistId", "1.0");
      DBCursor cursor = collection_old.find();
      String a = "";
      while (cursor.hasNext()) {
          a=a+(cursor.next());
      }
   	     
   	     /*FindIterable list1=collection.find();
	         iterator=list1.iterator();
	        while (iterator.hasNext()) {
	          System.out.println(iterator.next().toString());
	        }*/
    	       
    	        /*// create data
    	        DBCollection collection = database.getCollection("customers");
    	        BasicDBObject document = new BasicDBObject();
    	        document.put("name", "Shubham");
    	        document.put("company", "Baeldung");
    	        collection.insert(document);

    	        // update data
    	        BasicDBObject query = new BasicDBObject();
    	        query.put("name", "Shubham");
    	        BasicDBObject newDocument = new BasicDBObject();
    	        newDocument.put("name", "John");
    	        BasicDBObject updateObject = new BasicDBObject();
    	        updateObject.put("$set", newDocument);
    	        collection.update(query, updateObject);

    	        // read data
    	        BasicDBObject searchQuery = new BasicDBObject();
    	        searchQuery.put("name", "John");
    	        DBCursor cursor = collection.find(searchQuery);
    	        while (cursor.hasNext()) {
    	            System.out.println(cursor.next());
    	        }

    	        // delete data
    	        BasicDBObject deleteQuery = new BasicDBObject();
    	        deleteQuery.put("name", "John");
    	        collection.remove(deleteQuery);
    	        */
      long endTime = System.currentTimeMillis();
      System.out.println("That took " + (endTime - startTime) + " milliseconds");
    	return a;
    	
    }
    public static Object getNextSequence(String name) throws Exception{
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // Now connect to your databases
        DB db = mongoClient.getDB("ADC");
        DBCollection collection = db.getCollection("counters");
        BasicDBObject find = new BasicDBObject();
        find.put("_id", name);
        BasicDBObject update = new BasicDBObject();
        update.put("$inc", new BasicDBObject("seq", 1));
        DBObject obj =  collection.findAndModify(find, update);
        return obj.get("seq");
    }
    
    @CrossOrigin
    @RequestMapping("api/save/Checklist_Template_Requirements")
    public String saveChecklistTemplateRequirements(@RequestParam(value="name", defaultValue="World") String name) throws Exception {
    	
    	long startTime = System.currentTimeMillis();   
    	        MongoClient mongoClient = new MongoClient("localhost", 27017);

    	        MongoDatabase database = mongoClient.getDatabase("ADC");
    	        
    	        DBCollection collection_old = (mongoClient.getDB("ADC")).getCollection("users");
    	        
    	        
    	        BasicDBObject document = new BasicDBObject();
    	        String json = "{'database' : 'mkyongDB','table' : 'hosting'," +
    	        		  "'detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";
    	        document = (BasicDBObject)JSON.parse(json);
    	        document.put("_id", getNextSequence("userid"));

    	        		//DBObject dbObject = (DBObject)JSON.parse(json);
    	        		//dbObject.put("_id", getNextSequence("userid"));
    	        		
    	        collection_old.insert(document);
    	        
    	   	     
    	        /*
    	         * db.users.insert(
   {
     _id: getNextSequence("userid"),
     name: "Bob D."
   }
)
    	         */
    	        long endTime = System.currentTimeMillis();
    	        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    	      	return "a";
    }

    
}
