using MongoDB.Driver;
using MongoTest.Models;

class Program
{
    static void Main(string[] args)
    {
        //Kapcsolódás MongoDB-hez
        var client = new MongoClient("mongodb+srv://asd:asd@cluster0.zewoi03.mongodb.net/");

        //Adatbázis kiválasztása
        var database = client.GetDatabase("vendeglatas");
        var etteremCollection = database.GetCollection<Etterem>("ettermek");
        var foszakacsCollection =database.GetCollection<Foszakacs>("foszakacsok");
        var szakacsCollection = database.GetCollection<Szakacs>("szakacsok");
        var gyakornokCollection = database.GetCollection<Gyakornok>("gyakornokok");
        var vendegCollection = database.GetCollection<Vendeg>("vendegek");
        var rendelesCollection = database.GetCollection<Rendeles>("rendelesek");

        //Etterem kiiras
        /*var etteremCollection = database.GetCollection<Etterem>("ettermek");

        var ettermek = etteremCollection.Find(_ => true).ToList();

        foreach(var e in ettermek)
            {
                Console.WriteLine("-------");
                Console.WriteLine($"Név: {e.nev}");
                Console.WriteLine($"Város: {e.cim?.varos}"); 
                Console.WriteLine($"Utca: {e.cim?.utca}"); 
                Console.WriteLine($"Házszám: {e.cim?.hazszam}"); 
                Console.WriteLine($"Csillag: {e.csillag}");  
            }*/

        //Foszakacs kiiras
        /*var foszakacsCollection =database.GetCollection<Foszakacs>("foszakacsok");

        var foszakacsok = foszakacsCollection.Find(_ => true).ToList();

        foreach(var f in foszakacsok)
        {
            Console.WriteLine("-------");
            Console.WriteLine($"Név: {f.nev}");
            Console.WriteLine($"Életkor: {f.eletkor}");
            Console.WriteLine($"Fkod: {f._fkod}");
            Console.WriteLine($"EF: {f._e_f}");

            Console.WriteLine("Végzettseg:");
                foreach (var v in f.vegzettseg)
                {
                    Console.WriteLine(" - " + v);
                }
        }*/

        //Szakacs kiiras
        /*var szakacsCollection = database.GetCollection<Szakacs>("szakacsok");

        var szakacsok = szakacsCollection.Find(_ => true).ToList();

        foreach (var sz in szakacsok)
            {
                Console.WriteLine("-------");
                Console.WriteLine($"Név: {sz.nev}");
                Console.WriteLine($"Részleg: {sz.reszleg}");
                Console.WriteLine($"Szkod: {sz._szkod}");
                Console.WriteLine($"ESZ: {sz._e_sz}");

                Console.WriteLine("Végzettseg:");
                foreach (var v in sz.vegzettseg)
                {
                    Console.WriteLine(" - " + v);
                }
            }*/

        //Gyakornok kiiraas
        /*var gyakornokCollection = database.GetCollection<Gyakornok>("gyakornokok");
        
        var gyakornokok = gyakornokCollection.Find(_ => true).ToList();

        foreach (var g in gyakornokok)
            {
                Console.WriteLine("-------");
                Console.WriteLine($"Név: {g.nev}");
                Console.WriteLine($"Kezdete: {g.gyakorlat?.kezdete}");
                Console.WriteLine($"Időtartama: {g.gyakorlat?.idotartama}");
                Console.WriteLine($"Gykód: {g._gykod}");
                Console.WriteLine($"EGY: {g._e_gy}");

                Console.WriteLine("Műszak:");
                foreach (var m in g.muszak)
                {
                    Console.WriteLine(" - " + m);
                }

            }*/

            //Vendegek kiirasa
            /*
            var vendegCollection = database.GetCollection<Vendeg>("vendegek");

            var vendegek = vendegCollection.Find(_ => true).ToList();
            foreach(var v in vendegek)
                {
                    Console.WriteLine("-------");
                    Console.WriteLine($"Név: {v.nev}");
                    Console.WriteLine($"Életkor: {v.eletkor}");
                    Console.WriteLine($"Város: {v.cim?.varos}");
                    Console.WriteLine($"Utca: {v.cim?.utca}");
                    Console.WriteLine($"Házszám: {v.cim?.hazszam}"); 
                    Console.WriteLine($"Vkod: {v._vkod}"); 
                }
            */

            //Rendelés kiirás
            /*var rendelesCollection = database.GetCollection<Rendeles>("rendelesek");

            var rendelesek = rendelesCollection.Find(_ => true).ToList();

            foreach (var r in rendelesek)
                {
                    Console.WriteLine("-------");
                    Console.WriteLine($"Név: {r.etel}");
                    Console.WriteLine($"Összeg: {r.osszeg}");
                    Console.WriteLine($"Dátum: {r.datum}");
                    Console.WriteLine($"Rkod: {r._rkod}");
                    Console.WriteLine($"Vkod: {r._vkod}");
                    Console.WriteLine($"Ekod: {r._ekod}");
                }*/

            //Új étterem beszúrás
            //var etteremCollection = database.GetCollection<Etterem>("ettermek");

            /*var ujEtterem = new Etterem
            {
                nev = "Valhalla",
                cim = new Cim
                {
                    varos = "Nyíregyháza",
                    utca = "Sas",
                    hazszam = 3
                },
                csillag = 5
            };
            etteremCollection.InsertOne(ujEtterem);
            Console.WriteLine("Sikeres beszurás!");*/

            //Új főszakács beszúrása
            /*var foszakacsCollection = database.GetCollection<Foszakacs>("foszakacsok");

            var ujFoszakacs = new Foszakacs
            {
                nev = "Hegedűs Lajos",
                eletkor = 25,
                vegzettseg = new List<string> { "Le Cordon Bleu" },
                _fkod = "f3",
                _e_f = "e1"
            };

            Console.WriteLine("Sikeres beszurás!");

            foszakacsCollection.InsertOne(ujFoszakacs);*/

            // Csillag módosítás
            /*var filter = Builders<Etterem>.Filter.Eq(e => e.nev, "Valhalla");

            var update = Builders<Etterem>.Update.Set(e => e.csillag, 3);

            etteremCollection.UpdateOne(filter, update);*
            
            Console.WriteLine("Sikeres módosítás!");/
            

            //30 alatti törlés
            /*var filter = Builders<Foszakacs>.Filter.Lt(f => f.eletkor, 30);
            foszakacsCollection.DeleteMany(filter);
            Console.WriteLine("Sikeres törlés!");*/

            //Szakacs reszlegek szerint
            /*var reszlegek = szakacsCollection.Find(_ => true).ToList();

            foreach (var sz in reszlegek)
            {
                Console.WriteLine($"{sz.nev} - {sz.reszleg}");
            }*/

            //4+ csillagos éttermek:
            /*var result = etteremCollection.Find(e => e.csillag >= 4).ToList();

            foreach (var e in result)
            {
                Console.WriteLine($"Név: {e.nev} - Csillag: {e.csillag}");
            }*/

            //Budapest vagy 5
            /*var filter =
            Builders<Etterem>.Filter.Eq(e => e.cim.varos, "Nyíregyháza") |
            Builders<Etterem>.Filter.Eq(e => e.csillag, 5);

            var result = etteremCollection.Find(filter).ToList();

            foreach (var e in result)
            {
                Console.WriteLine($"{e.nev} - {e.cim.varos} - {e.csillag}");
            }*/

            //25-40 eves vendegek

            /*var result = vendegCollection.Find(v => v.eletkor >= 25 && v.eletkor <= 40).ToList();

            foreach (var v in result)
            {
                Console.WriteLine($"{v.nev} - {v.eletkor}");
            }*/

            //MŰSZAK HOZZÁADÁS (GYAKORNOK)
            /*var filter = Builders<Gyakornok>.Filter.Eq(g => g.nev, "Szilágyi István");

            var update = Builders<Gyakornok>.Update.Push(g => g.muszak, "Éjszaka");

            gyakornokCollection.UpdateOne(filter, update);
            Console.WriteLine("Sikeres hozzadas!");*/
            

            //Városonként átlag csillag
            
            /*var result = etteremCollection.Aggregate()
                .Group(e => e.cim.varos, g => new
                {
                    Varos = g.Key,
                    Darab = g.Count(),
                    AtlagCsillag = g.Average(x => x.csillag)
                })
                .ToList();

            foreach (var r in result)
            {
                Console.WriteLine($"{r.Varos} - db: {r.Darab} - átlag: {r.AtlagCsillag}");
            }*/

            //Szakácsok száma éttermenként
            /*var result = szakacsCollection.Aggregate()
            .Group(s => s._e_sz, g => new
            {
                EtteremKod = g.Key,
                Szam = g.Count()
            })
            .ToList();

            foreach (var r in result)
            {
                Console.WriteLine($"{r.EtteremKod} - {r.Szam} fő");
            }
            */
            
            // Legidősebb szakács éttermenként
            /*var result = foszakacsCollection.Aggregate()
            .SortByDescending(s => s.eletkor)
            .Group(s => s._fkod, g => new
            {
                EtteremKod = g.Key,
                LegidosebbNev = g.First().nev,
                Kor = g.First().eletkor
            })
            .ToList();

            foreach (var r in result)
            {
                Console.WriteLine($"{r.EtteremKod} - {r.LegidosebbNev} ({r.Kor})");
            }*/

            //Éttermek és szakácsok összekapcsolása
            /*var result = etteremCollection.Aggregate()
            .Lookup("szakacsok", "_ekod", "_e_sz", "szakacsok")
            .ToList();

            foreach (var r in result)
                {
                    Console.WriteLine($"Étterem: {r["nev"]}");

                    var szakacsok = r["szakacsok"].AsBsonArray;

                    foreach (var s in szakacsok)
                    {
                        Console.WriteLine($"  Szakács: {s["nev"]}");
                    }
                }*/

    }
}