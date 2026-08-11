package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Client;
import org.example.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Client client) {
        clientService.create(client);
        return "redirect:/clients";
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("clients", clientService.getAll());
        return "clients/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getById(id));
        return "clients/form";
    }


    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Client client) {
        clientService.update(id, client);
        return "redirect:/clients";
    }
}
