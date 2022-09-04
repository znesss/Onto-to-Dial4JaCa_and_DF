package Onto2DD;


import java.util.Arrays;
import java.util.List;

public class MakeEntityEntr {

   private String value;
   private String[] synonyms;

   public MakeEntityEntr(String value, String[] synonyms){ //constructor
	  this.value = value;
      this.synonyms= synonyms;
   }

   //Getters and setters

   public void setValue(String value) {
	   this.value = value;
   }
   public void setSynonyms(String[] synonyms) {
	   this.synonyms = synonyms;
   }
   
}