package com.adjt.agendamento.data.security;

import com.adjt.agendamento.core.util.MensagemUtil;
import com.adjt.agendamento.data.repository.jpa.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public CustomUserDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmailAndAtivoTrue(email)
                .map(UsuarioPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException(MensagemUtil.USUARIO_NAO_ENCONTRADO));
    }
}
