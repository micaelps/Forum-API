package br.com.micaelps.forum.config.security;


import br.com.micaelps.forum.modelo.Usuario;
import br.com.micaelps.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository ur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = ur.findByEmail(username);
        if(usuario.isPresent()){
            return usuario.get();
        }
        else {
            throw new UsernameNotFoundException("dados  invalidos");
        }

    }




    
}
