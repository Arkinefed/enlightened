package com.arkinefed.luminous;

import com.arkinefed.luminous.model.Genre;
import com.arkinefed.luminous.model.Order;
import com.arkinefed.luminous.model.SamplePack;
import com.arkinefed.luminous.model.User;
import com.arkinefed.luminous.service.GenreService;
import com.arkinefed.luminous.service.OrderService;
import com.arkinefed.luminous.service.SamplePackService;
import com.arkinefed.luminous.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserService userService;
    private final GenreService genreService;
    private final SamplePackService samplePackService;
    private final OrderService orderService;

    public DataInitializer(UserService userService,
                           GenreService genreService,
                           SamplePackService samplePackService,
                           OrderService orderService) {
        this.userService = userService;
        this.genreService = genreService;
        this.samplePackService = samplePackService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User("Arkinefed", "x", "Adrian", "Ciesielski", User.Role.admin, LocalDateTime.now());
        User user = new User("Defenikra", "y", "Nairda", "Iksleiseic", User.Role.user, LocalDateTime.now());

        userService.saveUser(admin);
        userService.saveUser(user);

        Genre trap = new Genre("trap");
        Genre edm = new Genre("edm");

        genreService.saveGenre(trap);
        genreService.saveGenre(edm);

        SamplePack trapSamplePack = new SamplePack("Trap Sample Pack",
                37.0,
                trap,
                "Trap sample pack from another planet",
                LocalDateTime.of(2023, 5, 1, 0, 0));
        SamplePack edmSamplePack = new SamplePack("EDM from Heaven",
                25.0,
                edm,
                "Sample pack from Heaven",
                LocalDateTime.of(2023, 5, 8, 0, 0));

        samplePackService.saveSamplePack(trapSamplePack);
        samplePackService.saveSamplePack(edmSamplePack);

        Order order1 = new Order(LocalDateTime.now().minusHours(37),
                admin,
                List.of(trapSamplePack, edmSamplePack));
        Order order2 = new Order(LocalDateTime.now().minusHours(5),
                user,
                List.of(trapSamplePack));

        orderService.saveOrder(order1);
        orderService.saveOrder(order2);
    }
}
