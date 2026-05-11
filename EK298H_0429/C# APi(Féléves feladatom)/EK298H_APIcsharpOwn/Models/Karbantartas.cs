using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace MongoTest.Models
{
    [BsonIgnoreExtraElements]
    public class Karbantartas
    {
        [BsonId]
        public ObjectId Id { get; set; }

        public required List<string> munkalatokleirasa { get; set; }

        public required string koltseg { get; set; }
        public required string munkavallaloneve { get; set; }
        public required string datum { get; set; }

        public required string _a_k_sz { get; set; }
        public required string _a_k_a { get; set; }  
    }
}