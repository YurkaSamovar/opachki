package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.database.repository.UserRepository;
import org.example.dto.QPredicates;
import org.example.dto.UserCreatEditDto;
import org.example.dto.UserDto;
import org.example.dto.UserFilter;
import org.example.service.mapper.UserCreateEditMapper;
import org.example.service.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.example.database.entity.QUser.user;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public Page<UserDto> findAll(UserFilter filter, Pageable pageable) {
        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();

        return userRepository.findAll(predicate, pageable)
                .map(userMapper::map);
    }

    public List<UserDto> findAll(UserFilter filter) {
        return userRepository.findAllByFilter(filter).stream()
                .map(userMapper::map)
                .toList();
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::map)
                .toList();
    }

    public Optional<UserDto> findById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::map);
    }

    @Transactional
    public UserDto create(UserCreatEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserDto> update(Integer id, UserCreatEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
