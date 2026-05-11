using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace MongoTest.Models
{
    [BsonIgnoreExtraElements]
    public class Szervizbevitel
    {
        [BsonId]
        public ObjectId Id { get; set; }

        public required string Autoszerviz { get; set; }
        public required string _szervizbevitelid { get; set; }
    }
}