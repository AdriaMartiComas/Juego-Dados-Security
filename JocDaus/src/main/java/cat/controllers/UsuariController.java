package cat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import cat.model.domain.Usuari;

import cat.model.service.UsuariService;

@RestController
public class UsuariController {

	@Autowired
	private UsuariService usuariService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Mostrar Usuaris
	@GetMapping("/mostrarUsuaris")
	public ResponseEntity<?> mostrarUsuaris() {

		return new ResponseEntity<>(usuariService.mostrarTot(), HttpStatus.OK);
	}

	// Crear Usuari
/*	@PostMapping("/crearUsuari")
	public ResponseEntity<Usuari> crearUsuari(@RequestBody Usuari usuari) {
		List<Usuari> list = usuariService.mostrarTot();

		if (usuari.getNomUsuari().isBlank()) {
			usuari.setNomUsuari("ANONIM");
			usuariService.guardar(usuari);

		}

		if (!list.isEmpty() && !usuari.getNomUsuari().isBlank()) {
			for (Usuari usuarif : list) {
				if (usuari.getNomUsuari().equals(usuarif.getNomUsuari())) {
					return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
				}
			}
		}
		usuariService.guardar(usuari);
		return new ResponseEntity<>(usuari, HttpStatus.CREATED);
	}*/

	// Actualitzar Usuari
	@PutMapping("/modificarUsuari")
	public ResponseEntity<Usuari> actualitzarUsuari(@RequestBody Usuari usuari) {
		List<Usuari> list = usuariService.mostrarTot();

		if (!list.isEmpty()) {
			for (Usuari usuarif : list) {
				if (usuari.getPk_UsuariID() == usuarif.getPk_UsuariID()) {
					usuari.setPassword(passwordEncoder.encode(usuari.getPassword()));
					usuariService.guardar(usuari);

					return new ResponseEntity<Usuari>(usuari, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Ranquing mig de tots els jugadors
	@GetMapping("/ranking")
	public ResponseEntity<Double> rankingMitja() {
		double mitjana = 0, suma = 0;
		int count = 0;
		List<Usuari> list = usuariService.mostrarTot();

		for (Usuari usuari : list) {
			suma += usuari.getPercentatgeExit();
			count++;
		}
		mitjana = suma / count;

		return new ResponseEntity<Double>(mitjana, HttpStatus.OK);
	}

	// Loser
	@GetMapping("/loser")
	public ResponseEntity<Usuari> loser() {

		List<Usuari> list = usuariService.mostrarTot();
		Usuari uLoser = usuariService.buscarPerId(1);

		for (Usuari usuari : list) {
			if (uLoser.getPercentatgeExit() > usuari.getPercentatgeExit()) {
				uLoser = usuari;
			}
		}

		return new ResponseEntity<Usuari>(uLoser, HttpStatus.OK);

	}

	// Winer
	@GetMapping("/winer")
	public ResponseEntity<Usuari> winer() {

		List<Usuari> list = usuariService.mostrarTot();
		Usuari uWiner = new Usuari();

		for (Usuari usuari : list) {
			if (uWiner.getPercentatgeExit() < usuari.getPercentatgeExit()) {
				uWiner = usuari;
			}
		}

		return new ResponseEntity<Usuari>(uWiner, HttpStatus.OK);

	}

}
