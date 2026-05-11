using MongoDB.Bson.Serialization.Attributes;

namespace MongoTest.Models
{
    public class Gyakorlat
    {
        public required string kezdete { get; set; }   // JSON-ben "kezdete"
        public required string idotartama { get; set; }
    }
}