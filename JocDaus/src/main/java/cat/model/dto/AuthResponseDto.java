package cat.model.dto;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AuthResponseDto {
	private String accesToken;
	private String tokenType = "Bearer ";

	public AuthResponseDto(String accesToken) {
		this.accesToken = accesToken;
	}

}
