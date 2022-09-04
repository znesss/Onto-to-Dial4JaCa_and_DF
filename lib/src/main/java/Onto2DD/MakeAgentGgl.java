package Onto2DD;

import java.util.List;

public class MakeAgentGgl {

	public boolean googleAssistantCompatible;
	public String project;
	public boolean welcomeIntentSignInRequired;
	public List<String> startIntents;
	public List<String> systemIntents;
	public List<String> endIntentIds;
	//public OntoAgent_gglAssist_oAuth oAuthLinking;//////////
    public String voiceType;
    public List<String> capabilities;
    public String env;
    public String protocolVersion;
    public boolean autoPreviewEnabled;
    public boolean isDeviceAgent;
	//public class OntoAgent_gglAssist_oAuth{
//		public boolean required;
//	    public String providerId;
//	    public String authorizationUrl;
//	    public String tokenUrl;
//	    public String scopes;
//	    public String privacyPolicyUrl;
//	    public String grantType;
//		public void OntoAgent_gglAssist_oAuth(){
//			this.required = false;
//		    this.providerId = "";
//		    this.authorizationUrl = "";
//		    this.tokenUrl = "";
//		    this.scopes = "";
//		    this.privacyPolicyUrl = "";
//		    this.grantType = "AUTH_CODE_GRANT";
//		}
//	}
    public void main(){
		this.googleAssistantCompatible = false;
	    this.project = "";
	    System.out.println(this.project);
	    this.welcomeIntentSignInRequired = false;
	    this.startIntents = null;
	    this.systemIntents = null;
	    this.endIntentIds = null;
	    //oAuthLinking.OntoAgent_gglAssist_oAuth();
		this.voiceType = "VOICE_TYPE_UNSPECIFIED";
	    this.capabilities = null;
	    this.env = "";
	    this.protocolVersion = "PROTOCOL_VERSION_UNSPECIFIED";
	    this.autoPreviewEnabled = false;
	    this.isDeviceAgent = false;
	}
}