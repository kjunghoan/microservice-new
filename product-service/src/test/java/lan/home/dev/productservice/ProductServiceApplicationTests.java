package lan.home.dev.productservice;


import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;

import lan.home.dev.productservice.dto.ProductRequest;
import lan.home.dev.productservice.repository.ProductRepository;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

  //The mongoDB container that will be used for testing
  @Container
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
  static {mongoDBContainer.start();}

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ProductRepository productRepository;

  //This method will initialize the mongoDB container
  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    try{
      registry.add("spring.data.mongodb.uri", mongoDBContainer:: getReplicaSetUrl);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  //Integration test to check createProduct method (ProductController)
  //Expected result: Product created (HTTP status code 201)
  @Test
  void shouldCreateProduct() throws Exception {

    ProductRequest productRequest = getProductRequest();

    String productRequestString = objectMapper.writeValueAsString(productRequest);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(productRequestString))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    
    Assertions.assertEquals(1, productRepository.findAll().size());
  }

  //Mock constructor for a ProductRequest object
  private ProductRequest getProductRequest() {
    return ProductRequest.builder()
        .name("iPhone 13")
        .description("iPhone 13")
        .price(BigDecimal.valueOf(1200))
        .build();
  }
}