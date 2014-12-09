package trempe.ta.nouille.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface NouilleService extends RemoteService {
	String test(String name) throws IllegalArgumentException;
	
	String subscribe(String name,String password, String email) throws IllegalArgumentException;

	String login(String login, String password);

	List<String> getUserList();
}
