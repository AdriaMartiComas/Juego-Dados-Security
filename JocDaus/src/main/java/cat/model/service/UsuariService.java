package cat.model.service;

import java.util.List;

import cat.model.domain.Usuari;


public interface UsuariService {
	
	public void guardar(Usuari usuari);
	
	public List<Usuari> mostrarTot();

	public Usuari buscarPerId(Integer id);

	public List<Usuari> buscarPerNom(String nom);

	public void eliminarPerId(Integer id);


}
