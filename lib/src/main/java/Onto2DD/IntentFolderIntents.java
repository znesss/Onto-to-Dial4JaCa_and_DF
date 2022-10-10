package Onto2DD;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class IntentFolderIntents {

	public static String main(String OntoClass, List<String> allreltrentity, List<String> responses, String selectedDest, String folderName) {
		String outputmessagejson = "";
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	    MakeIntentFile intentjson = new MakeIntentFile();
		MakeIntentResp intentjsonresp = new MakeIntentResp();
		MakeIntentRespMsg intentjsonrespmsg = new MakeIntentRespMsg();
		
		
		boolean webhook = true;   
	    if (responses != null && !responses.isEmpty())
	    {
	    	webhook = false;
			intentjsonrespmsg.setSpeech(responses); //responses
	    }
	   
		//setting intent.Responses.Messages:
		intentjsonrespmsg.setTitle("");
		intentjsonrespmsg.setType("0");
		intentjsonrespmsg.setTextToSpeech("");
		intentjsonrespmsg.setLang("en");
		intentjsonrespmsg.setCondition("");
		
			//setting res.parameters:
			List<MakeIntentRespPar> arrresppar = new ArrayList<MakeIntentRespPar>();
			if (allreltrentity!=null)
			{
				for(int j = 0 ; j< allreltrentity.size() ;j++) //for each of its words check if its an entity
		        {
					String thisid  = UUID.randomUUID().toString();
					arrresppar.add (new MakeIntentRespPar(thisid,allreltrentity.get(j),false,
							"@"+allreltrentity.get(j),"&"+allreltrentity.get(j),
							"none",false,Arrays.asList(),Arrays.asList(),Arrays.asList(),Arrays.asList(),Arrays.asList())); //make object out of this entity
					
		        }
			    intentjsonresp.setParameters(arrresppar); //updating resp.parameter
			}	
		
		//setting intent.Responses:
		intentjsonresp.setResetContexts(false);
	    intentjsonresp.setAction("");
	    intentjsonresp.setAffectedContexts(Arrays.asList());
//	    intentjsonresp.setParameters();
	    intentjsonresp.setMessages(intentjsonrespmsg);
	    intentjsonresp.setSpeech(Arrays.asList());	    
	    
		//setting the intent:
		intentjson.setId(UUID.randomUUID().toString());
		intentjson.setName(OntoClass);
		intentjson.setAuto(true);
		intentjson.setContexts(Arrays.asList());
		intentjson.setResponses(intentjsonresp);
		intentjson.setPriority(50000);
		intentjson.setWebhookUsed(webhook);
		intentjson.setWebhookForSlotFilling(false);
		intentjson.setFallbackIntent(false);
		intentjson.setEvents(Arrays.asList());
		intentjson.setConditionalResponses(Arrays.asList());
		intentjson.setCondition("");
		intentjson.setConditionalFollowupEvents(Arrays.asList());	
		
		try {
	         FileWriter filejson = new FileWriter(selectedDest+"/"+folderName+"/intents/"+OntoClass+".json");
	         filejson.write(gson.toJson(intentjson));
	         filejson.close();
	         outputmessagejson= "Successfully wrote to the json file "+ OntoClass+ ".\n";
	     } catch (IOException e) {
	         outputmessagejson= "An error occurred while writing to intent files.";
	         e.printStackTrace();
	     }
		return outputmessagejson;
	}
}
