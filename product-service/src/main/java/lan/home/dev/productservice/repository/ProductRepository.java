package lan.home.dev.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import lan.home.dev.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
