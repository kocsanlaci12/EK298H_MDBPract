package ek298h;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class EK298HDomWrite {

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();

        Element root = doc.createElement("vendeglatas");
        doc.appendChild(root);

        Element etterem1 = doc.createElement("etterem");
        etterem1.setAttribute("ekod", "e1");

        Element nev1 = doc.createElement("nev");
        nev1.appendChild(doc.createTextNode("Trófea"));

        Element cim1 = doc.createElement("cim");

        Element varos1 = doc.createElement("varos");
        varos1.appendChild(doc.createTextNode("Budapest"));

        Element utca1 = doc.createElement("utca");
        utca1.appendChild(doc.createTextNode("Visegrádi"));

        Element hazszam1 = doc.createElement("hazszam");
        hazszam1.appendChild(doc.createTextNode("13"));

        cim1.appendChild(varos1);
        cim1.appendChild(utca1);
        cim1.appendChild(hazszam1);

        Element csillag1 = doc.createElement("csillag");
        csillag1.appendChild(doc.createTextNode("4"));

        etterem1.appendChild(nev1);
        etterem1.appendChild(cim1);
        etterem1.appendChild(csillag1);

        root.appendChild(etterem1);

        Element etterem2 = doc.createElement("etterem");
        etterem2.setAttribute("ekod", "e2");

        Element nev2 = doc.createElement("nev");
        nev2.appendChild(doc.createTextNode("Gundel"));

        Element cim2 = doc.createElement("cim");

        Element varos2 = doc.createElement("varos");
        varos2.appendChild(doc.createTextNode("Budapest"));

        Element utca2 = doc.createElement("utca");
        utca2.appendChild(doc.createTextNode("Gundel Károly"));

        Element hazszam2 = doc.createElement("hazszam");
        hazszam2.appendChild(doc.createTextNode("4"));

        cim2.appendChild(varos2);
        cim2.appendChild(utca2);
        cim2.appendChild(hazszam2);

        Element csillag2 = doc.createElement("csillag");
        csillag2.appendChild(doc.createTextNode("5"));

        etterem2.appendChild(nev2);
        etterem2.appendChild(cim2);
        etterem2.appendChild(csillag2);

        root.appendChild(etterem2);

        Element foszakacs1 = doc.createElement("foszakacs");
        foszakacs1.setAttribute("fkod", "f1");
        foszakacs1.setAttribute("e_f", "e1");

        Element fnev1 = doc.createElement("nev");
        fnev1.appendChild(doc.createTextNode("Havas Péter"));

        Element feletkor1 = doc.createElement("eletkor");
        feletkor1.appendChild(doc.createTextNode("35"));

        Element fveg1 = doc.createElement("vegzettseg");
        fveg1.appendChild(doc.createTextNode("Paul Bocuse Institute"));

        foszakacs1.appendChild(fnev1);
        foszakacs1.appendChild(feletkor1);
        foszakacs1.appendChild(fveg1);

        root.appendChild(foszakacs1);

        Element szakacs = doc.createElement("szakacs");
        szakacs.setAttribute("szkod", "sz1");
        szakacs.setAttribute("e_sz", "e1");

        Element sznev = doc.createElement("nev");
        sznev.appendChild(doc.createTextNode("Ötlet Elek"));

        Element reszleg = doc.createElement("reszleg");
        reszleg.appendChild(doc.createTextNode("Saucier"));

        Element vegz1 = doc.createElement("vegzettseg");
        vegz1.appendChild(doc.createTextNode("Szakközépiskola"));

        Element vegz2 = doc.createElement("vegzettseg");
        vegz2.appendChild(doc.createTextNode("Le Cordon Bleu"));

        szakacs.appendChild(sznev);
        szakacs.appendChild(reszleg);
        szakacs.appendChild(vegz1);
        szakacs.appendChild(vegz2);

        root.appendChild(szakacs);

        Element vendeg = doc.createElement("vendeg");
        vendeg.setAttribute("vkod", "v1");

        Element vnev = doc.createElement("nev");
        vnev.appendChild(doc.createTextNode("Fekete Péter"));

        Element eletkor = doc.createElement("eletkor");
        eletkor.appendChild(doc.createTextNode("21"));

        vendeg.appendChild(vnev);
        vendeg.appendChild(eletkor);

        root.appendChild(vendeg);

        Element rendeles = doc.createElement("rendeles");
        rendeles.setAttribute("rkod", "r1");
        rendeles.setAttribute("vkod", "v1");
        rendeles.setAttribute("ekod", "e1");

        Element etel = doc.createElement("etel");
        etel.appendChild(doc.createTextNode("Rántott hús"));

        Element osszeg = doc.createElement("osszeg");
        osszeg.appendChild(doc.createTextNode("4500"));

        Element datum = doc.createElement("datum");
        datum.appendChild(doc.createTextNode("2021-09-15"));

        rendeles.appendChild(etel);
        rendeles.appendChild(osszeg);
        rendeles.appendChild(datum);

        root.appendChild(rendeles);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);

        StreamResult file = new StreamResult(new File("EK298HXML1.xml"));
        transformer.transform(source, file);
    }
}