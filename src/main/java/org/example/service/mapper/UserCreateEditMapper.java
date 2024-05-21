package org.example.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.database.entity.Company;
import org.example.database.entity.User;
import org.example.database.repository.CompanyRepository;
import org.example.dto.UserCreatEditDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreatEditDto, User> {

    private final CompanyRepository companyRepository;

    public User map(UserCreatEditDto from, User to) {
        copy(from, to);
        return to;
    }

    @Override
    public User map(UserCreatEditDto from) {
        User user = new User();
        copy(from, user);
        return user;
    }

    private void copy(UserCreatEditDto from, User user) {
        user.setUsername(from.getUsername());
        user.setFirstname(from.getFirstname());
        user.setLastname(from.getLastname());
        user.setBirthDate(from.getBirthDate());
        user.setRole(from.getRole());
        user.setCompany(getCompany(from.getCompanyId()));
    }

    private Company getCompany(Integer companyId) {
        return Optional.ofNullable(companyId)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }
}
