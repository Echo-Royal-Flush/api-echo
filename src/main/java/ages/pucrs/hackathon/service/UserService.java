package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.User;
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

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(UUID id, User userData) {
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