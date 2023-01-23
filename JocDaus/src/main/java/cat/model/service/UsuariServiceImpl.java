package cat.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.model.domain.Usuari;
import cat.model.repository.UsuariRepository;

@Service
public class UsuariServiceImpl implements UsuariService {

	@Autowired
	private UsuariRepository usuariRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Usuari> mostrarTot() {
		return usuariRepository.findAll();
	}

	@Override
	@Transactional
	public void guardar(Usuari usuari) {
		usuariRepository.save(usuari);
	}

	@Override
	public Usuari buscarPerId(Integer id) {
		return usuariRepository.findById(id).get();
	}

	@Override
	public List<Usuari> buscarPerNom(String nom) {
		List<Usuari> listIn = usuariRepository.findAll();
		List<Usuari> listOut = new ArrayList<Usuari>();

		for (Usuari usuari : listIn) {
			if (usuari.getNomUsuari().contains(nom)) {
				listOut.add(usuari);
			}
		}
		return listOut;
	}

	@Override
	public void eliminarPerId(Integer id) {
		usuariRepository.deleteById(id);

	}

}
