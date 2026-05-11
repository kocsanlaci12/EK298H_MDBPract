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

public class Main {
    public static void main(String[] args) {

        String connectionString = "mongodb+srv://asd:asd@cluster0.zewoi03.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {

            MongoDatabase database = mongoClient.getDatabase("autoszerviz");
            MongoCollection<Document> autoCollection = database.getCollection("auto");

            // Autó kiírás
            /*List<Document> autok = autoCollection.find().into(new ArrayList<>());
            for (Document auto : autok) {
                System.out.println(auto.toJson());
            }*/

            //Autók beszúrása
            /*Document a1 = new Document("_id", "a1")
                    .append("szin", "Sárga")
                    .append("motor", "2.0 Benzin")
                    .append("karbantartasok", "4")
                    .append("muszakiallapot", "Újszerű")
                    .append("_autorendszam", "ZSK254");

            Document a2 = new Document("_id", "a2")
                    .append("szin", "Fekete")
                    .append("motor", "2.2 Dízel")
                    .append("karbantartasok", "5")
                    .append("muszakiallapot", "Kiváló")
                    .append("_autorendszam", "JKL789");

            Document a3 = new Document("_id", "a3")
                    .append("szin", "Piros")
                    .append("motor", "1.4 Benzin")
                    .append("karbantartasok", "1")
                    .append("muszakiallapot", "Jó")
                    .append("_autorendszam", "POI672");

            autoCollection.insertMany(Arrays.asList(a1, a2, a3));
            System.out.println("Sikeres beszúrás!"); */

            // Autók beolvasása
            MongoCollection<Document> autoColl = database.getCollection("auto");
            String autoRaw = Files.readString(Paths.get("autok.json"));
            List<Document> autoList = Document.parse(autoRaw).getList("TempList", Document.class);

            /*List<Document> formattedAutoList = autoList.stream().map(doc -> {

                String rendszam = doc.getString("_autorendszam");
                doc.put("_id", rendszam);
                doc.remove("_autorendszam");

                return doc;

            }).collect(Collectors.toList());
            autoColl.drop();
            autoColl.insertMany(formattedAutoList);
            System.out.println("Autók sikeresen feltöltve!");*/

            // Tulajdonosok beolvasása
            MongoCollection<Document> tulajColl = database.getCollection("tulajdonos");
            String tulajRaw = Files.readString(Paths.get("tulajdonosok.json"));
            List<Document> tulajList = Document.parse(tulajRaw).getList("TempList", Document.class);

            /*List<Document> formattedTulajList = tulajList.stream().map(doc -> {
                String id = doc.getString("_tulajid");
                doc.put("_id", id);
                doc.remove("_tulajid");
                return doc;
            }).collect(Collectors.toList());
            tulajColl.drop();
            tulajColl.insertMany(formattedTulajList);
            System.out.println("Tulajdonosok sikeresen feltöltve!"); */

            //Rendszam alapjan lekerdezes
            /* Document auto = autoColl.find(eq("_id", "ZSK254")).first();
            if (auto != null) {
                System.out.println(auto.toJson());
            } */

            // Autók, ahol 3-nál több karbantartás van
            /*List<Document> autok = autoColl.find(gt("karbantartasok", "3"))
                    .into(new ArrayList<>());
            for (Document doc : autok) {
                System.out.println(doc.toJson());
            }*/

            // Autó és a gyártási adatai
            /*List<Document> eredmeny = autoColl.aggregate(Arrays.asList(
                    match(eq("karbantartasok", "4")),
                    lookup("gyartott", "_autorendszam", "_a_gy_a", "gyartasi_info")
            )).into(new ArrayList<>());
            for (Document doc : eredmeny) {
                System.out.println(doc.toJson());
            }*/

           // Autók átlagos karbantartása

            /* Document atlag = autoColl.aggregate(Arrays.asList(
                    group(null,
                            avg("atlagKarbantartas",
                                    new Document("$toInt", "$karbantartasok")
                            )
                    )
            )).first();
            System.out.println("Átlag karbantartás: " + atlag.get("atlagKarbantartas")); */

            // Műszaki állapot módosítás
            /* autoColl.updateOne(
                    eq("_id", "ZSK254"),
                    new Document("$set", new Document("muszakiallapot", "Rossz"))
            );
            System.out.println("Sikeres módosítás!"); */

            // Autó törlése
            /*autoColl.deleteOne(eq("_id", "POI672"));
            System.out.println("Sikeres törlés!");*/

            //Karbantartás alapján tölés
            autoColl.deleteMany(lt("karbantartasok", "2"));
            System.out.println("Sikeres törlés!");


        } catch (Exception e) {
            System.err.println("Hiba történt: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
