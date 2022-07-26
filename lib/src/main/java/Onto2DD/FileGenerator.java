package Onto2DD;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import br.pucrs.smart.ontology.OwlOntoLayer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.UUID;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileGenerator {

    public static void main(String [] args) {
        //String fileName = "src/main/resources/Miro-intents-names.owl";
        String fileName = "/Users/zeinabnamakizadehesfahani/Documents/Thesis/MiroOnto/MiroOnto.owl";
        String[] intentsNames = get_ontointent_classes(fileName);
        //String[] trainphNames = get_ontotrainph_classes(fileName);
        makefiles(intentsNames);
    }


    public static String[] get_ontointent_classes(String fn){
    	String line = null;
    	List<String> ontoclasses = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(fn);
            BufferedReader br = new BufferedReader(fr);

            while( (line = br.readLine() ) != null ) {
                System.out.println(line);

                if (line.contains("<owl:Class") & line.indexOf('"'+"/>")==-1) //if it's a class and is not a root class
                {
                    int indexstart = line.indexOf("#");
                    int indexend = line.indexOf('"', indexstart);
                    ontoclasses.add(line.substring(indexstart+1,indexend));
                    //ontoclasses.add(line.substring(index+1,line.length()-3));
                }
            }
            System.out.println(ontoclasses);
            br.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return ontoclasses.toArray(new String [] {});
    }



    public static void makefiles(String[] intents) {
    	makeasl(intents);
    	makejsons(intents);
    }


    public static String makeasl(String[] plannames) {
    	String plan1= "+!responder(RequestedBy, ResponseId, IntentName, Params, Contexts)\n\t: (IntentName == \"";
    	String plan2="\")\n <-";
    	String outputmessageasl;
    	try {
            FileWriter fileasl = new FileWriter("communication_sample_agent.asl");
	        for (int i = 0 ; i<plannames.length ;i++)
	        {
                fileasl.write(plan1+plannames[i]+plan2+"\n");
	        }
	        fileasl.close();
	        System.out.println("Successfully wrote to the asl file communication_sample_agent.");
	        outputmessageasl="Successfully wrote to the asl file communication_sample_agent.";
    	}catch (IOException e) {
            System.out.println("An error occurred.");
            outputmessageasl="An error occurred.";
            e.printStackTrace();
        }
    	return outputmessageasl;
    }


    public static String makejsons(String[] intents) {
        //JSONObject jsonObject = new JSONObject(); //Creating a JSONObject object
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	String outputmessagejson = "";
        File theDir = new File("intents");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        for (int i = 0 ; i<intents.length ;i++)
        {
        	OntoClasses intentjson = new OntoClasses();
        	OntoClassesResp intentjsonresp = new OntoClassesResp();
        	OntoClassesRespMsg intentjsonrespmsg = new OntoClassesRespMsg();
        	OntoClassesRespPar intentjsonresppar = new OntoClassesRespPar();
        	

        	//setting intent.Responses.Parameters:
        	intentjsonresppar.setId(UUID.randomUUID().toString());
        	intentjson.setName(intents[i]);  //for now, but in real, the parameters should be extracted
        	intentjsonresppar.setRequired(false);
        	intentjsonresppar.setDataType("");
        	intentjsonresppar.setValue("");
        	intentjsonresppar.setDefaultValue("");
        	intentjsonresppar.setIsList(false);
        	intentjsonresppar.setPrompts(null);
        	intentjsonresppar.setPromptMessages(null);
        	intentjsonresppar.setNoMatchPromptMessages(null);
        	intentjsonresppar.setNoInputPromptMessages(null);
        	intentjsonresppar.setOutputDialogContexts(null);
        	//setting intent.Responses.Messages:
        	intentjsonrespmsg.setTitle("");
        	intentjsonrespmsg.setType("0");
        	intentjsonrespmsg.setTextToSpeech("");
        	intentjsonrespmsg.setLang("en");
        	intentjsonrespmsg.setSpeech(Arrays.asList("response example 1", "response example 2"));
        	intentjsonrespmsg.setCondition("");
        	//setting intent.Responses:
        	intentjsonresp.setResetContexts(false);
            intentjsonresp.setAction("");
            intentjsonresp.setAffectedcontexts(null); //affectedContexts is "name": "venues-eatingout", "lifespan": 3,   "parameters": {}
            intentjsonresp.setParameters(intentjsonresppar);
            intentjsonresp.setMessages(intentjsonrespmsg);
            intentjsonresp.setDefaultResponsePlatforms("");
            intentjsonresp.setSpeech(null);
        	//setting the intent:
        	intentjson.setId(UUID.randomUUID().toString());
        	intentjson.setName(intents[i]);
        	intentjson.setAuto(true);
        	intentjson.setContexts(Arrays.asList("context example 1", "context example 2"));
        	intentjson.setResponses(intentjsonresp);
        	intentjson.setPriority(50000);
        	intentjson.setWebhookUsed(false);
        	intentjson.setWebhookForSlotFilling(false);
        	intentjson.setFallbackIntent(false);
        	intentjson.setEvents(null);
        	intentjson.setConditionalResponses(null);
        	intentjson.setCondition("");
        	intentjson.setConditionalFollowupEvents(null);
        	
        	
        	try {
 	            FileWriter filejson = new FileWriter("intents/"+intents[i]+".json");
 	            filejson.write(gson.toJson(intentjson));
 	            filejson.close();
 	            System.out.println(String.format("%s%s%s", "Successfully wrote to the json file ", intents[i], "."));
 	            outputmessagejson=outputmessagejson+"Successfully wrote to the json file"+ intents[i]+ ".\n";
 	        } catch (IOException e) {
 	            System.out.println("An error occurred.");
 	            outputmessagejson="An error occurred.";
 	            e.printStackTrace();
 	        }
        }
        return outputmessagejson;
    }

}
