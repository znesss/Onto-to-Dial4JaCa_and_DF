package Onto2DD;

import java.util.List;

public class OntoClassesRespMsg {
   private String type;
   private String title;
   private String textToSpeech;
   private String lang;
   private List<String> speech;
   private String condition;

   public void OntoClassesRespMsg(boolean resetContexts, String action, List<String> affectedcontexts, List<String> parameters, List<String> messages, String type, String title, String textToSpeech, String lang, List<String> speech, String condition){
      this.type = type;
      this.title = title;
      this.textToSpeech = textToSpeech;
      this.lang = lang;
      this.speech = speech;
      this.condition = condition;
   }

   //Getters and setters

   public void setType(String type) {
	   this.type = type;
   }
   public void setTitle(String title) {
	   this.title = title;
   }
   public void setTextToSpeech(String textToSpeech) {
	   this.textToSpeech = textToSpeech;
   }
   public void setLang(String lang) {
	   this.lang = lang;
   }
   public void setSpeech(List<String> speech) {
	   this.speech = speech;
   }
   public void setCondition(String condition) {
	   this.condition = condition;
   }   

}
