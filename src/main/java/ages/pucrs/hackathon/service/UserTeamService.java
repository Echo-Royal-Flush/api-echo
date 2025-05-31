package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.UserEntity;
import ages.pucrs.hackathon.entity.UserTeamEntity;
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

    public List<UserEntity> findUsersByTeamId(UUID teamId) {
        List<UserTeamEntity> userTeams = userTeamRepository.findByTeam_Id(teamId);
        return userTeams.stream()
                .map(UserTeamEntity::getUser)
                .toList();
    }

    public List<UserTeamEntity> listAll() {
        return userTeamRepository.findAll();
    }

    public Optional<UserTeamEntity> findById(UUID id) {
        return userTeamRepository.findById(id);
    }

    public UserTeamEntity create(UserTeamEntity userTeam) {
        return userTeamRepository.save(userTeam);
    }

    public UserTeamEntity update(UUID id, UserTeamEntity userTeamData) {
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