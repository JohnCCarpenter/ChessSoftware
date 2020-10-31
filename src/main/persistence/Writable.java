package persistence;

import org.json.JSONObject;

//Taken and adjusted for this projects needs from JsonSerializationDemo.java file on class github
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
