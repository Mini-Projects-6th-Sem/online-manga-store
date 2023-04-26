package com.ooadproject.onlinemangastore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ooadproject.onlinemangastore.exception.ResourceNotFoundException;
import com.ooadproject.onlinemangastore.model.Product;
import com.ooadproject.onlinemangastore.repository.ProductRepository;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Product controller.
 *
 * @author Givantha Kalansuriya
 */
@RestController
@RequestMapping("/api/v1")
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  /**
   * Get all products list.
   *
   * @return the list
   */
  @GetMapping("/products")
  public List<Product> getAllProductss() {
    return productRepository.findAll();
  }

  /**
   * Gets products by id.
   *
   * @param productId the product id
   * @return the products by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping("/products/{id}")
  public ResponseEntity<Product> getProductsById(@PathVariable(value = "id") Long productId)
      throws ResourceNotFoundException {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productId));
    return ResponseEntity.ok().body(product);
  }

  /**
   * Create product product.
   *
   * @param product the product
   * @return the product
   */
  @PostMapping("/products")
  public Product createProduct(@Valid @RequestBody Product product) {
    return productRepository.save(product);
  }

  /**
   * Update product response entity.
   *
   * @param productId the product id
   * @param productDetails the product details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @PutMapping("/products/{id}")
  public ResponseEntity<Product> updateProduct(
      @PathVariable(value = "id") Long productId, @Valid @RequestBody Product productDetails)
      throws ResourceNotFoundException {

    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productId));

    product.setProductName(productDetails.getProductName());
    product.setProductPrice(productDetails.getProductPrice());
    product.setBalanceQuantity(productDetails.getBalanceQuantity());
    product.setUpdatedAt(new Date());
    final Product updatedProduct = productRepository.save(product);
    return ResponseEntity.ok(updatedProduct);
  }

  /**
   * Delete product map.
   *
   * @param productId the product id
   * @return the map
   * @throws Exception the exception
   */
  @DeleteMapping("/product/{id}")
  public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId) throws Exception {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productId));

    productRepository.delete(product);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}
