package org.example.service.mapper;

import org.example.database.entity.Company;
import org.example.dto.CompanyDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements Mapper<Company, CompanyDto> {

    @Override
    public CompanyDto map(Company from) {
        return new CompanyDto(
                from.getId(),
                from.getName()
        ) ;
    }
}
