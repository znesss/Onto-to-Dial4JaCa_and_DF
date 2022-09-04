package Onto2DD;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class IntentFolderTrainings {
	
	public static String main(String OntoClass, List<String> OntoEntities, List<String> alltraining, String selectedDest, String folderName) {
		
		String outputmessagejson = "";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    MakeUsersaysFile[] arrfile = new MakeUsersaysFile[alltraining.size()];
	    
		for(int i = 0 ; i< alltraining.size() ;i++)//for each single statement in the training phrases 
		{
		    //setting the usersays:
			arrfile[i] = new MakeUsersaysFile(null,null,false,0,"en",0);
			arrfile[i].setId(UUID.randomUUID().toString()); //updating id
			
			String tph = alltraining.get(i);
			String[] tphwords = tph.split(" ");  
			String nonentities = "";

			//setting usersays.Data:
			List<MakeUsersaysData> lstdata = new ArrayList<MakeUsersaysData>();
			for(int j = 0 ; j< tphwords.length ;j++) //for each of its words check if its an entity
	        {
				if (!OntoEntities.contains(tphwords[j]))
				{
					nonentities = nonentities + " " + tphwords[j];
				}
				else { //entity:
					if (nonentities != "")
					{
						lstdata.add (new MakeUsersaysData(nonentities+" ", false)); //make object out of previous words
						nonentities = "";
					}
						lstdata.add (new MakeUsersaysData(tphwords[j]+" ", true)); //make object out of this entity
				}
	        }

			if (nonentities != "")
			{
				lstdata.add (new MakeUsersaysData(nonentities, false)); //make object out of previous words
				nonentities = "";
			}
			
			arrfile[i].setData(lstdata); //updating  data
		}
		
		try {
	         FileWriter filejson = new FileWriter(selectedDest+"/"+folderName+"/intents/"+OntoClass+"_usersays_en.json");
	         filejson.write(gson.toJson(arrfile)); //usersays should be in an array
	         filejson.close();
	         outputmessagejson= "Successfully wrote to the json file "+ OntoClass+ "_usersays_en.\n";
	     } catch (IOException e) {
	         outputmessagejson= "An error occurred while writing to intent files.";
	         e.printStackTrace();
	     }
		return outputmessagejson;
	}
}
