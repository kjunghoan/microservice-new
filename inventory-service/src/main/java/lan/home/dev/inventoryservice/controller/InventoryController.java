package lan.home.dev.inventoryservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lan.home.dev.inventoryservice.dto.InventoryResponse;
import lan.home.dev.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
  
  private final InventoryService inventoryService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<InventoryResponse> isInStock(@RequestParam List<String> skuCodes) {
    log.info("Received request to check if sku codes {} are in stock", skuCodes);
    return inventoryService.isInStock(skuCodes);
  }
}
