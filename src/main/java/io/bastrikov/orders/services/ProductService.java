package io.bastrikov.orders.services;

import io.bastrikov.orders.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductService {
    @Value("${products.url}")
    private String productsUrl;

    public List<Product> productList = new LinkedList<>();
    RestTemplate restTemplate = new RestTemplate();

    public List<Product> getProductList(List<Integer> productIdList){
        List<Product> productList = new LinkedList<>();
        for(int productId: productIdList) {
            ResponseEntity<Product> responseEntity =
                    restTemplate.getForEntity(productsUrl + "/find/" + productId, Product.class);
            productList.add(responseEntity.getBody());
        }
        return productList;
    }
    public double calculateTotalPrice(List<Integer> productIdList){
        if (productList.isEmpty()) {
            productList = getProductList(productIdList);
        }
        double totalPrice = 0;
        for(Product product: productList) totalPrice += product.getPrice();
        return totalPrice;
    }
}
