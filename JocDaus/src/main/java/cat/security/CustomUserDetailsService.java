package cat.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cat.model.domain.Usuari;
import cat.model.repository.UsuariRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UsuariRepository usuariRepository;

	@Autowired
	public CustomUserDetailsService(UsuariRepository usuariRepository) {
		this.usuariRepository = usuariRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuari usuari = usuariRepository.findByNomUsuari(username).orElseThrow(()-> new UsernameNotFoundException("Usuari no trovat"));
		
		
		return new User(usuari.getNomUsuari(), usuari.getPassword(), mapRolesToAuthorities());
		
		
	}
	
	private Collection<GrantedAuthority> mapRolesToAuthorities(){
		List<String> list = new ArrayList<String>();
		list.add("ADMIN");
		list.add("USER");
		
		return list.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		
		
	}

}
