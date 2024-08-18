package com.fatichdev.online_food_ordering.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
    private Long cartItemId;
    private Integer quantity;
}
