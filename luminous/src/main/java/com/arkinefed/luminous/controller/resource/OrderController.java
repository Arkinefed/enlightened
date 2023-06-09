package com.arkinefed.luminous.controller.resource;

import com.arkinefed.luminous.data.order.OrderRequest;
import com.arkinefed.luminous.model.Order;
import com.arkinefed.luminous.model.SamplePack;
import com.arkinefed.luminous.model.User;
import com.arkinefed.luminous.service.OrderService;
import com.arkinefed.luminous.service.SamplePackService;
import com.arkinefed.luminous.service.UserService;
import com.arkinefed.luminous.utility.Authorization;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<?> place(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer,
                                   @RequestBody OrderRequest orderRequest) {
        String token = bearer.split("\\s+")[1];

        try {
            if (!Authorization.verifyToken(token)) {
                return ResponseEntity.status(403).body("access denied");
            }

            if (Authorization.tokenExpired(token)) {
                return ResponseEntity.status(401).body("token expired");
            }

            String username = Authorization.getValueFromTokenPayload("username", token);

            User user = userService.findByUsername(username);
            List<SamplePack> samplePacks = new ArrayList<>();

            for (long id : orderRequest.getIds()) {
                samplePacks.add(samplePackService.findById(id));
            }

            orderService.saveOrder(new Order(LocalDateTime.now(), user, samplePacks));

            return ResponseEntity.ok("order complete");
        } catch (NoSuchAlgorithmException | InvalidKeyException | JsonProcessingException e) {
            return ResponseEntity.status(500).body("internal error");
        }
    }
}
