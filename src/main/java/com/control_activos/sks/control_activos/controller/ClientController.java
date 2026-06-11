package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.ClientDTO;
import com.control_activos.sks.control_activos.models.dto.PhotoDTO;
import com.control_activos.sks.control_activos.models.dto.branchDTO.BranchTableDTO;
import com.control_activos.sks.control_activos.models.dto.clientDTO.ClientTableDTO;
import com.control_activos.sks.control_activos.services.ClientService;
import com.control_activos.sks.control_activos.services.FilesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;
    private final FilesService filesService;

    public ClientController(ClientService clientService, FilesService filesService) {
        this.clientService = clientService;
        this.filesService = filesService;
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

    @PostMapping("/{clientId}/photo")
    public ResponseEntity<?> addPhoto(@PathVariable Long clientId,
                                      @RequestPart("file")MultipartFile file,
                                      @RequestParam(defaultValue = "false") Boolean replaceExisting) {
        filesService.UploadClientPhoto(clientId, file, replaceExisting);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long clientId, @RequestBody ClientDTO clientDTO) {
        ClientDTO updatedClient = clientService.editClient(clientId, clientDTO);
        return ResponseEntity.ok(updatedClient);
    }


    // #TODO: check endpoints below this comment
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO createdClient = clientService.saveClient(clientDTO);
        return ResponseEntity.ok(createdClient);
    }


}
