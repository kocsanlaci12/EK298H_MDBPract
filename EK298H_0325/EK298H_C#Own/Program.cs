using System;
using System.Linq;
using System.Xml.Linq;

namespace AutoszervizLINQ
{
    class Program
    {
        static void Main(string[] args)
        {
            // 1. XML betöltése
            XDocument doc = XDocument.Load("EK298H_XML1.xml");
            XElement gyoker = doc.Descendants("autoszervizeles").First();

            Console.WriteLine("\n(0.) A gyökér elem / teljes dokumentum:\n");
            Console.WriteLine(gyoker);

            // 2. Egyszerű lekérdezés: karbantartások egy adott szervizben
            Console.WriteLine("\n(1.) Karbantartások a Rapid Autószerviz Kft.-ben:\n");
            var szervizKarban = gyoker.Descendants("karbantartas")
                .Where(k => k.Attribute("a_k_sz").Value == "SZB001")
                .ToList();
            szervizKarban.ForEach(k =>
                Console.WriteLine($"- Autó: {k.Attribute("a_k_a").Value}, Munkálatok: {k.Descendants("munkalatokleirasa").First().Value}")
            );

            // 3. Join: tulajdonos, autó, karbantartás
            Console.WriteLine("\n(2.) Tulajdonos, autó, szerviz, munkálatok és költség:\n");
            var tulajAutoKarbaJoin = gyoker.Descendants("karbantartas")
                .Select(k =>
                {
                    var autoID = k.Attribute("a_k_a").Value;
                    var tulaj = gyoker.Descendants("birtokol")
                        .Where(b => b.Attribute("a_b_a").Value == autoID)
                        .Select(b => gyoker.Descendants("tulajdonos")
                            .First(t => t.Attribute("tulajid").Value == b.Attribute("a_b_t").Value)
                            .Descendants("nev")
                            .FirstOrDefault().Value)
                        .FirstOrDefault();

                    var szerviz = gyoker.Descendants("szervizbevitel")
                        .First(s => s.Attribute("szervizbevitelid").Value == k.Attribute("a_k_sz").Value)
                        .Descendants("Autoszerviz")
                        .First().Value;

                    var munkalat = k.Descendants("munkalatokleirasa").First().Value;
                    var koltseg = k.Descendants("koltseg").First().Value;

                    return new { Tulaj = tulaj, Auto = autoID, Szerviz = szerviz, Munkalat = munkalat, Koltseg = koltseg };
                })
                .ToList();

            tulajAutoKarbaJoin.ForEach(j =>
                Console.WriteLine($"- Tulajdonos: {j.Tulaj}, Autó: {j.Auto}, Szerviz: {j.Szerviz}, Munkálat: {j.Munkalat}, Költség: {j.Koltseg}")
            );

            // 4. Aggregáció: átlagos karbantartási költség
            var atlagKoltseg = gyoker.Descendants("karbantartas")
                .Select(k => double.Parse(k.Descendants("koltseg").First().Value))
                .Average();
            Console.WriteLine($"\n(3.) Átlagos karbantartási költség: {atlagKoltseg}");

            // 5. Módosítás: minden autó színét "Fekete" → "Sötétkék"
            Console.WriteLine("\n(4.) Minden fekete autó színét Sötétkékre módosítom:\n");
            gyoker.Descendants("auto")
                .Where(a => a.Descendants("szin").First().Value == "Fekete")
                .ToList()
                .ForEach(a =>
                {
                    Console.WriteLine($"- Autó {a.Attribute("autorendszam").Value}: {a.Descendants("szin").First().Value} → Sötétkék");
                    a.Descendants("szin").First().Value = "Sötétkék";
                });

            doc.Save("EK298H_XML1_modositott.xml");
            Console.WriteLine("Az új fájl neve: EK298H_XML1_modositott.xml");

            // 6. Törlés: karbantartások 50000 Ft felett
            Console.WriteLine("\n(5.) Törlöm az összes 50.000 Ft feletti karbantartást:\n");
            gyoker.Descendants("karbantartas")
                .Where(k => double.Parse(k.Descendants("koltseg").First().Value) > 50000)
                .ToList()
                .ForEach(k =>
                {
                    Console.WriteLine($"- Törlöm: Autó {k.Attribute("a_k_a").Value}, Költség: {k.Descendants("koltseg").First().Value}");
                    k.Remove();
                });

            doc.Save("EK298H_XML1_torolt.xml");
            Console.WriteLine("Az új fájl neve: EK298H_XML1_torolt.xml");

            // 7. Új XML létrehozása kódból: új autó és tulajdonos
            Console.WriteLine("\n(6.) Új XML dokumentum létrehozása:\n");
            XElement ujGyoker = new XElement("autoszervizeles",
                new XElement("auto", new XAttribute("autorendszam", "XYZ789"),
                    new XElement("szin", "Fehér"),
                    new XElement("motor", "1.6 Benzin"),
                    new XElement("karbantartasok", "0"),
                    new XElement("muszakiallapot", "Új")
                ),
                new XElement("tulajdonos", new XAttribute("tulajid", "T002"),
                    new XElement("nev",
                        new XElement("vezeteknev", "Nagy"),
                        new XElement("keresztnev", "László")
                    ),
                    new XElement("emailcim", "nagy.laszlo@email.hu")
                )
            );

            XDocument ujDoc = new XDocument(ujGyoker);
            ujDoc.Save("autoszerviz_uj.xml");
            Console.WriteLine("Az új fájl neve: autoszerviz_uj.xml");

            Console.WriteLine("\n--- Program vége ---");
        }
    }
}