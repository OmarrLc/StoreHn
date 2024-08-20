package hn.test.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.test.store.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{

}
