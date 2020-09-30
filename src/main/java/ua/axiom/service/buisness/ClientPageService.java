package ua.axiom.service.buisness;

import ua.axiom.model.actors.Order;
import ua.axiom.persistance.jdbcbased.repository.impl.CarRepositoryJDBC;

import java.util.List;

public class ClientPageService {
    private CarRepositoryJDBC clientRepository;
    private OrderService orderService;

    public ClientPageService(CarRepositoryJDBC clientRepository, OrderService orderService) {
        this.clientRepository = clientRepository;
        this.orderService = orderService;
    }

    public List<Order> getTakenOrdersForClientId(long clientID) {
        return orderService.getClientTakenOrders(clientID);

    }

    public List<Order> getPendingOrdersByClientId(long clientID) {
        return orderService.getClientPendingOrders(clientID);
    }
}
