package ek298h;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Accumulators.*;
import org.bson.Document;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://asd:asd@cluster0.zewoi03.mongodb.net/")) {

            MongoDatabase db = mongoClient.getDatabase("vendeglatas");
            MongoCollection<Document> etterem = db.getCollection("ettermek");
            MongoCollection<Document> foszakacsokColl = db.getCollection("foszakacsok");

            // Étterem beszúrás
            /*Document e1 = new Document("_id", "e6")
                    .append("nev", "Gundel")
                    .append("cim", new Document("varos", "Budapest")
                            .append("utca", "Gundel Károly")
                            .append("hazszam", "14"))
                    .append("tipus", "Magyaros");

            Document e2 = new Document("_id", "e7")
                    .append("nev", "Trattoria")
                    .append("cim", new Document("varos", "Szeged")
                            .append("utca", "Oskola utca")
                            .append("hazszam", "10"))
                    .append("tipus", "Olasz");

            Document e3 = new Document("_id", "e8")
                    .append("nev", "Sakura")
                    .append("cim", new Document("varos", "Debrecen")
                            .append("utca", "Piac utca")
                            .append("hazszam", "22"))
                    .append("tipus", "Japán");

            etterem.insertMany(Arrays.asList(e1, e2, e3));
            System.out.println("Sikeres beszúrás!");*/

            //Ettermek.json Szakacsok.json beolvasása
            MongoCollection<Document> etteremColl = db.getCollection("ettermek");
            String ettermekRaw = Files.readString(Paths.get("ettermek.json"));
            List<Document> ettermekList = Document.parse(ettermekRaw).getList("TempList", Document.class);


            /*List<Document> formattedEtteremList = ettermekList.stream().map(doc -> {
                String ekod = doc.getString("ekod");
                doc.put("_id", ekod);
                doc.remove("ekod");
                return doc;
            }).collect(Collectors.toList());
            etteremColl.drop();
            etteremColl.insertMany(formattedEtteremList);
            System.out.println("Sikeres feltöltés!"); */

            MongoCollection<Document> szakacsColl = db.getCollection("szakacsok");
            String szakacsokRaw = Files.readString(Paths.get("szakacsok.json"));
            List<Document> szakacsokList = Document.parse(szakacsokRaw).getList("TempList", Document.class);

            /*List<Document> formattedSzakacsok = szakacsokList.stream().map(doc -> {
                String szKod = doc.getString("ekod");
                doc.put("_id", szKod);
                doc.remove("sz_kod");
                if (doc.containsKey("eletkor")) {
                    String korStr = doc.getString("eletkor");
                    doc.put("eletkor", Integer.parseInt(korStr));
                }
                return doc;
            }).collect(Collectors.toList());
            szakacsColl.drop();
            szakacsColl.insertMany(formattedSzakacsok);
            System.out.println("Sikeres feltöltés!"); */

            /* System.out.println("--- a) Összes étterem lekérdezése ---");
            List<Document> ettermek = etteremColl.find().into(new ArrayList<>());
            for (Document doc : ettermek) {
                System.out.println(doc.toJson());
            } */

            /*System.out.println("\n--- b) Étterem lekérdezése (ekod: e3) ---");
            Document e3 = etteremColl.find(eq("_id", "e3")).first();
            if (e3 != null){
                System.out.println(e3.toJson());
            }*/

            /*System.out.println("\n--- c) Főszakácsok, akik idősebbek 35 évnél ---");
            List<Document> foszakacsok = foszakacsokColl.find(gt("eletkor", 35)).into(new
                    ArrayList<>());
            for (Document doc : foszakacsok) {
                System.out.println(doc.toJson());
            }*/

            /*System.out.println("\n--- d) FőSzakács (45 éves) és étterme (JOIN) ---");
            List<Document> eredmeny = foszakacsokColl.aggregate(Arrays.asList(
                    match(eq("eletkor", 45)),
                    lookup("etterem", "e_sz", "ekod", "etterem_info")
            )).into(new ArrayList<>());

            for (Document doc : eredmeny) {
                System.out.println(doc.toJson());
            }*/

            /*System.out.println("\n--- e) Főszakácsok átlagos életkora ---");
            Document atlag = foszakacsokColl.aggregate(Arrays.asList(
                    group(null, avg("atlagEletkor", "$eletkor"))
            )).first();
            System.out.println("Átlagéletkor: " + atlag.get("atlagEletkor"));*/

            //Etterem nevenek módosítása
            /*etteremColl.updateOne(
                    eq("_id", "e2"),
                    new Document("$set", new Document("nev", "Laci konyha"))
            );
            System.out.println("Sikeres módosítás!");*/

            // E5 azonosítójú étterem törlése
            /* etteremColl.deleteOne(eq("_id", "e5"));
            System.out.println("Sikeres törlés!");*/

            //35 alatti foszakacsok törlése
            foszakacsokColl.deleteMany(lt("eletkor", 35));
            System.out.println("Sikeres törlés!");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}