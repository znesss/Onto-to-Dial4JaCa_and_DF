package Onto2DD;

import java.util.Arrays;
import java.util.List;

public class MakeUsersaysFile {

   private String id;
   private List<MakeUsersaysData> data;
   private boolean isTemplate;
   private int count;
   private String lang;
   private int updated;

   public MakeUsersaysFile(String id, List<MakeUsersaysData> data, boolean isTemplate, int count, String lang, int updated){
	   
	  this.id = id;
      this.data = data;
      this.isTemplate = isTemplate;
      this.count = count;
      this.lang = lang;
      this.updated = updated;
   }

   //Getters and setters

   public void setId(String id) {
	   this.id = id;
   }
   public void setData(List<MakeUsersaysData> data) {
	   this.data = data;
   }
   public void setIsTemplate(boolean isTemplate) {
	   this.isTemplate = isTemplate;
   }
   public void setCount(int count) {
	   this.count = count;
   }
   public void setLang(String lang) {
	   this.lang = lang;
   }
   public void setUpdated(int updated) {
	   this.updated = updated;
   }
}
