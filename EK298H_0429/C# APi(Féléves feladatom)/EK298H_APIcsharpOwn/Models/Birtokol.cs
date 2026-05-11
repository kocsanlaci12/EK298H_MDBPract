using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace MongoTest.Models
{
    [BsonIgnoreExtraElements]
    public class Birtokol
    {
        [BsonId]
        public ObjectId Id { get; set; }

        public required string vasarlasdatuma { get; set; }
        public required string vasarlasiar { get; set; }
        public required string eladasdatuma { get; set; }

        public required string _a_b_t { get; set; }
        public required string _a_b_a { get; set; }
    }
}