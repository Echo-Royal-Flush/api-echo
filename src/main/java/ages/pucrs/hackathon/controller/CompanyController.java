package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.entity.CompanyEntity;
import ages.pucrs.hackathon.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyEntity> getAll() {
        return companyService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyEntity> getById(@PathVariable UUID id) {
        return companyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CompanyEntity> create(@RequestBody CompanyEntity company) {
        return ResponseEntity.ok(companyService.create(company));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyEntity> update(@PathVariable UUID id, @RequestBody CompanyEntity company) {
        return ResponseEntity.ok(companyService.update(id, company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}