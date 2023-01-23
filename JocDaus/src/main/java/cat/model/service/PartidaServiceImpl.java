package cat.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.model.domain.Partida;
import cat.model.domain.Usuari;
import cat.model.repository.PartidaRepository;
import cat.model.repository.UsuariRepository;

@Service
public class PartidaServiceImpl implements PartidaService {

	@Autowired
	private PartidaRepository pRepo;
	
	@Autowired
	private UsuariRepository uRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Partida> mostrarPartidesUsuari(Integer pk_UsuariID) {
		List<Partida> partidesUsuari = pRepo.findAll().stream()
				.filter(u -> pk_UsuariID == u.getUsuari().getPk_UsuariID()).collect(Collectors.toList());

		return partidesUsuari;
	}

	@Override
	@Transactional
	public void guardar(Partida partida, Integer pk_UsuariID) {
		Usuari usuari = uRepo.findById(pk_UsuariID).get();
		partida.setUsuari(usuari);
		pRepo.save(partida);
	}

	@Override
	@Transactional
	public void eliminarPartides(Integer pk_UsuariID) {
		List<Partida> partidesUsuari = pRepo.findAll().stream()
				.filter(u -> pk_UsuariID == u.getUsuari().getPk_UsuariID()).collect(Collectors.toList());
		pRepo.deleteAll(partidesUsuari);

	}
	
	
	@Override
	@Transactional
	public Partida jugar(Integer pk_UsuariID) {
		Partida partida = new Partida();
		Usuari usuari = uRepo.findById(pk_UsuariID).get();
				


		int dau1 = (int) (Math.random() * 6 + 1);
		int dau2 = (int) (Math.random() * 6 + 1);
		boolean resultat = false;

		if (dau1 + dau2 == 7) {
			resultat = true;
		}
		
		partida.setUsuari(usuari);
		partida.setDau1(dau1);
		partida.setDau2(dau2);
		partida.setResultat(resultat);

		// Set percentatge exit
		List<Partida> partidesUsuari = usuari.getPartides();
		int partidesGuanyades = 0, size = partidesUsuari.size();
		double percentatgeExit = 0;

		for (int i = 0; i < size; i++) {
			if (partidesUsuari.get(i).isResultat()) {
				partidesGuanyades = partidesGuanyades + 1;
			}
		}
		if (partidesGuanyades != 0) {
			percentatgeExit = (double) (partidesGuanyades / (double) (size + 1)) * 100;
		}
		usuari.setPartides(partidesUsuari);
		usuari.setPercentatgeExit(percentatgeExit);
		
		
		pRepo.save(partida);
		uRepo.save(usuari);

		
		
		return partida;
		
		
		
	}

}
