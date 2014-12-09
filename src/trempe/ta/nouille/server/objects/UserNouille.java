package trempe.ta.nouille.server.objects;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable="true")
public class UserNouille implements Serializable{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	@Persistent
	private String login;
	@Persistent
	private String email;
	@Persistent
	private List<RemiseDeBadge> badges;
	@Persistent
	private List<SoireeNouille> soireesNouilles;
	@Persistent
	private List<CommentaireNouillePalace> commentaires;
	@Persistent
	private Integer compteur;
	@Persistent
	private String password;
	@Persistent
	private String cheminImage;
	
	
	public UserNouille(String login) {
		setKey(KeyFactory.createKey(UserNouille.class.getSimpleName(), login));
	}
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public String getEmail() {
		return email;
	}
	private void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public List<RemiseDeBadge> getBadges() {
		return badges;
	}
	public void setBadges(List<RemiseDeBadge> badges) {
		this.badges = badges;
	}
	public List<SoireeNouille> getSoireesNouilles() {
		return soireesNouilles;
	}
	public void setSoireesNouilles(List<SoireeNouille> soireesNouilles) {
		this.soireesNouilles = soireesNouilles;
	}
	public List<CommentaireNouillePalace> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(List<CommentaireNouillePalace> commentaires) {
		this.commentaires = commentaires;
	}
	public Integer getCompteur() {
		return compteur;
	}
	public void setCompteur(Integer compteur) {
		this.compteur = compteur;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCheminImage() {
		return cheminImage;
	}
	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}
	
	
}
