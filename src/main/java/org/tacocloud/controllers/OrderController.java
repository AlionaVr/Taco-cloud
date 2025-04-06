package org.tacocloud.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.tacocloud.TacoOrder;
import org.tacocloud.repository.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order,
                               Errors errors,
                               SessionStatus status) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        orderRepository.save(order);
        status.setComplete(); //ensuring that the session is cleaned up and ready for a new order

        return "redirect:/";
    }
}
