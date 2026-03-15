package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.enums.ResourceNotFoundExceptionEnum;
import com.control_activos.sks.control_activos.exception.ResourceNotFoundException;
import com.control_activos.sks.control_activos.models.dto.ClientDTO;
import com.control_activos.sks.control_activos.mapper.Mapper;
import com.control_activos.sks.control_activos.models.dto.clientDTO.ClientTableDTO;
import com.control_activos.sks.control_activos.models.dto.clientDTO.ClientTableRowDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import com.control_activos.sks.control_activos.models.entity.Client;
import com.control_activos.sks.control_activos.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientTableDTO> getClientTableDTO() {
        List<ClientTableRowDTO> clientRows = clientRepository.getClientTableRows();
        List<ReportCountDTO> reports = clientRepository.getActiveReports();
        Map<Long, List<ReportCountDTO>> reportsByClientId = reports.stream().collect(Collectors.groupingBy(ReportCountDTO::getId));
        return clientRows.stream().map(row -> new ClientTableDTO(
                row.getId(),
                row.getName(),
                row.getBranches(),
                row.getTotalHardware(),
                reportsByClientId.getOrDefault(row.getId(), List.of())
        )).toList();
    }

    @Transactional
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName());
        Client savedClient = clientRepository.save(client);
        return Mapper.entityToDTO(savedClient);
    }

    @Transactional
    public ClientDTO editClient(Long clientId, ClientDTO clientDTO) {
        Client client = findClientById(clientId);
        client.setName(clientDTO.getName());
        Client updatedClient = clientRepository.save(client);
        return Mapper.entityToDTO(updatedClient);
    }

    public Client findClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ResourceNotFoundExceptionEnum.CLIENT_NOT_FOUND.build(clientId)));
    }

}
