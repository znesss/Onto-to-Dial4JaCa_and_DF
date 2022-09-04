package Onto2DD;

import java.util.Arrays;
import java.util.List;

public class MakeIntentResp {

   private boolean resetContexts;
   private String action;
   private List<String> affectedContexts;
   private List<MakeIntentRespPar> parameters;
   private List<MakeIntentRespMsg> messages;
   private List<String> speech;

   public void MakeIntentResp(boolean resetContexts, String action, List<String> affectedContexts, List<MakeIntentRespPar> param, MakeIntentRespMsg msg, List<String> speech){
      this.resetContexts = resetContexts;
      this.action = action;
      this.affectedContexts = affectedContexts;
      this.parameters = param;
      this.messages = Arrays.asList(msg);
      this.speech = speech;
   }

   //Getters and setters

   public void setResetContexts(boolean resetContexts) {
	   this.resetContexts = resetContexts;
   }
   public void setAction(String action) {
	   this.action = action;
   }
   public void setAffectedContexts(List<String> affectedContexts) {
	   this.affectedContexts = affectedContexts;
   }
   public void setParameters(List<MakeIntentRespPar> param) {
	   this.parameters = param;
   }
   public void setMessages(MakeIntentRespMsg msg) {
	   this.messages = Arrays.asList(msg);
   }
   public void setSpeech(List<String> speech) {
	   this.speech = speech;
   }
   
}
