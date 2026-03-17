package ek298h;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class EK298HDomWriteOwn {

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();

        Element root = doc.createElement("autoszervizeles");
        doc.appendChild(root);

        Element auto = doc.createElement("auto");
        auto.setAttribute("autorendszam", "ASD123");

        Element szin = doc.createElement("szin");
        szin.appendChild(doc.createTextNode("Fekete"));

        Element motor = doc.createElement("motor");
        motor.appendChild(doc.createTextNode("2.0 TDI Dízel"));

        Element karb = doc.createElement("karbantartasok");
        karb.appendChild(doc.createTextNode("3"));

        Element allapot = doc.createElement("muszakiallapot");
        allapot.appendChild(doc.createTextNode("Kiváló"));

        auto.appendChild(szin);
        auto.appendChild(motor);
        auto.appendChild(karb);
        auto.appendChild(allapot);

        root.appendChild(auto);

        Element szerviz = doc.createElement("szervizbevitel");
        szerviz.setAttribute("szervizbevitelid", "SZB001");

        Element szervizNev = doc.createElement("Autoszerviz");
        szervizNev.appendChild(doc.createTextNode("Rapid Autószerviz Kft."));

        szerviz.appendChild(szervizNev);
        root.appendChild(szerviz);

        Element karbantartas = doc.createElement("karbantartas");
        karbantartas.setAttribute("a_k_sz", "SZB001");
        karbantartas.setAttribute("a_k_a", "ASD123");

        Element munka = doc.createElement("munkalatokleirasa");
        munka.appendChild(doc.createTextNode("Olajcsere, szűrők cseréje, fékbetét ellenőrzés"));

        Element koltseg = doc.createElement("koltseg");
        koltseg.appendChild(doc.createTextNode("45000"));

        Element dolgozo = doc.createElement("munkavallaloneve");
        dolgozo.appendChild(doc.createTextNode("Kiss Gábor"));

        Element datum = doc.createElement("datum");
        datum.appendChild(doc.createTextNode("2023-05-12"));

        karbantartas.appendChild(munka);
        karbantartas.appendChild(koltseg);
        karbantartas.appendChild(dolgozo);
        karbantartas.appendChild(datum);

        root.appendChild(karbantartas);

        Element tulaj = doc.createElement("tulajdonos");
        tulaj.setAttribute("tulajid", "T001");

        Element email = doc.createElement("emailcim");
        email.appendChild(doc.createTextNode("kovacs.peter@email.hu"));

        Element telefon = doc.createElement("telefonszam");
        telefon.appendChild(doc.createTextNode("+36201234567"));

        Element szuletesi = doc.createElement("szuletesidatum");
        szuletesi.appendChild(doc.createTextNode("1988-03-14"));

        Element nevElem = doc.createElement("nev");

        Element vezetek = doc.createElement("vezeteknev");
        vezetek.appendChild(doc.createTextNode("Kovács"));

        Element kereszt = doc.createElement("keresztnev");
        kereszt.appendChild(doc.createTextNode("Péter"));

        nevElem.appendChild(vezetek);
        nevElem.appendChild(kereszt);

        Element lakcim = doc.createElement("lakcim");

        Element irsz = doc.createElement("iranyitoszam");
        irsz.appendChild(doc.createTextNode("4025"));

        Element varos = doc.createElement("varos");
        varos.appendChild(doc.createTextNode("Debrecen"));

        Element utca = doc.createElement("utcahazszam");
        utca.appendChild(doc.createTextNode("Piac utca 12."));

        lakcim.appendChild(irsz);
        lakcim.appendChild(varos);
        lakcim.appendChild(utca);

        tulaj.appendChild(email);
        tulaj.appendChild(telefon);
        tulaj.appendChild(szuletesi);
        tulaj.appendChild(nevElem);
        tulaj.appendChild(lakcim);

        root.appendChild(tulaj);

        Element birtokol = doc.createElement("birtokol");
        birtokol.setAttribute("a_b_t", "T001");
        birtokol.setAttribute("a_b_a", "ASD123");

        Element vasarlas = doc.createElement("vasarlasdatuma");
        vasarlas.appendChild(doc.createTextNode("2020-07-01"));

        Element ar = doc.createElement("vasarlasiar");
        ar.appendChild(doc.createTextNode("3200000"));

        Element eladas = doc.createElement("eladasdatuma");
        eladas.appendChild(doc.createTextNode(""));

        birtokol.appendChild(vasarlas);
        birtokol.appendChild(ar);
        birtokol.appendChild(eladas);

        root.appendChild(birtokol);

        Element marka = doc.createElement("marka");
        marka.setAttribute("markaid", "M001");

        Element mnev = doc.createElement("nev");
        mnev.appendChild(doc.createTextNode("Volkswagen"));

        Element szekhely = doc.createElement("szekhely");
        szekhely.appendChild(doc.createTextNode("Wolfsburg, Németország"));

        Element modellek = doc.createElement("modellek");
        modellek.appendChild(doc.createTextNode("Golf, Passat, Polo, Tiguan"));

        Element stat = doc.createElement("statisztika");
        stat.appendChild(doc.createTextNode("2023-ban 4.9 millió eladott jármű"));

        marka.appendChild(mnev);
        marka.appendChild(szekhely);
        marka.appendChild(modellek);
        marka.appendChild(stat);

        root.appendChild(marka);

        Element gyartott = doc.createElement("gyartott");
        gyartott.setAttribute("a_gy_m", "M001");
        gyartott.setAttribute("a_gy_a", "ASD123");

        Element tipus = doc.createElement("tipus");
        tipus.appendChild(doc.createTextNode("Golf VII"));

        Element ev = doc.createElement("gyartasiev");
        ev.appendChild(doc.createTextNode("2018"));

        gyartott.appendChild(tipus);
        gyartott.appendChild(ev);

        root.appendChild(gyartott);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);

        StreamResult file = new StreamResult(new File("EK298HXMLOwn.xml"));
        transformer.transform(source, file);
    }
}