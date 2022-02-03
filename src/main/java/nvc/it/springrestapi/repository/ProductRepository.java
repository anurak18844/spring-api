package nvc.it.springrestapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import nvc.it.springrestapi.models.Product;

public interface ProductRepository extends MongoRepository<Product, String>{
    public List<Product> findByNameContaining(String name);
}
