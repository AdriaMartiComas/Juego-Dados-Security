package cat.model.service;

import java.util.List;

import cat.model.domain.Partida;

public interface PartidaService {
	public List<Partida> mostrarPartidesUsuari(Integer pk_UsuariID);

	public void guardar(Partida partida, Integer pk_UsuariID);

	public void eliminarPartides(Integer id);
	
	public Partida jugar(Integer pk_UsuariID);
}
