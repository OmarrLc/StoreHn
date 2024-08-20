package hn.test.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.test.store.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

}
