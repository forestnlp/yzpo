package com.yzpo.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String [] args) {
        MongoClient client = new MongoClient(DBProperties.host,DBProperties.port);
        MongoDatabase db = client.getDatabase(DBProperties.dbname);
        MongoCollection<Document> collection = db.getCollection("contents");

        List<Document> collections = new ArrayList<Document>();

        Document d1 = new Document();
        d1.append("titles", "yztitle1").append("author", "author1");
        collections.add(d1);

        Document d2 = new Document();
        d2.append("titles", "yztitle2").append("author", "author2");
        collections.add(d2);

        collection.insertMany(collections);

        FindIterable<Document> documents = collection.find(Filters.eq("author", "author2"));
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().get("titles"));
        }
        iterator.close();
    }
}
