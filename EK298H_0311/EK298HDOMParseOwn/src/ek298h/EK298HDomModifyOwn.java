package ek298h;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class EK298HDomModifyOwn {

    public static void main(String[] args) {

        try {

            File xmlFile = new File("EK298H_XML1.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("auto");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) node;

                    if ("ASD123".equals(elem.getAttribute("autorendszam"))) {

                        elem.getElementsByTagName("szin")
                                .item(0)
                                .setTextContent("Piros");

                        elem.getElementsByTagName("muszakiallapot")
                                .item(0)
                                .setTextContent("Jó");
                    }
                }
            }

            nodeList = doc.getElementsByTagName("karbantartas");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) node;

                    elem.setAttribute("a_k_sz", "SZB789");
                }
            }

            nodeList = doc.getElementsByTagName("marka");

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