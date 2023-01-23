package cat.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "partides")
public class Partida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pk_partidaID;

	private int dau1;

	private int dau2;

	private boolean resultat;

	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "usuari_id")
	private Usuari usuari;

	public Partida() {
	}

	public Partida(Integer pk_partidaID, int dau1, int dau2, boolean resultat, Usuari usuari) {
		this.pk_partidaID = pk_partidaID;
		this.dau1 = dau1;
		this.dau2 = dau2;
		this.resultat = resultat;
		this.usuari = usuari;
	}

	public Integer getPk_partidaID() {
		return pk_partidaID;
	}

	public void setPk_partidaID(Integer pk_partidaID) {
		this.pk_partidaID = pk_partidaID;
	}

	public int getDau1() {
		return dau1;
	}

	public void setDau1(int dau1) {
		this.dau1 = dau1;
	}

	public int getDau2() {
		return dau2;
	}

	public void setDau2(int dau2) {
		this.dau2 = dau2;
	}

	public boolean isResultat() {
		return resultat;
	}

	public void setResultat(boolean resultat) {
		this.resultat = resultat;
	}

	public Usuari getUsuari() {
		return usuari;
	}

	public void setUsuari(Usuari usuari) {
		this.usuari = usuari;
	}

}