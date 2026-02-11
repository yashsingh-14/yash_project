package com.yash.store.controller;

import com.yash.store.model.Address;
import com.yash.store.model.User;
import com.yash.store.model.enums.State;
import com.yash.store.repository.AddressRepository;
import com.yash.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String viewAddresses(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        List<Address> addresses = addressRepository.findAllByUser(user);
        model.addAttribute("addresses", addresses);
        model.addAttribute("states", State.values());

        return "address";
    }

    @PostMapping("/add")
    public String addAddress(@RequestParam String name,
            @RequestParam String area,
            @RequestParam String city,
            @RequestParam State state,
            @RequestParam Integer pincode) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        Address address = new Address();
        address.setName(name);
        address.setArea(area);
        address.setCity(city);
        address.setState(state);
        address.setPincode(pincode);
        address.setUser(user);

        addressRepository.save(address);

        return "redirect:/address";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id) {
        addressRepository.deleteById(id);
        return "redirect:/address";
    }
}
