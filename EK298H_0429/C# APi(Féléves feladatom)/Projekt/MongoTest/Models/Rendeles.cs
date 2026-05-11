
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

public class Rendeles
{
    [BsonId]
    public ObjectId Id { get; set; }

    public required string etel { get; set; }

    public int osszeg { get; set; }

    public required string datum { get; set; }

    public required string _rkod { get; set; }

    public required string _vkod { get; set; }

    public required string _ekod { get; set; }
}