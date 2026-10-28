package com.br.yourmenu.dataprovider.database.repository;

import com.br.yourmenu.dataprovider.database.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {


}
