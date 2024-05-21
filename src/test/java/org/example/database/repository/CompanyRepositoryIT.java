package org.example.database.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.annotation.IT;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class CompanyRepositoryIT {

    private final Integer APPLE_ID = 3;
    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void checkByQuerys() {
        var apple = companyRepository.findByName("Apple");
        var companies = companyRepository.findAllByNameContainingIgnoreCase("le");
        assertTrue(apple.isPresent());
        assertTrue(companies.size() > 1);
    }


    @Test
    void delete() {
        var maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(entity -> companyRepository.delete(entity));
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

}