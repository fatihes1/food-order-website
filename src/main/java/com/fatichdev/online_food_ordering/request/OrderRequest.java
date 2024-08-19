package com.fatichdev.online_food_ordering.request;

import com.fatichdev.online_food_ordering.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}
