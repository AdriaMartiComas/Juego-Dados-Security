package cat.model.dto;

import java.util.Date;

public class UsuariDto {

	private int id;
	private String nomUsuari;
	private String password;
	private Date dataRegistre;
	private double percentatgeExit;

	public UsuariDto() {
	
	}

	public UsuariDto(int id, String nomUsuari, String password, Date dataRegistre, double percentatgeExit) {
		this.id = id;
		this.nomUsuari = nomUsuari;
		this.password = password;
		this.dataRegistre = dataRegistre;
		this.percentatgeExit = percentatgeExit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getDataRegistre() {
		return dataRegistre;
	}

	public void setDataRegistre(Date dataRegistre) {
		this.dataRegistre = dataRegistre;
	}

	public double getPercentatgeExit() {
		return percentatgeExit;
	}

	public void setPercentatgeExit(double percentatgeExit) {
		this.percentatgeExit = percentatgeExit;
	}

}
