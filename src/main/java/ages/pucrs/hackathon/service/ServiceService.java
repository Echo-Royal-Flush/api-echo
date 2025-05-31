package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.ServiceEntity;
import ages.pucrs.hackathon.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceEntity> listAll() {
        return serviceRepository.findAll();
    }

    public Optional<ServiceEntity> findById(UUID id) {
        return serviceRepository.findById(id);
    }

    public ServiceEntity create(ServiceEntity service) {
        return serviceRepository.save(service);
    }

    public ServiceEntity update(UUID id, ServiceEntity serviceData) {
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