package lan.home.dev.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lan.home.dev.inventoryservice.model.Inventory;
import lan.home.dev.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	/**
	 * Only here for testing and development purposes.
	 * Remove this bean before seeding and deploying to production.
	 * Also be sure to change `spring.jpa.hibernate.ddl-auto` property to `none` in application.properties
	 * This method will be called once the application context has been loaded and seed 2 products into the database.
	 * @param inventoryRepository
	 * @return
	 */
	@Bean
	CommandLineRunner loadData (InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(100);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone_13_red");
			inventory1.setQuantity(0);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}
}
