using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

public class Szakacs
{
    [BsonId]
    public ObjectId Id { get; set; }

    public required string nev { get; set; }

    public required string reszleg { get; set; }

    public required List<string> vegzettseg { get; set; }

    public required string _szkod { get; set; }

    public required string _e_sz { get; set; }
}