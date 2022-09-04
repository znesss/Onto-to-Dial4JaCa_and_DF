package Onto2DD;

import java.util.Arrays;
import java.util.List; 

public class MakeUsersaysData {

   private String text;
   private boolean userDefined;


   public MakeUsersaysData(String text, boolean userDefined){//constructor
	  this.text = text;
      this.userDefined = userDefined;
   }

   //Getters and setters

	   public void setText(String text) {
		   this.text = text;
	   }
	   public void setUserDefined(boolean userDefined) {
		   this.userDefined = userDefined;
	   }
}
