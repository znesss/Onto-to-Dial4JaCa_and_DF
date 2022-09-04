package Onto2DD;


public class MakeAgentWbk{
	private String url;
    private String username;
    //private OntoAgent_wbhook_h headers;
    private boolean available;
    private boolean useForDomains;
    private boolean cloudFunctionsEnabled;
    private boolean cloudFunctionsInitialized;
//    public class OntoAgent_wbhook_h{
//    	public void OntoAgent_wbhook_h() {
//    	}
//    }
    public MakeAgentWbk(){
		this.url = "";
	    this.username = "";
	    //headers.OntoAgent_wbhook_h();
	    this.available = true;
	    this.useForDomains = false;
	    this.cloudFunctionsEnabled = false;
	    this.cloudFunctionsInitialized = false;
    }
}
