package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.entity.ServiceEntity;
import ages.pucrs.hackathon.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<ServiceEntity> getAll() {
        return serviceService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getById(@PathVariable UUID id) {
        return serviceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServiceEntity> create(@RequestBody ServiceEntity service) {
        return ResponseEntity.ok(serviceService.create(service));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntity> update(@PathVariable UUID id, @RequestBody ServiceEntity service) {
        return ResponseEntity.ok(serviceService.update(id, service));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        serviceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}