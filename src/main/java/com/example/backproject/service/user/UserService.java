package com.example.backproject.service.user;

import com.example.backproject.domain.user.User;
import com.example.backproject.domain.user.UserJpaRepo;
import com.example.backproject.dto.user.UserRequestDto;
import com.example.backproject.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private UserJpaRepo userJpaRepo;

    @Transactional
    public Long save(UserRequestDto userDto) {
        User saved = userJpaRepo.save(userDto.toEntity());
        return saved.getUserId();
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userJpaRepo.findById(id)
                .orElseThrow(CUserNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto findByEmail(String email) {
        User user = userJpaRepo.findByEmail(email)
                .orElseThrow(CUserNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAllUser() {
        return userJpaRepo.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, UserRequestDto userRequestDto) {
        User modifiedUser = userJpaRepo
                .findById(id).orElseThrow(CUserNotFoundException::new);
        modifiedUser.updateNickName(userRequestDto.getNickName());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        userJpaRepo.deleteById(id);
    }
}
