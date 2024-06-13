package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.database.entity.Card;
import org.example.database.entity.Role;
import org.example.database.entity.User;
import org.example.database.repository.UserRepository;
import org.example.dto.QPredicates;
import org.example.dto.UserCreatEditDto;
import org.example.dto.UserDto;
import org.example.dto.UserFilter;
import org.example.service.mapper.UserCreateEditMapper;
import org.example.service.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.example.database.entity.QUser.user;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserCreateEditMapper userCreateEditMapper;
    private final ImageService imageService;

    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::map)
                .orElseThrow();
    }

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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword("{bcrypt}" + passwordEncoder.encode(userDto.getPassword()));
        userDto.setRole(Role.USER);

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

    public Optional<byte[]> findAvatar(Integer id) {
        return userRepository.findById(id)
                .map(User::getAvatar)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrive user: " + username));
    }
}
