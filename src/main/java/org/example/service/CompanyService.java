package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.database.repository.CompanyRepository;
import org.example.dto.CompanyDto;
import org.example.service.mapper.CompanyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public List<CompanyDto> findAll() {
        return companyRepository.findAll().stream()
                .map(companyMapper::map)
                .toList();
    }

    public Optional<CompanyDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(companyMapper::map);
    }
}
