package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Client;
import org.example.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    public String create(@Valid @ModelAttribute Client client, BindingResult result) {
        if (result.hasErrors()) {
            return "clients/form";
        }
        clientService.create(client);
        return "redirect:/clients";
    }

    @GetMapping
    public String getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model
    ) {
        Page<Client> clients = clientService.getAll(
                PageRequest.of(page, size, Sort.by("fullName", "id"))
        );
        model.addAttribute("clients", clients);
        return "clients/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getById(id));
        return "clients/form";
    }


    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Client client,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "clients/form";
        }
        clientService.update(id, client);
        return "redirect:/clients";
    }
}
