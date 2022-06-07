package com.company.team.service.implement;

import com.company.team.data.entity.CartEntity;
import com.company.team.data.entity.CartItemEntity;
import com.company.team.data.entity.CategoryEntity;
import com.company.team.data.entity.ProductEntity;
import com.company.team.data.request.CartItemRequest;
import com.company.team.data.request.CartRequest;
import com.company.team.exception.custom.InternalServerException;
import com.company.team.exception.custom.NotFoundException;
import com.company.team.repository.CartRepository;
import com.company.team.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public void createCart(CartRequest cart, String username){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUsername(username);

        List<CartItemRequest> cartItemRequest = cart.getProductList();

        List<CartItemEntity> cartItemEntityList =  cartItemRequest.stream().map(cartItem->{

            Optional<ProductEntity> productEntity = productRepository.findById(cartItem.getProductId());
            if (!productEntity.isPresent()) {
                throw new NotFoundException("No product found");
            }

            return new CartItemEntity(cartEntity,productEntity.get(),cartItem.getAmount());
        }).collect(Collectors.toList());

        cartEntity.setListCartItemEntities(cartItemEntityList);

        cartRepository.save(cartEntity);
    }
    public void createUpdateCart(CartRequest cart, String username) {

        try {
            CartEntity cartEntityCheck = findByUsername(username);
            deleteCart(cartEntityCheck.getId());
            createCart(cart, username);

        }catch (NotFoundException e){
            createCart(cart, username);
        }

    }


    public void deleteCart(long cartId) {

        Optional<CartEntity> cart = cartRepository.findById(cartId);
        if (!cart.isPresent()) {
            throw new NotFoundException("No category found");
        }
        try {
            cartRepository.deleteById(cartId);
        } catch (Exception ex) {
            throw new InternalServerException("Database error. Can't delete category");
        }

    }


    public CartEntity findByUsername(String userName) {

        CartEntity cartEntity =  cartRepository.findLatestCartByUsername(userName);
        if ( cartEntity == null){
            throw new NotFoundException("user not create cart before");
        }else {
            return cartEntity;
        }

    }
}
