package io.bastrikov.orders.services;


import io.bastrikov.orders.models.Address;
import io.bastrikov.orders.models.Customer;
import io.bastrikov.orders.models.TaxRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Value("${customers.url}")
    private String customersUrl;

    @Value("${addresses.url}")
    private String addressedUrl;

    @Value("${taxes.url}")
    private String taxesUrl;

    RestTemplate restTemplate = new RestTemplate();


    public Customer getCustomer(int customerId) {
        logger.info("customersUrl: " + customersUrl);
        ResponseEntity<Customer> responseEntity=
                restTemplate.getForEntity(customersUrl + "/customer/find/" + customerId, Customer.class);
        Customer customer = responseEntity.getBody();
        logger.info(customer + "");
        return customer;
    }
    public Address getAddress(int addressId) {
        ResponseEntity<Address> responseEntity =
                restTemplate.getForEntity(addressedUrl + "/address/find/" + addressId, Address.class);
        return responseEntity.getBody();
    }

    public double calculateTax(double totalPrice, Address address) {
        TaxRequestEntity taxRequestEntity = new TaxRequestEntity(totalPrice, address);
        Double response = restTemplate.postForObject(taxesUrl + "/calculate", taxRequestEntity, Double.class);
        return response;
    }

}
