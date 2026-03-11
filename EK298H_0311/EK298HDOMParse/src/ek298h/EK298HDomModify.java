package ek298h;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class EK298HDomModify {

    public static void main(String[] args) {

        try {

            File xmlFile = new File("EK298H_XML.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("vendeg");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) node;

                    if ("v1".equals(elem.getAttribute("vkod"))) {

                        elem.getElementsByTagName("nev")
                                .item(0)
                                .setTextContent("Kovács Béla");

                        elem.getElementsByTagName("eletkor")
                                .item(0)
                                .setTextContent("30");
                    }
                }
            }

            nodeList = doc.getElementsByTagName("gyakornok");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) node;

                    elem.setAttribute("e_gy", "e3");
                }
            }

            nodeList = doc.getElementsByTagName("rendeles");

            while (nodeList.getLength() > 0) {

                Node node = nodeList.item(0);
                node.getParentNode().removeChild(node);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(System.out);

            transformer.transform(source, console);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}