package Onto2DD;

import java.util.ArrayList;
import java.util.List;

public class MakeAgentFile {
	public String description;
	public String language;
	public String shortDescription;
	public String examples;
	public String linkToDocs;
	public String displayName;
	public boolean disableInteractionLogs;
	public boolean disableStackdriverLogs;
	//public OntoAgentGgl googleAssistant;////////
    public String defaultTimezone;
    //public OntoAgentWbk webhook;////////
	public boolean ispublic;
	public float mlMinConfidence;
	public List<String>supportedLanguages;
	public boolean enableOnePlatformApi;
	public String onePlatformApiVersion;
	public String secondaryKey;
	public boolean analyzeQueryTextSentiment;
	public List<String> enabledKnowledgeBaseNames;
	public float knowledgeServiceConfidenceAdjustment;
	public boolean dialogBuilderMode;
	public String baseActionPackagesUrl;
	public boolean enableSpellCorrection;
	
	public void main(){
		this.description =  "";
		this.language = "en";
		this.shortDescription = "";
		this.examples = "";
		this.linkToDocs = "";
		this.disableInteractionLogs =  false;
		this.disableStackdriverLogs = true;
		MakeAgentGgl googleAssistant = new MakeAgentGgl();
		googleAssistant.main();
		this.defaultTimezone = "Europe/Rome";
		//this.webhook.main();
		this.ispublic = true;
		this.mlMinConfidence = 0.3f;
		this.supportedLanguages = new ArrayList<>();
		this.enableOnePlatformApi = true;
		this.onePlatformApiVersion = "v2";
		this.secondaryKey = "";
		this.analyzeQueryTextSentiment = true;
		this.enabledKnowledgeBaseNames = null;
		this.knowledgeServiceConfidenceAdjustment = -0.4f;
		this.dialogBuilderMode = false;
		this.baseActionPackagesUrl = "";
		this.enableSpellCorrection = false;
	}
	
	//Getters and setters
	public void setName(String displayName) {
		this.displayName = displayName;
	}
}
	

