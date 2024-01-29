package cat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cat.model.domain.Usuari;
import cat.model.dto.AuthResponseDto;
import cat.model.dto.LoginDto;
import cat.model.dto.RegistreDto;
import cat.model.repository.UsuariRepository;
import cat.security.JwtTokenGenerator;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

	private AuthenticationManager authManager;
	private UsuariRepository usuariRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenGenerator jwtTokenGenerator;

	@Autowired
	public AuthController(AuthenticationManager authManager, UsuariRepository usuariRepository,
			PasswordEncoder passwordEncoder, JwtTokenGenerator jwtTokenGenerator) {
		this.authManager = authManager;
		this.usuariRepository = usuariRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenGenerator = jwtTokenGenerator;
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getNomUsuari(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenGenerator.generateToken(authentication);
		return new ResponseEntity<AuthResponseDto>(new AuthResponseDto(token), HttpStatus.OK);
	}

	//log out

	
	
	@PostMapping("/registre")
	public ResponseEntity<String> registre(@Valid @RequestBody RegistreDto registreDto){

		if(usuariRepository.existsByNomUsuari(registreDto.getNomUsuari())) {
			throw new IllegalArgumentException("El nom d'usuari ja esta usat");
		}
		
		Usuari usuari = new Usuari();
		usuari.setNomUsuari(registreDto.getNomUsuari());
		usuari.setPassword(passwordEncoder.encode(registreDto.getPassword()));
		
		usuariRepository.save(usuari);
		
		return new ResponseEntity<String>("Registre completat!", HttpStatus.OK);
	}

}
