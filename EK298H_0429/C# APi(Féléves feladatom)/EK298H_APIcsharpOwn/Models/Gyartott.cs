using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace MongoTest.Models
{
    [BsonIgnoreExtraElements]
    public class Gyartott
    {
        [BsonId]
        public ObjectId Id { get; set; }

        public required string tipus { get; set; }
        public required string gyartasiev { get; set; }
        public required string _a_gy_m { get; set; }
        public required string _a_gy_a { get; set; }
    }
}