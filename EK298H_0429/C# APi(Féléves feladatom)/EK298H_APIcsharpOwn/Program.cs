using MongoDB.Driver;
using MongoTest.Models;

class Program
{
    static void Main(string[] args)
    {
        var client = new MongoClient("mongodb+srv://asd:asd@cluster0.zewoi03.mongodb.net/");

        var database = client.GetDatabase("autoszerviz");

        var autoCollection = database.GetCollection<Auto>("auto");
        var szervizbevitelCollection = database.GetCollection<Szervizbevitel>("szervizbevitel");
        var tulajdonosCollection = database.GetCollection<Tulajdonos>("tulajdonos");
        var markaCollection = database.GetCollection<Marka>("marka");
        var karbantartasCollection = database.GetCollection<Karbantartas>("karbantartas");
        var gyartottCollection = database.GetCollection<Gyartott>("gyartott");
        var birtokolCollection = database.GetCollection<Birtokol>("birtokol");

        
        /* AUTO kiírás
        Console.WriteLine("\n===== AUTO =====");

        var autok = autoCollection.Find(_ => true).ToList();

        foreach (var a in autok)
        {
            Console.WriteLine("-------");
            Console.WriteLine($"Rendszám: {a._autorendszam}");
            Console.WriteLine($"Szín: {a.szin}");
            Console.WriteLine($"Motor: {a.motor}");
            Console.WriteLine($"Karbantartások: {a.karbantartasok}");
            Console.WriteLine($"Műszaki állapot: {a.muszakiallapot}");
        }
        */

        
        /* SZERVIZBEVITEL kiírás
        Console.WriteLine("\n===== SZERVIZBEVITEL =====");

        var szervizek = szervizbevitelCollection.Find(_ => true).ToList();

        foreach (var s in szervizek)
        {
            Console.WriteLine("-------");
            Console.WriteLine($"Szerviz: {s.Autoszerviz}");
            Console.WriteLine($"ID: {s._szervizbevitelid}");
        } */

        
        /* TULAJDONOS kiírás
        Console.WriteLine("\n===== TULAJDONOS =====");

        var tulajdonosok = tulajdonosCollection.Find(_ => true).ToList();

        foreach (var t in tulajdonosok)
        {
            Console.WriteLine("-------");
            Console.WriteLine($"Név: {t.nev.vezeteknev} {t.nev.keresztnev}");
            Console.WriteLine($"Email: {t.emailcim}");
            Console.WriteLine($"Telefon: {t.telefonszam}");
            Console.WriteLine($"Város: {t.lakcim.varos}");
            Console.WriteLine($"ID: {t._tulajid}");
        } */

        /* MARKA kiírás
        Console.WriteLine("\n===== MARKA =====");

        var markak = markaCollection.Find(_ => true).ToList();

        foreach (var m in markak)
        {
            Console.WriteLine("-------");
            Console.WriteLine($"Név: {m.nev}");
            Console.WriteLine($"Székhely: {m.szekhely}");

            Console.WriteLine("Modellek:");
            foreach (var model in m.modellek)
            {
                Console.WriteLine(" - " + model);
            }

            Console.WriteLine($"Statisztika: {m.statisztika}");
        } */

        /* KARBANTARTAS kiíráa
        Console.WriteLine("\n===== KARBANTARTAS =====");

        var karbantartasok = karbantartasCollection.Find(_ => true).ToList();

        foreach (var k in karbantartasok)
        {
            Console.WriteLine("-------");
            Console.WriteLine($"Munkás: {k.munkavallaloneve}");
            Console.WriteLine($"Dátum: {k.datum}");
            Console.WriteLine($"Költség: {k.koltseg}");
            Console.WriteLine($"Autó: {k._a_k_a}");

            Console.WriteLine("Munkálatok:");
            foreach (var m in k.munkalatokleirasa)
            {
                Console.WriteLine(" - " + m);
            }
        } */

        /* GYARTOTT kiírás
        Console.WriteLine("\n===== GYARTOTT =====");

        var gyartottak = gyartottCollection.Find(_ => true).ToList();

        foreach (var g in gyartottak)
        {
            Console.WriteLine("-------");
            Console.WriteLine($"Típus: {g.tipus}");
            Console.WriteLine($"Év: {g.gyartasiev}");
            Console.WriteLine($"Márka ID: {g._a_gy_m}");
            Console.WriteLine($"Autó: {g._a_gy_a}");
        }
        */

        /* BIRTOKOL kiírás
        Console.WriteLine("\n===== BIRTOKOL =====");

        var birtokolok = birtokolCollection.Find(_ => true).ToList();

        foreach (var b in birtokolok)
        {
            Console.WriteLine("-------");
            Console.WriteLine($"Vásárlás: {b.vasarlasdatuma}");
            Console.WriteLine($"Ár: {b.vasarlasiar}");
            Console.WriteLine($"Eladás: {b.eladasdatuma}");
            Console.WriteLine($"Tulaj: {b._a_b_t}");
            Console.WriteLine($"Autó: {b._a_b_a}");
        }

        */
        /* Új autó beszúrás
        var ujAuto = new Auto
        {
            szin = "Fehér",
            motor = "1.8 Benzin",
            karbantartasok = "2",
            muszakiallapot = "Újszerű",
            _autorendszam = "SDK213"
        };

        autoCollection.InsertOne(ujAuto);
        Console.WriteLine("Sikeres autó beszúrás!"); */

        /* Új tulajdonos beszúrás
        var ujTulajdonos = new Tulajdonos
        {
            emailcim = "lajos@email.hu",
            telefonszam = "+36123456789",
            szuletesidatum = "1995-01-01",

            nev = new Nev
            {
                vezeteknev = "Lajos",
                keresztnev = "Márton"
            },

            lakcim = new Lakcim
            {
                iranyitoszam = "1000",
                varos = "Budapest",
                utcahazszam = "Péter utca 1."
            },

            _tulajid = "T999"
        };

        tulajdonosCollection.InsertOne(ujTulajdonos);
        Console.WriteLine("Sikeres tulajdonos beszúrás!");
        */

        /* Műszaki állapot módosítás
        var filter = Builders<Auto>.Filter.Eq(a => a._autorendszam, "ABC111");

        var update = Builders<Auto>.Update.Set(a => a.muszakiallapot, "Rossz");

        autoCollection.UpdateOne(filter, update);

        Console.WriteLine("Sikeres módosítás!");
        */

        /* Rossz autó törlés
        var filter = Builders<Auto>.Filter.Eq(a => a.muszakiallapot, "Rossz");
        autoCollection.DeleteMany(filter);
        Console.WriteLine("Sikeres törlés!");
        */

        /* Autók szín szerint
        var autok = autoCollection.Find(_ => true).ToList();

        foreach (var a in autok)
        {
            Console.WriteLine($"{a._autorendszam} - {a.szin}");
        }*/

        /* Jó állapotú autók
        var result = autoCollection.Find(a => a.muszakiallapot == "Jó").ToList();

        foreach (var a in result)
        {
            Console.WriteLine($"Rendszám: {a._autorendszam} - Állapot: {a.muszakiallapot}");
        }
        */

        /* Fehér VAGY Jó állapotú autók
        var filter =
            Builders<Auto>.Filter.Eq(a => a.szin, "Fehér") |   // példa város helyett
            Builders<Auto>.Filter.Eq(a => a.muszakiallapot, "Jó");

        var result = autoCollection.Find(filter).ToList();

        foreach (var a in result)
        {
            Console.WriteLine($"{a._autorendszam} - {a.szin} - {a.muszakiallapot}");
        }
        */

        /* 3M és 6M közötti vásárlási ár
        var result = birtokolCollection.Find(
            b => b.vasarlasiar.CompareTo("3000000") >= 0 &&
                b.vasarlasiar.CompareTo("6000000") <= 0
        ).ToList();

        foreach (var b in result)
        {
            Console.WriteLine($"Autó: {b._a_b_a} - Ár: {b.vasarlasiar}");
        }*/

        /* Új munkálat hozzáadása
        var filter = Builders<Karbantartas>.Filter.Eq(k => k._a_k_a, "ABC111");

        var update = Builders<Karbantartas>.Update.Push(
            k => k.munkalatokleirasa,
            "Dugattyú csere"
        );

        karbantartasCollection.UpdateOne(filter, update);

        Console.WriteLine("Sikeres hozzáadás!"); */

        /* Színenként átlag karbantartás
        var autok = autoCollection.Find(_ => true).ToList();

        var result = autok
            .GroupBy(a => a.szin)
            .Select(g => new
            {
                Szin = g.Key,
                Darab = g.Count(),
                AtlagKarbantartas = g.Average(x => Convert.ToInt32(x.karbantartasok))
            });

        foreach (var r in result)
        {
            Console.WriteLine($"{r.Szin} - db: {r.Darab} - átlag: {r.AtlagKarbantartas}");
        } */

        /* Karbantartások száma autónként
        var result = karbantartasCollection.Aggregate()
            .Group(k => k._a_k_a, g => new
            {
                AutoRendszam = g.Key,
                KarbantartasokSzama = g.Count()
            })
            .ToList();

        foreach (var r in result)
        {
            Console.WriteLine($"{r.AutoRendszam} - {r.KarbantartasokSzama} db");
        } */

        /* Legdrágább autó tulajdonosonként
        var birtoklasok = birtokolCollection.Find(_ => true).ToList();

        var result = birtoklasok
            .OrderByDescending(b => Convert.ToInt32(b.vasarlasiar))
            .GroupBy(b => b._a_b_t)
            .Select(g => new
            {
                Tulajdonos = g.Key,
                Auto = g.First()._a_b_a,
                Ar = g.First().vasarlasiar
            });

        foreach (var r in result)
        {
            Console.WriteLine($"{r.Tulajdonos} - {r.Auto} ({r.Ar} Ft)");
        } */

        /* Autók és karbantartások összekapcsolása
        var result = autoCollection.Aggregate()
            .Lookup(
                "karbantartas",      // másik collection
                "_autorendszam",     // auto mező
                "_a_k_a",            // karbantartas mező
                "karbantartasok"     // új tömb neve
            )
            .ToList();

        foreach (var r in result)
        {
            Console.WriteLine($"Autó: {r["_autorendszam"]}");

            var karbantartasok = r["karbantartasok"].AsBsonArray;

            foreach (var k in karbantartasok)
            {
                Console.WriteLine($"  Munkavállaló: {k["munkavallaloneve"]}");
            }
        }
        */

    }
}