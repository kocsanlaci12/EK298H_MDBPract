using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace MongoTest.Models
{
    public class Vendeg
    {
        [BsonId]
        public ObjectId Id { get; set; }

        public required string nev { get; set; }

        public int eletkor { get; set; }

        public required Cim cim { get; set; }

        public required string _vkod { get; set; }
    }
}