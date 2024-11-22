package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Utils {
    public static int generateRandomNumber(int min, int max){

        double randomID = Math.random()*(max-min)+min;
        return (int) randomID;

    }

//public static void saveUSerInfo(String filePath, JSONObject jsonObject) throws IOException, ParseException {
//    JSONParser jsonParser= new JSONParser();
//    JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader("filePath"));
//    jsonArray.add(jsonObject);
//    FileWriter fileWriter= new FileWriter(filePath);
//    fileWriter.write(jsonArray.toJSONString());
//    fileWriter.flush();
//    fileWriter.close();



    public static void saveUSerInfo(String filePath, JSONObject jsonObject) throws IOException, ParseException {
        // Check if the file exists; if not, create it
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();  // Create directories if they don't exist
            file.createNewFile();  // Create the file if it doesn't exist
        }

        // Parse existing data in the file (if any)
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray;
        try (FileReader fileReader = new FileReader(file)) {
            Object obj = jsonParser.parse(fileReader);
            jsonArray = obj instanceof JSONArray ? (JSONArray) obj : new JSONArray();  // Ensure we have a JSONArray
        } catch (IOException | ParseException e) {
            jsonArray = new JSONArray();  // If parsing fails (empty file), initialize a new JSONArray
        }

        // Add the new user data to the JSON array
        jsonArray.add(jsonObject);

        // Write updated data back to the file
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
        }
    }
}

