package trempe.ta.nouille.server.objects;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class RemiseDeBadge {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private Badge theBadge;
	@Persistent
	private Date laDateDobtention;
	@Persistent
	private String commentaire;
	@Persistent
	private Integer niveauDuBadge;
	@Persistent
	private List<CommentaireNouillePalace> commentaires;
	
	
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public List<CommentaireNouillePalace> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(List<CommentaireNouillePalace> commentaires) {
		this.commentaires = commentaires;
	}
	public Badge getTheBadge() {
		return theBadge;
	}
	public void setTheBadge(Badge theBadge) {
		this.theBadge = theBadge;
	}
	public Date getLaDateDobtention() {
		return laDateDobtention;
	}
	public void setLaDateDobtention(Date laDateDobtention) {
		this.laDateDobtention = laDateDobtention;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Integer getNiveauDuBadge() {
		return niveauDuBadge;
	}
	public void setNiveauDuBadge(Integer niveauDuBadge) {
		this.niveauDuBadge = niveauDuBadge;
	}
	
	
}
