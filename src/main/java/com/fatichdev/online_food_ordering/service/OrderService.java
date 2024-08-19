package com.fatichdev.online_food_ordering.service;

import com.fatichdev.online_food_ordering.model.Order;
import com.fatichdev.online_food_ordering.model.User;
import com.fatichdev.online_food_ordering.request.OrderRequest;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderRequest order, User user) throws Exception;
    public Order updateOrder(Long orderId, String orderStatus) throws Exception;
    public void cancelOrder(Long orderId) throws Exception;
    public List<Order> getUserOrders(Long userId) throws Exception;
    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception;
    public Order findOrderById(Long orderId) throws Exception;

}
