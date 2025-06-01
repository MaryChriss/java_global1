package com.global.globalSoluction.repository;

import com.global.globalSoluction.model.Favoritos;
import com.global.globalSoluction.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritosRepository extends JpaRepository<Favoritos, Long> {
    
    List<Favoritos> findByUsuario(User usuario);

    boolean existsByUsuarioAndCidadeIgnoreCase(User usuario, String cidade);
}
