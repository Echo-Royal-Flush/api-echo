package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.CompanyEntity;
import ages.pucrs.hackathon.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyEntity> listAll() {
        return companyRepository.findAll();
    }

    public Optional<CompanyEntity> findById(UUID id) {
        return companyRepository.findById(id);
    }

    public CompanyEntity create(CompanyEntity company) {
        return companyRepository.save(company);
    }

    public CompanyEntity update(UUID id, CompanyEntity companyData) {
        return companyRepository.findById(id)
                .map(company -> {
                    company.setName(companyData.getName());
                    company.setAdCountry(companyData.getAdCountry());
                    company.setAdState(companyData.getAdState());
                    company.setAdCity(companyData.getAdCity());
                    company.setAdStreet(companyData.getAdStreet());
                    company.setAdNumber(companyData.getAdNumber());
                    company.setAdCep(companyData.getAdCep());
                    return companyRepository.save(company);
                }).orElseThrow(() -> new RuntimeException("Company not found"));
    }

    public void delete(UUID id) {
        companyRepository.deleteById(id);
    }
}