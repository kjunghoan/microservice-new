package lan.home.dev.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lan.home.dev.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
