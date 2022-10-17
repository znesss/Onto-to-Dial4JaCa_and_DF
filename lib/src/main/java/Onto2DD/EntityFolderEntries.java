//https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Objects/JSON#arrays_as_json
package Onto2DD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class EntityFolderEntries {

	public static String main(String OntoClass, List<String> allvalues, String selectedDest, String folderName) {
		 
		String outputmessagejson = "";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		MakeEntityEntr[] arr = new MakeEntityEntr[allvalues.size()];
		for(int i = 0 ; i< allvalues.size() ;i++)
		        {
					//setting value synonyms of Entity_entries:
					arr[i] = new MakeEntityEntr(allvalues.get(i), new String[0]);
		        }
		
		try {
		FileWriter filejson = new FileWriter(selectedDest+"/"+folderName+"/entities/"+OntoClass+"_entries_en.json");
	    filejson.write(gson.toJson(arr)); //entryjson should be in an array

        filejson.close();   
	    outputmessagejson= "Successfully wrote to the json file "+ OntoClass+ "_entries_en.\n";

		} catch (IOException e) {
	         outputmessagejson= "An error occurred while writing to intent files.";

	         e.printStackTrace();
	    }
		return outputmessagejson;
	
	}
}
