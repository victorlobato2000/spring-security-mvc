package com.victorlobato.curso.security.repository;

import com.victorlobato.curso.security.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioReposity extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.email like :email")
    Usuario findByEmail(@Param("email") String email);
}
