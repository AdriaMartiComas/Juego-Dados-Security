package cat.model.dto;

public class LoginDto {
	private String nomUsuari;
	private String password;

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

}
