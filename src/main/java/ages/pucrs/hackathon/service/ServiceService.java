package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.Service;
import ages.pucrs.hackathon.repository.ServiceRepository;
import org.springframework.stereotype.Service as SpringService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringService
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> listAll() {
        return serviceRepository.findAll();
    }

    public Optional<Service> findById(UUID id) {
        return serviceRepository.findById(id);
    }

    public Service create(Service service) {
        return serviceRepository.save(service);
    }

    public Service update(UUID id, Service serviceData) {
        return serviceRepository.findById(id)
                .map(s -> {
                    s.setName(serviceData.getName());
                    return serviceRepository.save(s);
                }).orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public void delete(UUID id) {
        serviceRepository.deleteById(id);
    }
}