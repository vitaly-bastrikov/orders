package io.bastrikov.orders.controllers;

import io.bastrikov.orders.models.*;
import io.bastrikov.orders.services.OrderService;
import io.bastrikov.orders.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;
    Logger logger = LoggerFactory.getLogger(Controller.class);

    @Value("${message:hello}")
    private String message;

    @GetMapping("/hello")
    public String test(){
        return message;
    }

    @PostMapping("/createOrder")
    Document createOrder(@RequestBody CreateOrderRequest request) {
        Document document = new Document();
        //getting info
        logger.info("starting getting info");
        Customer customer = orderService.getCustomer(request.getCustomerId());
        logger.info("customer: " + customer);
        Address address = orderService.getAddress(customer.getAddressId());
        logger.info("address: " + address);
        List<Product> products = productService.getProductList(request.getProductIds());
        logger.info("products: " + products);
        double totalPrice = productService.calculateTotalPrice(request.getProductIds());
        double tax = orderService.calculateTax(totalPrice, address);
        logger.info("tax: " + tax);

        //setting
        document.setId(1);
        document.setCustomer(customer);
        document.setAddress(address);
        document.setProducts(products);
        document.setShippingFee(getShippingFee());
        document.setTaxes(tax);
        document.setTotalPrice(totalPrice);
        document.setPriceWithTax(tax + totalPrice);

        return document;
    }



    public int getDeliveryTime(String location) {
        return 3;
    }
    public double getShippingFee() {
        return 14.2;
    }


}
