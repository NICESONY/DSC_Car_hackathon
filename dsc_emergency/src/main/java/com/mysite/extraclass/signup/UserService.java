package com.mysite.extraclass.signup;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.extraclass.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password, String residence) throws DataIntegrityViolationException {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new DataIntegrityViolationException("이미 존재하는 사용자 ID입니다.");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DataIntegrityViolationException("이미 존재하는 이메일입니다.");
        }

        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setResidence(residence);
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(password));

        this.userRepository.save(user);
        return user;
    }

    public List<SiteUser> readList() {
        return userRepository.findAll();
    }

    public SiteUser readDetail(Integer id) {
        Optional<SiteUser> oc = userRepository.findById(id);
        if (oc.isPresent()) {
            return oc.get();
        }
        throw new DataNotFoundException("사용자를 찾을 수 없습니다.");
    }

    public void update(SiteUser siteuser) {
        userRepository.save(siteuser);
    }
}
