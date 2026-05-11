using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace MongoTest.Models
{
    [BsonIgnoreExtraElements]
    public class Marka
    {
        [BsonId]
        public ObjectId Id { get; set; }

        public required string nev { get; set; }
        public required string szekhely { get; set; }
        public required List<string> modellek { get; set; }
        public required string statisztika { get; set; }
        public required string _markaid { get; set; }
    }
}