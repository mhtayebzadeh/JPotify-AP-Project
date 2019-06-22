package JPotifyLogic.Library;

import JPotifyLogic.Entity.Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Library implements Serializable {
    private ArrayList<Entity> entities;

    public Library() { this.entities = new ArrayList<>(); }

    public ArrayList<Entity> getEntities() { return this.entities; }
    public void addEntity(Entity entity) { this.entities.add(entity); }
}
