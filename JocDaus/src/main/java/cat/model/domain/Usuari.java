package cat.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "usuaris")
public class Usuari {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pk_UsuariID;

	private String nomUsuari;
	
	private String password;

	private Date dataRegistre;

	private double percentatgeExit;
	@JsonIgnore
	@OneToMany(mappedBy = "usuari", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Partida> partides;
	
	

	public Usuari() {
		this.dataRegistre = new Date();
		percentatgeExit = 0;
		partides = new ArrayList<Partida>();
		
	}
	
	public Usuari(String nomUsuari, String password) {
		super();
		this.nomUsuari = nomUsuari;
		this.password = password;
	}

	public Usuari(Integer pk_UsuariID, String nomUsuari, String password, List<Partida> partides) {
		this.pk_UsuariID = pk_UsuariID;
		this.nomUsuari = nomUsuari;
		this.password = password;
		this.dataRegistre = new Date();
		percentatgeExit = 0;
		this.partides = partides;

	}

	public Usuari(Integer pk_UsuariID, String nomUsuari, String password, Date dataRegistre, float percentatgeExit,
			List<Partida> partides) {
		this.pk_UsuariID = pk_UsuariID;
		this.nomUsuari = nomUsuari;
		this.password = password;
		this.dataRegistre = dataRegistre;
		this.percentatgeExit = percentatgeExit;
		this.partides = partides;
	}

	

	public List<Partida> getPartides() {
		return partides;
	}

	public void setPartides(List<Partida> partides) {
		this.partides = partides;
	}

	public String getNomUsuari() {
		return nomUsuari;
	}

	public void setNomUsuari(String nomUsuari) {
		this.nomUsuari = nomUsuari;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getPercentatgeExit() {
		return percentatgeExit;
	}

	public void setPercentatgeExit(double percentatgeExit) {
		this.percentatgeExit = percentatgeExit;
	}

	public Integer getPk_UsuariID() {
		return pk_UsuariID;
	}

	public void setPk_UsuariID(Integer pk_UsuariID) {
		this.pk_UsuariID = pk_UsuariID;
	}

	public void setDataRegistre(Date dataRegistre) {
		this.dataRegistre = dataRegistre;
	}

	public Date getDataRegistre() {
		return dataRegistre;
	}
	
	

}