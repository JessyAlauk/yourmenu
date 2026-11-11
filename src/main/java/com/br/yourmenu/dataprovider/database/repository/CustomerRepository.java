package com.br.yourmenu.dataprovider.database.repository;

import com.br.yourmenu.dataprovider.database.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    List<CustomerEntity> findByFirstNameContainingIgnoreCase(String firstName);
}
