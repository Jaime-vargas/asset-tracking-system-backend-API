package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.ClientDTO;
import com.control_activos.sks.control_activos.models.dto.branchDTO.BranchTableDTO;
import com.control_activos.sks.control_activos.models.dto.clientDTO.ClientTableDTO;
import com.control_activos.sks.control_activos.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientTableDTO>> getAllClientTableDTO() {
        List<ClientTableDTO> clients = clientService.getAllClientTableDTO();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{clientId}/branches")
    public ResponseEntity<List<BranchTableDTO>> getAllBranchTableDTOByClientId(@PathVariable Long clientId) {
        List<BranchTableDTO> branches = clientService.getAllBranchTableDTOByClientId(clientId);
        return ResponseEntity.ok().body(branches);
    }

    // #TODO: check endpoints below this comment
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO createdClient = clientService.saveClient(clientDTO);
        return ResponseEntity.ok(createdClient);
    }
    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long clientId, @RequestBody ClientDTO clientDTO) {
        ClientDTO updatedClient = clientService.editClient(clientId, clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

}
