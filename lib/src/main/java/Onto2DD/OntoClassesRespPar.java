package Onto2DD;

import java.util.List;

public class OntoClassesRespPar {
	private String id;
	private String name;
	private boolean required;
	private String dataType;
	private String value;
	private String defaultValue;
	private boolean isList;
	private List<String> prompts;
	private List<String> promptMessages;
	private List<String> noMatchPromptMessages;
	private List<String> noInputPromptMessages;
	private List<String> outputDialogContexts;

   
	public void OntoClassesRespPar(String id, String name, boolean required, String dataType, String value, String defaultValue, boolean isList, List<String> prompts, List<String> promptMessages, List<String> noMatchPromptMessages, List<String> noInputPromptMessages, List<String> outputDialogContexts){
	  this.id = id;
      this.name = name;
      this.required = required;
      this.dataType = dataType;
      this.value = value;
      this.defaultValue = defaultValue;
      this.isList = isList;
      this.prompts = prompts;
      this.promptMessages = promptMessages;
      this.noMatchPromptMessages = noMatchPromptMessages;
      this.noInputPromptMessages = noInputPromptMessages;
      this.outputDialogContexts = outputDialogContexts;
	}

   //Getters and setters

	public void setId(String id) {
		   this.id = id;
	   }
	   public void setName(String name) {
		   this.name = name;
	   }
	   public void setRequired(boolean required) {
		   this.required = required;
	   }
	   public void setDataType(String dataType) {
		   this.dataType = dataType;
	   }
	   public void setValue(String value) {
		   this.value = value;
	   }
	   public void setDefaultValue(String defaultValue) {
		   this.defaultValue = defaultValue;
	   }
	   public void setIsList(boolean isList) {
		   this.isList = isList;
	   }
	   public void setPrompts(List<String> prompts) {
		   this.prompts = prompts;
	   }
	   public void setPromptMessages(List<String> promptMessages) {
		   this.promptMessages = promptMessages;
	   }
	   public void setNoMatchPromptMessages(List<String> noMatchPromptMessages) {
		   this.noMatchPromptMessages = noMatchPromptMessages;
	   }
	   public void setNoInputPromptMessages(List<String> noInputPromptMessages) {
		   this.noInputPromptMessages = noInputPromptMessages;
	   }
	   public void setOutputDialogContexts(List<String> outputDialogContexts) {
		   this.outputDialogContexts = outputDialogContexts;
	   }
}