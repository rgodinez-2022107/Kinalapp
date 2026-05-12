package com.rodolfogodinez.kinalapp.repository;

import java.util.List;
import com.rodolfogodinez.kinalapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,String> {

    List<Cliente> findByEstado(Integer estado);


}