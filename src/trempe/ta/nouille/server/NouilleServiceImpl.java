package trempe.ta.nouille.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import trempe.ta.nouille.client.NouilleService;
import trempe.ta.nouille.server.objects.UserNouille;
import trempe.ta.nouille.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class NouilleServiceImpl extends RemoteServiceServlet implements NouilleService {

	private static final String OK = "OK";


	public String test(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 3 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	
	@Override
	public String subscribe(String login, String password, String email) throws IllegalArgumentException {
		if (!FieldVerifier.isValidName(login)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 3 characters long");
		}
		
		UserNouille nouvelleNouille = new UserNouille(email);
		nouvelleNouille.setLogin(login);
		nouvelleNouille.setPassword(password);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
            pm.makePersistent(nouvelleNouille);
        } finally {
            pm.close();
        }
		return "Salut "+ login + "! ";
	}

	@Override
	public String login(String login, String password) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
        } finally {
            pm.close();
        }
		
		return null;
	}

	@Override
	public List<String> getUserList() {
		List<String> returnedList = new ArrayList<String>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		
		try {
			Query q = pm.newQuery(UserNouille.class);
			List<UserNouille> results = (List<UserNouille>) q.execute();
			for(UserNouille nouille:results){
				returnedList.add(nouille.getLogin());
			}
			return returnedList;
        } finally {
            pm.close();
        }
	}
}
