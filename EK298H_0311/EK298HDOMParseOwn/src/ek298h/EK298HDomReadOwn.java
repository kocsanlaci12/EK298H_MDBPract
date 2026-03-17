package ek298h;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EK298HDomReadOwn {
    public static void main(String[] args)
            throws SAXException, IOException, ParserConfigurationException {

        File xmlFile = new File("EK298H_XML1.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList autoList = doc.getElementsByTagName("auto");

        for (int i = 0; i < autoList.getLength(); i++) {
            Node node = autoList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String autorendszam = elem.getAttribute("autorendszam");
                String szin = elem.getElementsByTagName("szin").item(0).getTextContent();
                String motor = elem.getElementsByTagName("motor").item(0).getTextContent();
                String karbantartasok = elem.getElementsByTagName("karbantartasok").item(0).getTextContent();
                String muszakiallapot = elem.getElementsByTagName("muszakiallapot").item(0).getTextContent();

                System.out.println("\nAutó rendszám: " + autorendszam);
                System.out.println("Szín: " + szin);
                System.out.println("Motor: " + motor);
                System.out.println("Karbantartások száma: " + karbantartasok);
                System.out.println("Műszaki állapot: " + muszakiallapot);
            }
        }

        NodeList szervizbevitelList = doc.getElementsByTagName("szervizbevitel");

        for (int i = 0; i < szervizbevitelList.getLength(); i++) {
            Node node = szervizbevitelList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String szervizbevitelid = elem.getAttribute("szervizbevitelid");
                String autoszerviz = elem.getElementsByTagName("Autoszerviz").item(0).getTextContent();

                System.out.println("\nSzervizbeviteli ID: " + szervizbevitelid);
                System.out.println("Autószerviz: " + autoszerviz);
            }
        }

        NodeList karbantartasList = doc.getElementsByTagName("karbantartas");

        for (int i = 0; i < karbantartasList.getLength(); i++) {
            Node node = karbantartasList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String szervizid = elem.getAttribute("a_k_sz");
                String autorendszam = elem.getAttribute("a_k_a");

                String munkalatok = elem.getElementsByTagName("munkalatokleirasa").item(0).getTextContent();
                String koltseg = elem.getElementsByTagName("koltseg").item(0).getTextContent();
                String munkavallalonev = elem.getElementsByTagName("munkavallaloneve").item(0).getTextContent();
                String datum = elem.getElementsByTagName("datum").item(0).getTextContent();

                System.out.println("\nKarbantartás - Autó: " + autorendszam + ", Szerviz: " + szervizid);
                System.out.println("Munkálatok: " + munkalatok);
                System.out.println("Költség: " + koltseg + " Ft");
                System.out.println("Munkavállaló: " + munkavallalonev);
                System.out.println("Dátum: " + datum);
            }
        }

        NodeList tulajdonosList = doc.getElementsByTagName("tulajdonos");

        for (int i = 0; i < tulajdonosList.getLength(); i++) {
            Node node = tulajdonosList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String tulajid = elem.getAttribute("tulajid");
                String email = elem.getElementsByTagName("emailcim").item(0).getTextContent();
                String telefon = elem.getElementsByTagName("telefonszam").item(0).getTextContent();
                String szuletesidatum = elem.getElementsByTagName("szuletesidatum").item(0).getTextContent();

                Element nevElem = (Element) elem.getElementsByTagName("nev").item(0);
                String vezeteknev = nevElem.getElementsByTagName("vezeteknev").item(0).getTextContent();
                String keresztnev = nevElem.getElementsByTagName("keresztnev").item(0).getTextContent();

                Element lakcimElem = (Element) elem.getElementsByTagName("lakcim").item(0);
                String iranyitoszam = lakcimElem.getElementsByTagName("iranyitoszam").item(0).getTextContent();
                String varos = lakcimElem.getElementsByTagName("varos").item(0).getTextContent();
                String utcahazszam = lakcimElem.getElementsByTagName("utcahazszam").item(0).getTextContent();

                System.out.println("\nTulajdonos ID: " + tulajid);
                System.out.println("Név: " + vezeteknev + " " + keresztnev);
                System.out.println("Email: " + email);
                System.out.println("Telefonszám: " + telefon);
                System.out.println("Születési dátum: " + szuletesidatum);
                System.out.println("Lakcím: " + iranyitoszam + ", " + varos + ", " + utcahazszam);
            }
        }

        NodeList birtokolList = doc.getElementsByTagName("birtokol");

        for (int i = 0; i < birtokolList.getLength(); i++) {
            Node node = birtokolList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String tulajid = elem.getAttribute("a_b_t");
                String autorendszam = elem.getAttribute("a_b_a");

                String vasarlasdatum = elem.getElementsByTagName("vasarlasdatuma").item(0).getTextContent();
                String vasarlasiar = elem.getElementsByTagName("vasarlasiar").item(0).getTextContent();
                String eladasdatum = elem.getElementsByTagName("eladasdatuma").item(0).getTextContent();

                System.out.println("\nBirtoklás - Tulajdonos: " + tulajid + ", Autó: " + autorendszam);
                System.out.println("Vásárlás dátuma: " + vasarlasdatum);
                System.out.println("Vásárlási ár: " + vasarlasiar + " Ft");
                System.out.println("Eladás dátuma: " + (eladasdatum.isEmpty() ? "Nincs eladva" : eladasdatum));
            }
        }

        NodeList markaList = doc.getElementsByTagName("marka");

        for (int i = 0; i < markaList.getLength(); i++) {
            Node node = markaList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String markaid = elem.getAttribute("markaid");
                String nev = elem.getElementsByTagName("nev").item(0).getTextContent();
                String szekhely = elem.getElementsByTagName("szekhely").item(0).getTextContent();
                String modellek = elem.getElementsByTagName("modellek").item(0).getTextContent();
                String statisztika = elem.getElementsByTagName("statisztika").item(0).getTextContent();

                System.out.println("\nMárka ID: " + markaid);
                System.out.println("Név: " + nev);
                System.out.println("Székhely: " + szekhely);
                System.out.println("Modellek: " + modellek);
                System.out.println("Statisztika: " + statisztika);
            }
        }

        NodeList gyartottList = doc.getElementsByTagName("gyartott");

        for (int i = 0; i < gyartottList.getLength(); i++) {
            Node node = gyartottList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String markaid = elem.getAttribute("a_gy_m");
                String autorendszam = elem.getAttribute("a_gy_a");

                String tipus = elem.getElementsByTagName("tipus").item(0).getTextContent();
                String gyartasiev = elem.getElementsByTagName("gyartasiev").item(0).getTextContent();

                System.out.println("\nGyártott autó - Autó: " + autorendszam + ", Márka: " + markaid);
                System.out.println("Típus: " + tipus);
                System.out.println("Gyártási év: " + gyartasiev);
            }
        }
    }
}