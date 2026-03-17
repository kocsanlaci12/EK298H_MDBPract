package ek298h;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class EK298HDomQueryOwn {

    public static void main(String[] args)
            throws SAXException, IOException, ParserConfigurationException {

        File xmlFile = new File("EK298H_XML1.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        System.out.println("\nKiváló állapotú autók:\n");

        NodeList nodeList = doc.getElementsByTagName("auto");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node nNode = nodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                String condition = elem.getElementsByTagName("muszakiallapot").item(0).getTextContent();

                if ("Kiváló".equals(condition)) {

                    String id = elem.getAttribute("autorendszam");
                    String color = elem.getElementsByTagName("szin").item(0).getTextContent();
                    String engine = elem.getElementsByTagName("motor").item(0).getTextContent();

                    System.out.println("Rendszám: " + id);
                    System.out.println("Szín: " + color);
                    System.out.println("Motor: " + engine);
                    System.out.println("Állapot: " + condition + "\n");
                }
            }
        }

        System.out.println("40000 Ft feletti karbantartások:\n");

        nodeList = doc.getElementsByTagName("karbantartas");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node nNode = nodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                int cost = Integer.parseInt(
                        elem.getElementsByTagName("koltseg").item(0).getTextContent()
                );

                if (cost > 40000) {

                    String id = elem.getAttribute("a_k_sz");
                    String work = elem.getElementsByTagName("munkalatokleirasa").item(0).getTextContent();
                    String date = elem.getElementsByTagName("datum").item(0).getTextContent();
                    String worker = elem.getElementsByTagName("munkavallaloneve").item(0).getTextContent();

                    System.out.println("Szerviz ID: " + id);
                    System.out.println("Munkálatok: " + work);
                    System.out.println("Költség: " + cost);
                    System.out.println("Dátum: " + date);
                    System.out.println("Munkavállaló: " + worker + "\n");
                }
            }
        }

        System.out.println("Debreceni tulajdonosok:\n");

        nodeList = doc.getElementsByTagName("tulajdonos");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node nNode = nodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                String city = elem.getElementsByTagName("varos").item(0).getTextContent();

                if ("Debrecen".equals(city)) {

                    String id = elem.getAttribute("tulajid");

                    String lastname = elem.getElementsByTagName("vezeteknev").item(0).getTextContent();
                    String firstname = elem.getElementsByTagName("keresztnev").item(0).getTextContent();

                    String email = elem.getElementsByTagName("emailcim").item(0).getTextContent();
                    String phone = elem.getElementsByTagName("telefonszam").item(0).getTextContent();
                    String address = elem.getElementsByTagName("utcahazszam").item(0).getTextContent();

                    System.out.println("Tulajdonos ID: " + id);
                    System.out.println("Név: " + lastname + " " + firstname);
                    System.out.println("Email: " + email);
                    System.out.println("Telefon: " + phone);
                    System.out.println("Cím: " + city + ", " + address + "\n");
                }
            }
        }
    }
}