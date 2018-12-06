package demo.stage1initial;

import java.util.HashMap;
import java.util.Map;

public class RoverRepository {

    private static final Map<String, Rover> storage = new HashMap<String, Rover>();

    public void save(Rover rover) {
        storage.put(rover.getId(), rover);
    }

    public Rover findById(String id) {
        return storage.get(id);
    }
}
