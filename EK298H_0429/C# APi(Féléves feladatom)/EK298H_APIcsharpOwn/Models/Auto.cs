using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace MongoTest.Models
{
    [BsonIgnoreExtraElements]
    public class Auto
    {
        [BsonId]
        public ObjectId Id { get; set; }

        public required string szin { get; set; }
        public required string motor { get; set; }
        public required string karbantartasok { get; set; }
        public required string muszakiallapot { get; set; }
        public required string _autorendszam { get; set; }
    }
}