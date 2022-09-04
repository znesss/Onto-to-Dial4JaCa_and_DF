package Onto2DD;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EntityFolderEntities {


	public static String main(String OntoClass, String selectedDest, String folderName) {
		String outputmessagejson = "";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    MakeEntityFile entityjson = new MakeEntityFile();

		//setting the entity:
		entityjson.setId(UUID.randomUUID().toString());
		entityjson.setName(OntoClass);
		entityjson.setIsOverridable(true);
		entityjson.setIsEnum(false);
		entityjson.setIsRegexp(false);
		entityjson.setAutomatedExpansion(false);
		entityjson.setAllowFuzzyExtraction(false);
		
		try {
	         FileWriter filejson = new FileWriter(selectedDest+"/"+folderName+"/entities/"+OntoClass+".json");
	         filejson.write(gson.toJson(entityjson));
	         filejson.close();
	         outputmessagejson= "Successfully wrote to the json file "+ OntoClass+ ".\n";
	     } catch (IOException e) {
	         outputmessagejson= "An error occurred while writing to intent files.";
	         e.printStackTrace();
	     }
		return outputmessagejson;
	}
	

}
