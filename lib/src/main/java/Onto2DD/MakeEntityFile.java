package Onto2DD;

import java.util.List;

public class MakeEntityFile {

   private String id;
   private String name;
   private boolean isOverridable;
   private boolean isEnum;
   private boolean isRegexp;
   private boolean automatedExpansion;
   private boolean allowFuzzyExtraction;
   
   public void MakeEntityFile(String id, String name, boolean isOverridable, boolean isEnum, boolean isRegexp, boolean automatedExpansion, boolean allowFuzzyExtraction){
      this.id = id;
      this.name = name;
      this.isOverridable = isOverridable;
      this.isEnum = isEnum;
      this.isRegexp = isRegexp;
      this.automatedExpansion = automatedExpansion;
      this.allowFuzzyExtraction = allowFuzzyExtraction;
   }

   //Getters and setters

   public void setId(String id) {
	   this.id = id;
   }
   public void setName(String name) {
	   this.name = name;
   }
   public void setIsOverridable(boolean isOverridable) {
	   this.isOverridable = isOverridable;
   }
   public void setIsEnum(boolean isEnum) {
	   this.isEnum = isEnum;
   }
   public void setIsRegexp(boolean isRegexp) {
	   this.isRegexp = isRegexp;
   }
   public void setAutomatedExpansion(boolean automatedExpansion) {
	   this.automatedExpansion = automatedExpansion;
   }   
   public void setAllowFuzzyExtraction(boolean allowFuzzyExtraction) {
	   this.allowFuzzyExtraction = allowFuzzyExtraction;
   }   

}
