package com.arkinefed.luminous.controller.resource;

import com.arkinefed.luminous.data.order.OrderRequest;
import com.arkinefed.luminous.service.OrderService;
import com.arkinefed.luminous.service.SamplePackService;
import com.arkinefed.luminous.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource/order")
@CrossOrigin(origins = "http://localhost:8081")
public class OrderController {
    private final UserService userService;
    private final SamplePackService samplePackService;
    private final OrderService orderService;

    public OrderController(UserService userService,
                           SamplePackService samplePackService,
                           OrderService orderService) {
        this.userService = userService;
        this.samplePackService = samplePackService;
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<?> place(@RequestBody OrderRequest orderRequest) {
        return null;
    }
}
