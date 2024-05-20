package lan.home.dev.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import lan.home.dev.orderservice.dto.InventoryResponse;
import lan.home.dev.orderservice.dto.OrderLineItemsDto;
import lan.home.dev.orderservice.dto.OrderRequest;
import lan.home.dev.orderservice.model.Order;
import lan.home.dev.orderservice.model.OrderLineItems;
import lan.home.dev.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

  private final OrderRepository orderRepository;
  private final WebClient.Builder webClientBuilder;

  public void placeOrder(OrderRequest orderRequest) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());

    List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
        .stream()
        .map(this::mapToDto)
        .toList();

    order.setOrderLineItems(orderLineItems);

    List<String> skuCodes = order.getOrderLineItems().stream()
			.map(OrderLineItems::getSkuCode)
      .toList();


    // Call Inventory Service, and place order if product is in stock
    InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
      .uri("http://inventory-service/api/inventory",
				uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
      .retrieve()
        .bodyToMono(InventoryResponse[].class)
          .block();

		boolean allProductsAreInStock = Arrays.stream(inventoryResponseArray)
        .allMatch(InventoryResponse::getIsInStock);

    if (Boolean.TRUE.equals(allProductsAreInStock)) {
      orderRepository.save(order);
    } else {
      throw new IllegalArgumentException("Product is not in stock, please try again later.");
    }
  }

  private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
    OrderLineItems orderLineItems = new OrderLineItems();

    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

    return orderLineItems;
  }

}
