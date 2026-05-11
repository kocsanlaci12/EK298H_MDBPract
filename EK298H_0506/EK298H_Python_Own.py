import pymongo as mongo

client = mongo.MongoClient(
    "mongodb+srv://asd:asd@cluster0.zewoi03.mongodb.net/"
)

db = client["autoszerviz"]

auto_coll = db["auto"]
marka_coll = db["marka"]
tulajdonos_coll = db["tulajdonos"]

# Összes autó kiírása
# print("\n--- Összes autó ---")
#for auto in auto_coll.find():
#    print(auto)

# Összes márka kiírása
# -----------------------------
#print("\n--- Összes márka ---")
#for marka in marka_coll.find():
#    print(marka)

# Új Autó
# auto_adatok = [
#    {
#        "_autorendszam": "DEF222",
#        "szin": "Kék",
#        "motor": "2.0 Benzin",
#        "karbantartasok": "3",
#        "muszakiallapot": "Kiváló"
#    }
# ]

#auto_coll.insert_many(auto_adatok)
# print("Autó sikeresen feltöltve.")

# tulajdonos_adatok = [
#     {
#         "_tulajid": "T100",
#         "emailcim": "ujtulaj@email.hu",
#         "telefonszam": "+36701234567",
#         "szuletesidatum": "1992-06-15",
#         "nev": {
#             "vezeteknev": "Kiss",
#             "keresztnev": "Béla"
#         },
#         "lakcim": {
#             "iranyitoszam": "4024",
#             "varos": "Debrecen",
#             "utcahazszam": "Petőfi utca 12."
#         }
#     }
# ]

# tulajdonos_coll.insert_many(tulajdonos_adatok)
# print("Tulajdonos sikeresen feltöltve.")

# Auto lekérdezése
# def_auto = auto_coll.find_one({"_autorendszam": "DEF222"})
# print(def_auto)

# Autók, amelyeknél a karbantartások száma <= 2
# for auto in auto_coll.find({"karbantartasok": {"$lte": "2"}}):
#     print(auto)

# Autók átlagos karbantartás száma
# pipeline_avg = [
#     {
#         "$group": {
#             "_id": None,
#             "atlagKarbantartas": {
#                 "$avg": {
#                     "$toInt": "$karbantartasok"
#                 }
#             }
#         }
#     }
# ]

# atlag_eredmeny = list(auto_coll.aggregate(pipeline_avg))
# atlag = atlag_eredmeny[0]["atlagKarbantartas"]
# print(f"Az autók átlagos karbantartása: {atlag:.2f}")

# Tulajdonosok és autóik (birtokol alapján)
# pipeline = [
#     {
#         "$lookup": {
#             "from": "birtokol",
#             "localField": "_tulajid",
#             "foreignField": "_a_b_t",
#             "as": "kapcsolat"
#         }
#     },
#     {
#         "$unwind": {
#             "path": "$kapcsolat",
#             "preserveNullAndEmptyArrays": True
#         }
#     },
#     {
#         "$lookup": {
#             "from": "auto",
#             "localField": "kapcsolat._a_b_a",
#             "foreignField": "_autorendszam",
#             "as": "auto_adatok"
#         }
#     }
# ]

# for doc in tulajdonos_coll.aggregate(pipeline):
#     auto = doc["auto_adatok"][0]["_autorendszam"] if doc["auto_adatok"] else "Nincs autó"
#     nev = doc["nev"]["vezeteknev"] + " " + doc["nev"]["keresztnev"]
#     print(f"{nev} -> {auto}")

# Karbantartás frissítése:
# auto_coll.update_one(
#     {"_autorendszam": "LMN456"},
#     {"$set": {"karbantartasok": "6"}}
# )
# print(f"Sikeres módosítás!")

#for auto in auto_coll.find():
#    print(auto)

# Konkrét autó törlése
# torles_auto = auto_coll.delete_one(
#     {"_autorendszam": "LMN456"}
# )
# print(f"Törölt dokumentumok száma: {torles_auto.deleted_count}")

# for auto in auto_coll.find():
#     print(auto)

# Kiváló állapotú autók törlése

torles_eredmeny = auto_coll.delete_many(
    {"muszakiallapot": "Kiváló"}
)

print(f"Törölt autók száma: {torles_eredmeny.deleted_count}")
for auto in auto_coll.find():
    print(auto)

