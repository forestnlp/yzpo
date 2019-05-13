package com.yzpo.crawler.baidutieba.page;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.yzpo.mongodb.DBProperties;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class BaiduTieBaPageMongoDao {

    public static void save(int po_id,List<BaiduTieBaPage> list) {

        MongoClient client = new MongoClient(DBProperties.host,DBProperties.port);
        MongoDatabase db = client.getDatabase(DBProperties.dbname);

        MongoCollection<Document> collection = db.getCollection("contents");

        List<Document> collections = new ArrayList<Document>();

        Document d1 = new Document();

        FindIterable<Document> documents = collection.find(Filters.eq("po_id", po_id));
        MongoCursor<Document> iterator = documents.iterator();
        if (iterator.hasNext()) {
            d1 = iterator.next();
        }
        iterator.close();

        d1.put("po_id", po_id);

        for(BaiduTieBaPage p:list)
            d1.append("reply",p.toString());

        collections.add(d1);

        collection.insertMany(collections);

    }
}
