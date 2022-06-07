package com.company.team.service.implement;

import com.company.team.data.entity.CartEntity;
import com.company.team.data.entity.CartItemEntity;
import com.company.team.data.entity.OrderEntity;
import com.company.team.data.entity.OrderItemEntity;
import com.company.team.data.request.OrderRequest;
import com.company.team.data.response.dto.OrderUserHistoryDto;
import com.company.team.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    public List<OrderEntity> getListOrder(){
      List<OrderEntity> orders = orderRepository.findAll();
      return orders;
    }

    public void addNewOrder(OrderEntity order) {
        orderRepository.save(order);
    }

    public OrderEntity findOne(int orderId) {
        return orderRepository.getOne(orderId);
    }

    public List<OrderEntity> findOrderByUserName(String userName) {
        return orderRepository.findOrderByUserName(userName);
    }

    public OrderUserHistoryDto getUserHistory(String username){
        OrderUserHistoryDto orderUserHistoryDto = new OrderUserHistoryDto();

        orderUserHistoryDto.setUsername(username);
        List<OrderEntity> orderUserHistoryItemDtoArrayList = findOrderByUserName(username);

        orderUserHistoryDto.setOrderList(orderUserHistoryItemDtoArrayList);

        return orderUserHistoryDto;
    }

    public OrderEntity createOrder(OrderRequest orderReq, String username){
        OrderEntity order = new OrderEntity();

        order.setUserName(username);
        order.setAddress(orderReq.getAddress());
        order.setEmail(orderReq.getEmail());
        order.setPhoneNumber(orderReq.getPhoneNumber());
        order.setCustomerName(orderReq.getCustomerName());
        order.setCreatedDate(new Date());


        CartEntity cartEntity = cartService.findByUsername(username);

        double totalPrice = 0;
        List<OrderItemEntity> orderProducts = new ArrayList<>();
        for (CartItemEntity cartProduct : cartEntity.getListCartItemEntities()) {
            OrderItemEntity orderProduct = new OrderItemEntity();
            orderProduct.setOrderEntityMap(order);
            orderProduct.setProductOrderEntityMap(cartProduct.getProductEntityMap());
            orderProduct.setAmount(cartProduct.getAmount());

            double price = cartProduct.getAmount() * cartProduct.getProductEntityMap().getPrice();
            totalPrice += price;

            orderProduct.setPrice(price);
            orderProducts.add(orderProduct);
        }

        order.setListProductOrders(orderProducts);
        order.setPrice(totalPrice);

        addNewOrder(order);

        cartService.deleteCart(cartEntity.getId());

        return order;
    }
}
