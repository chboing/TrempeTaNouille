package trempe.ta.nouille.server.objects;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class CommentaireNouillePalace {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private String commentaire;
	@Persistent
	private Date date;
	@Persistent
	private int nouillePlus;
	@Persistent
	private int nouilleMoins;
	
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNouillePlus() {
		return nouillePlus;
	}
	public void setNouillePlus(int nouillePlus) {
		this.nouillePlus = nouillePlus;
	}
	public int getNouilleMoins() {
		return nouilleMoins;
	}
	public void setNouilleMoins(int nouilleMoins) {
		this.nouilleMoins = nouilleMoins;
	}
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	
}
