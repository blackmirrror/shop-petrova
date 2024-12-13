package orderservice.service;


import orderservice.client.ProductDto;
import orderservice.client.ProductClient;
import orderservice.entity.Order;
import orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    public Order placeOrder(Long productId, int quantity) {
        ProductDto product = productClient.getProductById(productId);
        if (product != null && product.getStock() >= quantity) {
            Order order = new Order();
            order.setProductId(productId);
            order.setQuantity(quantity);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Недостаточно товара на складе");
        }
    }
}
