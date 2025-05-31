package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.UserTeam;
import ages.pucrs.hackathon.repository.UserTeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserTeamService {

    private final UserTeamRepository userTeamRepository;

    public UserTeamService(UserTeamRepository userTeamRepository) {
        this.userTeamRepository = userTeamRepository;
    }

    public List<UserTeam> listAll() {
        return userTeamRepository.findAll();
    }

    public Optional<UserTeam> findById(UUID id) {
        return userTeamRepository.findById(id);
    }

    public UserTeam create(UserTeam userTeam) {
        return userTeamRepository.save(userTeam);
    }

    public UserTeam update(UUID id, UserTeam userTeamData) {
        return userTeamRepository.findById(id)
                .map(userTeam -> {
                    userTeam.setUser(userTeamData.getUser());
                    userTeam.setTeam(userTeamData.getTeam());
                    return userTeamRepository.save(userTeam);
                }).orElseThrow(() -> new RuntimeException("UserTeam not found"));
    }

    public void delete(UUID id) {
        userTeamRepository.deleteById(id);
    }
}