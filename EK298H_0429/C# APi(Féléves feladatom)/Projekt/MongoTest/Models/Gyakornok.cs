using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace MongoTest.Models{
    public class Gyakornok
    {
        [BsonId]
        public ObjectId Id { get; set; }

        public required string nev { get; set; }

        public required Gyakorlat gyakorlat { get; set; }

        public required List<string> muszak { get; set; }   

        public required string _gykod { get; set; }

        public required string _e_gy { get; set; }
    }
}