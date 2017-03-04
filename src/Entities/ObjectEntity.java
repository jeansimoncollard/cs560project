package Entities;

public class ObjectEntity {
	public int ObjectX;
	public int ObjectY;
	public Enums.ObjectType ObjectType;
	public String PathToImage;

	public ObjectEntity() {

	}

	public ObjectEntity(int objectX, int objectY, Enums.ObjectType objectType, String pathToImage) {
		ObjectX = objectX;
		ObjectY = objectY;
		ObjectType = objectType;
		PathToImage = pathToImage;
	}
}
