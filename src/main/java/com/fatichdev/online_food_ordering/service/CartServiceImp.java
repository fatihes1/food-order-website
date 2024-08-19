package com.fatichdev.online_food_ordering.service;

import com.fatichdev.online_food_ordering.model.Cart;
import com.fatichdev.online_food_ordering.model.CartItem;
import com.fatichdev.online_food_ordering.model.Food;
import com.fatichdev.online_food_ordering.model.User;
import com.fatichdev.online_food_ordering.repository.CartItemRepository;
import com.fatichdev.online_food_ordering.repository.CartRepository;
import com.fatichdev.online_food_ordering.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;


    @Override
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.findFoodById(request.getFoodId());

        Cart cart = cartRepository.findByCustomerId(user.getId());

        for (CartItem cartItem: cart.getItems()) {
            if (cartItem.getFood().equals(food)) {
                int newQuantity = cartItem.getQuantity() + request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setFood(food);
        cartItem.setQuantity(request.getQuantity());
        cartItem.setCart(cart);
        cartItem.setIngredients(request.getIngredients());
        cartItem.setTotalPrice(food.getPrice() * request.getQuantity());
        CartItem savedCartItem = cartItemRepository.save(cartItem);

        cart.getItems().add(savedCartItem);
        cartRepository.save(cart);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, Integer quantity) throws Exception {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);

        if (optionalCartItem.isEmpty()) {
            throw new Exception("Cart item not found");
        }

        CartItem cartItem = optionalCartItem.get();
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getFood().getPrice() * quantity);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);

        if (optionalCartItem.isEmpty()) {
            throw new Exception("Cart item not found");
        }

        CartItem cartItem = optionalCartItem.get();

        cart.getItems().remove(cartItem);
        cartRepository.save(cart);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        long total = 0;

        for (CartItem cartItem: cart.getItems()) {
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long cartId) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);

        if (optionalCart.isEmpty()) {
            throw new Exception("Cart not found");
        }

        return optionalCart.get();

    }

    @Override
    public Cart findCartByUserId(String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        return cartRepository.findByCustomerId(user.getId());
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        cart.getItems().clear();
        return cartRepository.save(cart);
    }

    @Override
    public Cart findCartByUser(User user) throws Exception {
        Cart cart = cartRepository.findByCustomerId(user.getId());
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }


}
