package trempe.ta.nouille.server.objects;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class NouillePalace {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private List<String> listeDesUserNouilles;
	
	@Persistent
	private List<SoireeNouille> listeDesSoireesNouilles;
	
	@Persistent
	String commentaireAcceuil;
	
	@Persistent
	private List<CommentaireNouillePalace> commentairesPagePrincipale;

	public List<String> getListeDesUserNouilles() {
		return listeDesUserNouilles;
	}

	public void setListeDesUserNouilles(List<String> listeDesUserNouilles) {
		this.listeDesUserNouilles = listeDesUserNouilles;
	}

	public List<SoireeNouille> getListeDesSoireesNouilles() {
		return listeDesSoireesNouilles;
	}

	public void setListeDesSoireesNouilles(List<SoireeNouille> listeDesSoireesNouilles) {
		this.listeDesSoireesNouilles = listeDesSoireesNouilles;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public List<CommentaireNouillePalace> getCommentairesPagePrincipale() {
		return commentairesPagePrincipale;
	}

	public void setCommentairesPagePrincipale(List<CommentaireNouillePalace> commentairesPagePrincipale) {
		this.commentairesPagePrincipale = commentairesPagePrincipale;
	}

	public String getCommentaireAcceuil() {
		return commentaireAcceuil;
	}

	public void setCommentaireAcceuil(String commentaireAcceuil) {
		this.commentaireAcceuil = commentaireAcceuil;
	}
	
	
}
