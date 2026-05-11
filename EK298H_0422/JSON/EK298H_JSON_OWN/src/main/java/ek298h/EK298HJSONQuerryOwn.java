package ek298h;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EK298HJSONQuerryOwn {

    public static void main(String[] args) throws Exception {

        ObjectMapper m = new ObjectMapper();

        JsonNode root = m.readTree(new File("EK298H_JSONOwn.json"));
        JsonNode schemaNode = m.readTree(new File("EK298H_JSONOwnschema.json"));

        JsonSchema schema = JsonSchemaFactory
                .getInstance(SpecVersion.VersionFlag.V7)
                .getSchema(schemaNode);

        Set<ValidationMessage> errors = schema.validate(root);

        if (errors.isEmpty()) {
            System.out.println("Valid JSON");
        } else {
            System.out.println("Hibás JSON:");
            errors.forEach(e -> System.out.println(e.getMessage()));
        }

        // ADATOK
        JsonNode autoszerviz = root.get("autoszervizeles");
        JsonNode autok = autoszerviz.get("auto");
        JsonNode karbantartasok = autoszerviz.get("karbantartas");
        JsonNode szervizek = autoszerviz.get("szervizbevitel");
        JsonNode tulajdonosok = autoszerviz.get("tulajdonos");
        JsonNode birtoklas = autoszerviz.get("birtokol");

        System.out.println("\n=== 1. AUTÓK LISTÁJA ===");
        for (JsonNode auto : autok) {
            System.out.println(auto.get("_autorendszam").asText()
                    + " | " + auto.get("szin").asText()
                    + " | " + auto.get("motor").asText()
                    + " | KM: " + auto.get("karbantartasok").asText());
        }

        System.out.println("\n=== 2. SZERVIZEK ÉS JAVÍTÁSOK ===");
        for (JsonNode sz : szervizek) {
            String szId = sz.get("_szervizbevitelid").asText();
            System.out.println("\nSzerviz: " + sz.get("Autoszerviz").asText());

            for (JsonNode k : karbantartasok) {
                if (k.get("_a_k_sz").asText().equals(szId)) {
                    System.out.println("- " + k.get("munkalatokleirasa").asText()
                            + " | " + k.get("munkavallaloneve").asText());
                }
            }
        }

        System.out.println("\n=== 3. ÁTLAGOS JAVÍTÁSI KÖLTSÉG ===");
        double osszeg = 0;
        int db = 0;

        for (JsonNode k : karbantartasok) {
            osszeg += k.get("koltseg").asDouble();
            db++;
        }

        System.out.println("AVG: " + (osszeg / db) + " Ft");

        System.out.println("\n=== 4. DRÁGA JAVÍTÁSOK (>50.000 Ft) ===");
        for (JsonNode k : karbantartasok) {
            if (k.get("koltseg").asDouble() > 50000) {
                System.out.println("- " + k.get("munkalatokleirasa").asText()
                        + " | " + k.get("koltseg").asText() + " Ft");
            }
        }

        System.out.println("\n=== 5. JOIN: TULAJDONOS + AUTÓ ===");
        for (JsonNode b : birtoklas) {
            String tulId = b.get("_a_b_t").asText();
            String autoId = b.get("_a_b_a").asText();

            String nev = "";
            for (JsonNode t : tulajdonosok) {
                if (t.get("_tulajid").asText().equals(tulId)) {
                    nev = t.get("nev").get("vezeteknev").asText()
                            + " " + t.get("nev").get("keresztnev").asText();
                }
            }

            String auto = "";
            for (JsonNode a : autok) {
                if (a.get("_autorendszam").asText().equals(autoId)) {
                    auto = a.get("motor").asText() + " (" + a.get("szin").asText() + ")";
                }
            }

            System.out.println(nev + " -> " + auto);
        }

        System.out.println("\n=== 6. JSON MÓDOSÍTÁS ===");
        for (JsonNode a : autok) {
            ObjectNode obj = (ObjectNode) a;
            obj.put("ellenorizve", true);
            obj.remove("karbantartasok");
        }
        System.out.println("Autók módosítva (ellenorizve hozzáadva, karbantartasok törölve)");

        System.out.println("\n=== 7. VIP TULAJDONOS (legtöbbet költő) ===");

        Map<String, Double> koltes = new HashMap<>();

        for (JsonNode k : karbantartasok) {
            String autoId = k.get("_a_k_a").asText();
            double ar = k.get("koltseg").asDouble();

            for (JsonNode b : birtoklas) {
                if (b.get("_a_b_a").asText().equals(autoId)) {
                    String tulId = b.get("_a_b_t").asText();
                    koltes.put(tulId, koltes.getOrDefault(tulId, 0.0) + ar);
                }
            }
        }

        String maxId = "";
        double max = 0;

        for (Map.Entry<String, Double> e : koltes.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                maxId = e.getKey();
            }
        }

        String vip = "";
        for (JsonNode t : tulajdonosok) {
            if (t.get("_tulajid").asText().equals(maxId)) {
                vip = t.get("nev").get("vezeteknev").asText()
                        + " " + t.get("nev").get("keresztnev").asText();
            }
        }

        System.out.println("VIP tulajdonos: " + vip + " (" + max + " Ft)");

        System.out.println("\n=== 8. ÚJ JSON (SZERVIZEK BEVÉTELE) ===");

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode uj = mapper.createArrayNode();

        for (JsonNode sz : szervizek) {
            String szId = sz.get("_szervizbevitelid").asText();
            double bevetel = 0;

            for (JsonNode k : karbantartasok) {
                if (k.get("_a_k_sz").asText().equals(szId)) {
                    bevetel += k.get("koltseg").asDouble();
                }
            }

            ObjectNode node = mapper.createObjectNode();
            node.put("szerviz", sz.get("Autoszerviz").asText());
            node.put("bevetel", bevetel);
            uj.add(node);
        }

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File("uj_szerviz_bevetel.json"), uj);

        System.out.println("Fájl kész: uj_szerviz_bevetel.json");
    }
}