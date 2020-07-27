package task.java;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonHandler {

	@SuppressWarnings("unchecked")
	public static ArrayList<String> read() throws IOException {
		JSONParser parser = new JSONParser();
		ArrayList<String> list = new ArrayList<>();
		try {
			Object obj = parser.parse(new FileReader("..\\tasks.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray taskList = (JSONArray) jsonObject.get("tasks");
			Stream<String> ss = taskList.stream().map(json -> json.toString());
			list = (ArrayList<String>) ss.collect(Collectors.toList());
		} catch (Exception ex) {
			File file = new File("..\\tasks.json");
			file.createNewFile();
		}
		return list;

	}

	@SuppressWarnings("unchecked")
	public static void write(List<String> taskList) throws IOException {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		taskList.forEach((n) -> jsonArray.add(n));
		jsonObject.put("tasks", jsonArray);
		FileWriter fileWriter = new FileWriter("..\\tasks.json");
		fileWriter.write(jsonObject.toString());
		fileWriter.close();
	}

}
