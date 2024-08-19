package com.fatichdev.online_food_ordering.service;

import com.fatichdev.online_food_ordering.model.Cart;
import com.fatichdev.online_food_ordering.model.CartItem;
import com.fatichdev.online_food_ordering.model.User;
import com.fatichdev.online_food_ordering.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception;
    public CartItem updateCartItemQuantity(Long cartItemId, Integer quantity) throws Exception;
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;
    public Long calculateCartTotals(Cart cart) throws Exception;
    public Cart findCartById(Long cartId) throws Exception;
    public Cart findCartByUserId(String  jwt) throws Exception;
    public Cart clearCart(String jwt) throws Exception;
    public Cart findCartByUser(User user) throws Exception;

}
