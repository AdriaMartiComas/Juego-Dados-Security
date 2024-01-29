package cat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cat.model.domain.Partida;
import cat.model.domain.Usuari;
import cat.model.service.PartidaService;
import cat.model.service.UsuariService;

@RestController
@RequestMapping("/partida")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PartidaController {

	@Autowired
	private PartidaService partidaService;
	@Autowired
	private UsuariService usuariService;

	// Jugar partida
	@PostMapping("/jugarDaus/{pk_UsuariID}")
	public ResponseEntity<Partida> jugarDaus(@PathVariable Integer pk_UsuariID) {
		Partida partida = partidaService.jugar(pk_UsuariID);

		return new ResponseEntity<Partida>(partida, HttpStatus.OK);
	}

	// Eliminar partides usuari
	@GetMapping("/eliminarPartides/{pk_UsuariID}")
	public ResponseEntity<?> eliminarPartides(@PathVariable Integer pk_UsuariID) {
		Usuari usuari = usuariService.buscarPerId(pk_UsuariID);
		partidaService.eliminarPartides(pk_UsuariID);
		List<Partida> partidesUsuari = partidaService.mostrarPartidesUsuari(pk_UsuariID);
		usuari.setPercentatgeExit(0);
		usuariService.guardar(usuari);


		return new ResponseEntity<>(partidesUsuari, HttpStatus.OK);
	}
	
	//Mostrar partides usuari
	@GetMapping("/mostrarPartidesUsuari/{pk_UsuariID}")
	public ResponseEntity<?> mostrarPartidesUsuari(@PathVariable Integer pk_UsuariID) {
		List<Partida> partidesUsuari = partidaService.mostrarPartidesUsuari(pk_UsuariID);

		return new ResponseEntity<>(partidesUsuari, HttpStatus.OK);

	}

}
