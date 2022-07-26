package Onto2DD;

import java.util.List;

public class OntoClasses
{
   private String id;
   private String name;
   private boolean auto;
   private List<String> contexts;
   private OntoClassesResp responses;
   private int priority;
   private boolean webhookUsed;
   private boolean webhookForSlotFilling;
   private boolean fallbackIntent;
   private List<String> events;
   private List<String> conditionalResponses;
   private String condition;
   private List<String> conditionalFollowupEvents;

   public void OntoClasses(String id, String name, boolean auto, List<String> contexts, OntoClassesResp responses, int priority, boolean webhookUsed, boolean webhookForSlotFilling, boolean fallbackIntent, List<String> events, List<String> conditionalResponses, String condition, List<String> conditionalFollowupEvents){
	  this.id = id;
      this.name = name;
      this.auto = auto;
      this.contexts = contexts;
      this.responses = responses;
      this.priority = priority;
      this.webhookUsed = webhookUsed;
      this.webhookForSlotFilling = webhookForSlotFilling;
      this.fallbackIntent = fallbackIntent;
      this.events = events;
      this.conditionalResponses = conditionalResponses;
      this.condition = condition;
      this.conditionalFollowupEvents = conditionalFollowupEvents;
   }

   //Getters and setters

   public void setId(String id) {
	   this.id = id;
   }
   public void setName(String name) {
	   this.name = name;
   }
   public void setAuto(Boolean auto) {
	   this.auto = auto;
   }
   public void setContexts(List<String> contexts) {
	   this.contexts = contexts;
   }
   public void setResponses(OntoClassesResp responses) {
	   this.responses = responses;
   }
   public void setPriority(int priority) {
	   this.priority = priority;
   }
   public void setWebhookUsed(boolean webhookUsed) {
	   this.webhookUsed = webhookUsed;
   }
   public void setWebhookForSlotFilling(boolean webhookForSlotFilling) {
	   this.webhookForSlotFilling = webhookForSlotFilling;
   }
   public void setFallbackIntent(boolean fallbackIntent) {
	   this.fallbackIntent = fallbackIntent;
   }
   public void setEvents(List<String> events) {
	   this.events = events;
   }
   public void setConditionalResponses(List<String> conditionalResponses) {
	   this.conditionalResponses = conditionalResponses;
   }
   public void setCondition(String condition) {
	   this.condition = condition;
   }
   public void setConditionalFollowupEvents(List<String> conditionalFollowupEvents) {
	   this.conditionalFollowupEvents = conditionalFollowupEvents;
   }

}
