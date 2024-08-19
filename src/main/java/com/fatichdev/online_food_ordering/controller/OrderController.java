package com.fatichdev.online_food_ordering.controller;

import com.fatichdev.online_food_ordering.model.Order;
import com.fatichdev.online_food_ordering.model.User;
import com.fatichdev.online_food_ordering.request.OrderRequest;
import com.fatichdev.online_food_ordering.service.OrderService;
import com.fatichdev.online_food_ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


   @PostMapping("/order")
   public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request, @RequestHeader("Authorization") String jwt) throws Exception {
       User user = userService.findUserByJwtToken(jwt);
       Order order = orderService.createOrder(request, user);
       return new ResponseEntity<>(order, HttpStatus.CREATED);
   }

   @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt) throws Exception {
       User user = userService.findUserByJwtToken(jwt);
       List<Order> orders = orderService.getUserOrders(user.getId());

       return new ResponseEntity<>(orders, HttpStatus.OK);
   }

}
