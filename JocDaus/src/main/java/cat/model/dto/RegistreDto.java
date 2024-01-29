package cat.model.dto;

import javax.validation.constraints.NotEmpty;

public class RegistreDto {

	@NotEmpty(message = "El nom d'usuari no pot estar buit")
	private String nomUsuari;
	@NotEmpty(message = "La contrasenya no pot estar buida")
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
