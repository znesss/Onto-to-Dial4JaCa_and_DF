package Onto2DD;

import java.util.List;

public class OntoClassesResp {

   private boolean resetContexts;
   private String action;
   private List<String> affectedcontexts;
   private OntoClassesRespPar parameters;
   private OntoClassesRespMsg messages;
   private String defaultResponsePlatforms;
   private List<String> speech;

   public void OntoClassesResp(boolean resetContexts, String action, List<String> affectedcontexts, OntoClassesRespPar parameters, OntoClassesRespMsg messages, List<String> speech, String defaultResponsePlatforms){
      this.resetContexts = resetContexts;
      this.action = action;
      this.affectedcontexts = affectedcontexts;
      this.parameters = parameters;
      this.messages = messages;
      this.defaultResponsePlatforms = defaultResponsePlatforms;
      this.speech = speech;
   }

   //Getters and setters

   public void setResetContexts(boolean resetContexts) {
	   this.resetContexts = resetContexts;
   }
   public void setAction(String action) {
	   this.action = action;
   }
   public void setAffectedcontexts(List<String> affectedcontexts) {
	   this.affectedcontexts = affectedcontexts;
   }
   public void setParameters(OntoClassesRespPar parameters) {
	   this.parameters = parameters;
   }
   public void setMessages(OntoClassesRespMsg messages) {
	   this.messages = messages;
   }
   public void setDefaultResponsePlatforms(String defaultResponsePlatforms) {
	   this.defaultResponsePlatforms = defaultResponsePlatforms;
   }
   public void setSpeech(List<String> speech) {
	   this.speech = speech;
   }
   
}
