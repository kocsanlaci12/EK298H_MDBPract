using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace MongoTest.Models
{
    [BsonIgnoreExtraElements]
    public class Tulajdonos
    {
        [BsonId]
        public ObjectId Id { get; set; }

        public required string emailcim { get; set; }
        public required string telefonszam { get; set; }
        public required string szuletesidatum { get; set; }

        public required Nev nev { get; set; }
        public required Lakcim lakcim { get; set; }

        public required string _tulajid { get; set; }
    }
}