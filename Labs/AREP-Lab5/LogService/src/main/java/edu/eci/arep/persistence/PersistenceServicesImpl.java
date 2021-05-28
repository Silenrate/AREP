package edu.eci.arep.persistence;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.eci.arep.model.Message;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class that implements the Persistence Methods For The App DB.
 */
public class PersistenceServicesImpl implements PersistenceServices {

    private final MongoClient mongoClient;
    private static final BasicDBObject filterByDate = new BasicDBObject("date", -1);

    /**
     * Constructor For PersistenceServicesImpl Class.
     */
    public PersistenceServicesImpl() {
        MongoClientURI uri = new MongoClientURI("mongodb://walteros:password@192.168.1.20:27017/arep?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=arep&authMechanism=SCRAM-SHA-1&3t.uriVersion=3");
        mongoClient = new MongoClient(uri);
    }

    /**
     * Adds a new message to the DB.
     *
     * @param message The New Message Information.
     */
    @Override
    public void addMessage(Message message) {
        MongoDatabase database = mongoClient.getDatabase("arep");
        MongoCollection<Document> collection = database.getCollection("Messages");
        Document document = new Document();
        document.put("data", message.getData());
        document.put("date", message.getDate());
        collection.insertOne(document);
        System.out.println("Message: " + message.getData() + " created on MongoDB at: " + message.getDate());
    }

    /**
     * Returns the last ten messages from the DB.
     *
     * @return A List that have the last ten messages from the DB.
     */
    @Override
    public List<Message> getLastMessages() {
        List<Message> messages = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("arep");
        MongoCollection<Document> collection = database.getCollection("Messages");
        FindIterable fit = collection.find().sort(filterByDate).limit(10);
        ArrayList<Document> docs = new ArrayList<>();
        fit.into(docs);
        String message;
        Date date = null;
        for (Document document : docs) {
            message = (String) document.get("data");
            date = (Date) document.get("date");
            messages.add(new Message(message, date));
        }
        System.out.println("Last Ten Messages Selected From MongoDB at: " + LocalDateTime.now());
        return messages;
    }
}
