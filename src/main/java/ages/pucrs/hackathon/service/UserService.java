package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.UserEntity;
import ages.pucrs.hackathon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> listAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(UUID id) {
        return userRepository.findById(id);
    }

    public UserEntity create(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity update(UUID id, UserEntity userData) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userData.getName());
                    user.setRole(userData.getRole());
                    user.setPosition(userData.getPosition());
                    user.setEmail(userData.getEmail());
                    user.setPassword(userData.getPassword());
                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}