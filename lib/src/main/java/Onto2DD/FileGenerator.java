package Onto2DD;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cartago.OpFeedbackParam;
import cartago.manual.syntax.Literal;

//import br.pucrs.smart.ontology.OwlOntoLayer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.UUID;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileGenerator {
	
	public static String[] OntoClasses;
	public static List<String> OntoEntities = new ArrayList<String>();
	public static List<String> OntoEntitiesVals = new ArrayList<String>();
	//public static List<String> OntoTrainings = new ArrayList<String>();


    public static String readOnto (String fn) {
    	String msg = OwlApiExtract.confirmReadOnto(fn);
		return msg;
    }
    

    public static String getOntoClasses(String fn){ //OwlApiExtract.fetchClasses() returns a List<Object> 
    	String msg = "";
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
                }
            }
            OntoClasses = ontoclasses.toArray(new String [] {});
            System.out.println("OntoClasses: ");
            System.out.println(Arrays.asList(OntoClasses));
            msg = "Successfully read the Classes!";
            br.close();
        }
        catch(IOException e) {
            //e.printStackTrace();
            msg = "Could not read the Classes: " +e.getMessage();
        }
        return msg;
    }



    
    public static String feedDJ(String folderName,String selectedDest) {
        System.out.println("folderName: "+folderName);

    	String msgasl = "";
    	if (makeasl (folderName,selectedDest) == true)
    		msgasl = "Successfully wrote to the asl file "+folderName+"_agent.";
    	return msgasl;
    }
    
    
    public static String[] feedDF(String folderName,String selectedDest) {
    	
    	String[] OutputResult= new String[4];
        File DFfolder = new File(selectedDest+"/"+folderName);
        System.out.println("folderName: "+folderName);
        if (DFfolder.exists()){
            DFfolder.delete();
        }
        DFfolder.mkdir();
    	// Making entities folder
    	OutputResult[0] = makeentitiesjsons(folderName, selectedDest);
    	// Making intents folder
    	OutputResult[1] = makeintentsjsons(folderName, selectedDest);
        // Making package.json
    	Gson gsonp = new GsonBuilder().setPrettyPrinting().create();
        MakePackageFile packagejson = new MakePackageFile();
        try {
            FileWriter filejsonp = new FileWriter(selectedDest+"/"+folderName+"/package"+".json");
            filejsonp.write(gsonp.toJson(packagejson));
            filejsonp.close();
            OutputResult[2]="Successfully wrote to the package.json file.\n";
        }catch (IOException e) {
        	OutputResult[2]="An error occurred while writing to package.json.";
            e.printStackTrace();
        }
        // Making agent.json
    	Gson gsona = new GsonBuilder().setPrettyPrinting().create();
        MakeAgentFile agentjson = new MakeAgentFile();
        agentjson.main();
        agentjson.setName(folderName);
        try {
            FileWriter filejsona = new FileWriter(selectedDest+"/"+folderName+"/agent"+".json");
            filejsona.write(gsona.toJson(agentjson));
            filejsona.close();
            OutputResult[3]="Successfully wrote to the agent.json file.\n";
        }catch (IOException e) {
        	OutputResult[3]="An error occurred while writing to agent.json.";
            e.printStackTrace();
        }
        zipdir.main(selectedDest,folderName);
        DFfolder.delete();
    	return OutputResult;
        
    }
    
    

    public static boolean makeasl(String folderName, String selectedDest) {
    	String plan1= "+!responder(RequestedBy, ResponseId, IntentName, Params, Contexts)\n\t: (IntentName == \"";
    	String plan2="\")\n <-";
    	try {
            FileWriter fileasl = new FileWriter(selectedDest+"/"+folderName+"_agent.asl");
	        for (int i = 0 ; i<OntoClasses.length ;i++)
	        {
	        	if (OwlApiExtract.isIntent(OntoClasses[i]))
	        	{
	        		fileasl.write(plan1+OntoClasses[i]+plan2+"\n.\n");
	        	}
	        }
	        fileasl.close();
	        return true;
    	}catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    
    
    
	public static String makeentitiesjsons(String folderName,String selectedDest) {
		
    	String outputmessagejson = "";
        // Making Entities folder contents
        File ents = new File(selectedDest+"/"+folderName+"/entities");
        if (ents.exists()){
        	ents.delete();
        }
            ents.mkdirs();
            
            for (int i = 0 ; i<OntoClasses.length ;i++)
            {
            	if (OwlApiExtract.isEntity(OntoClasses[i])) {
            		outputmessagejson = outputmessagejson + EntityFolderEntities.main(OntoClasses[i], selectedDest, folderName);
            		List<String> allValues = OwlApiExtract.getEntityValues(OntoClasses[i]); //irrespective of the Property connection
            		OntoEntities.add(OntoClasses[i]);
            		if (allValues.size()> 0) {
                		OntoEntitiesVals.addAll(allValues);
                		//OntoEntities
                		System.out.println("values of entity " + OntoClasses[i]+ " are: " + allValues);
            			outputmessagejson = outputmessagejson + EntityFolderEntries.main(OntoClasses[i], allValues, selectedDest, folderName);
                		}
            	}
            	
            }
            return outputmessagejson;
	}
            
       
    
    public static String makeintentsjsons(String folderName, String selectedDest) {
    	
		String outputmessagejson = "";
        // Making Intents folder contents
        File ints = new File(selectedDest+"/"+folderName+"/intents");
        if (ints.exists()){
            ints.delete();
        }
        ints.mkdir();
        
        for (int i = 0 ; i<OntoClasses.length ;i++)
        {
        	if (OwlApiExtract.isIntent(OntoClasses[i]))
        	{	
        		List<String> allRelTraining = OwlApiExtract.getRangeLst(OntoClasses[i],"has-training");
        		Set<String> EntNames = new HashSet<String>();
        		if (allRelTraining.size()!= 0)
        		{
        			//make usersays files:
        			outputmessagejson = outputmessagejson + IntentFolderTrainings.main(OntoClasses[i], OntoEntitiesVals, allRelTraining, selectedDest, folderName);
        			//get the relevant entity names:
        			getEntNames(allRelTraining);
        		}
        		
        		//now make the intents with the use of entities
        		List<String> allRelTrEntity = new ArrayList<>(EntNames);
        		List<String> responses = null; //gather the responses for this intent:
        		responses = OwlApiExtract.getRangeLst(OntoClasses[i],"has-answer");
				outputmessagejson = outputmessagejson + IntentFolderIntents.main(OntoClasses[i], allRelTrEntity, responses , selectedDest, folderName);
        	}
        }
        return outputmessagejson;
    }
    
    
    
    public static Set<String> getEntNames(List<String> allRelTraining)
    {
		Set<String> EntVal = new HashSet<String>();// Unique list of all relevant entity values to training phrases of this intent
		Set<String> EntNames = new HashSet<String>();
		Set<String> oneEntVal = new HashSet<String>();
    		for (int j = 0; j< allRelTraining.size(); j++)//extract the entities related to the training phrases of this intent:
    		{
    			oneEntVal = OwlApiExtract.getRangeTrainingLst(allRelTraining.get(j),"has-entity");
    			EntVal.addAll(oneEntVal);
        		for (String ent : OntoEntities)
        			for (String entval :EntVal)
                		if (OwlApiExtract.isIndividualOf(entval,ent))
        					EntNames.add(ent);
        	}
		
		return EntNames;
    }
    
    
    
    
}
