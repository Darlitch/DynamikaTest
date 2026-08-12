package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Client;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }

    public List<Client> getAllList() {
        return clientRepository.findAll(Sort.by(Sort.Order.asc("fullName")));
    }

    public Page<Client> getAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Client update(Long id, Client client) {
        Client existing = getById(id);
        existing.setFullName(client.getFullName());
        existing.setBirthDate(client.getBirthDate());
        return clientRepository.save(existing);
    }
}
