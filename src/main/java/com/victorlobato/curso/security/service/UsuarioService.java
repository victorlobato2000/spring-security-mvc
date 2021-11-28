package com.victorlobato.curso.security.service;

import com.victorlobato.curso.security.domain.Perfil;
import com.victorlobato.curso.security.domain.Usuario;
import com.victorlobato.curso.security.repository.UsuarioReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioReposity reposity;

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email){
        return reposity.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = buscarPorEmail(username);

        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                AuthorityUtils.createAuthorityList(getAuthorities(usuario.getPerfis())));
    }

    private String[] getAuthorities(List<Perfil> perfis){
        String[] authorities = new String[perfis.size()];

        for (int i = 0; i < perfis.size(); i++){
            authorities[i] = perfis.get(i).getDesc();
        }

        return authorities;
    }
}
