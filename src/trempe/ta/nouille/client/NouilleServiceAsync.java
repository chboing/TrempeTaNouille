package trempe.ta.nouille.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface NouilleServiceAsync {
	void test(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	void subscribe(String login,String password,String email, AsyncCallback<String> callback) throws IllegalArgumentException;
	void login(String login,String password, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void getUserList(AsyncCallback<List<String>> callback) throws IllegalArgumentException;
}
