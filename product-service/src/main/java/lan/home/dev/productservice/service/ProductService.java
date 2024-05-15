package lan.home.dev.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lan.home.dev.productservice.dto.ProductRequest;
import lan.home.dev.productservice.dto.ProductResponse;
import lan.home.dev.productservice.model.Product;
import lan.home.dev.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {


  private final ProductRepository productRepository;


  public void createProduct(ProductRequest productRequest) {
    Product product = Product.builder()
      .name(productRequest.getName())
      .description(productRequest.getDescription())
      .price(productRequest.getPrice())
      .build();
    
      productRepository.save(product);
      log.info("Product {} saved successfully", product.getId());
  }

  public List<ProductResponse> getAllProducts() {
    List<Product> products = productRepository.findAll();

    return products.stream().map(this::mapToProductResponse).toList();
  }

  private ProductResponse mapToProductResponse(Product product) {
    return ProductResponse.builder()
    .id(product.getId())
    .name(product.getName())
    .description(product.getDescription())
    .price(product.getPrice())
    .build();
  }

}
