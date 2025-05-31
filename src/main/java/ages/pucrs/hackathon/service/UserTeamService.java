package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.dto.UserTeamRequest;
import ages.pucrs.hackathon.entity.UserEntity;
import ages.pucrs.hackathon.entity.UserTeamEntity;
import ages.pucrs.hackathon.repository.TeamRepository;
import ages.pucrs.hackathon.repository.UserRepository;
import ages.pucrs.hackathon.repository.UserTeamRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ages.pucrs.hackathon.entity.TeamEntity;

@Service
public class UserTeamService {

    private final UserTeamRepository userTeamRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public UserTeamService(UserTeamRepository userTeamRepository, UserRepository userRepository, TeamRepository teamRepository) {
        this.userTeamRepository = userTeamRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    public List<UserEntity> findUsersByTeamId(UUID teamId) {
        return userTeamRepository.findUsersByTeamId(teamId);
    }

    public List<TeamEntity> findTeamsByUserId(UUID userId) {
        return userTeamRepository.findAllByUserId(userId).stream()
                .map(UserTeamEntity::getTeam)
                .toList();
    }


    public List<UserTeamEntity> listAll() {
        return userTeamRepository.findAll();
    }

    public Optional<UserTeamEntity> findById(UUID id) {
        return userTeamRepository.findById(id);
    }

    public UserTeamEntity create(UserTeamRequest userTeamRequest) {
        TeamEntity team = teamRepository.findById(
                UUID.fromString(userTeamRequest.getTeamId())
        ).orElseThrow(() -> new EntityNotFoundException("Team not found"));

        UserEntity user = userRepository.findById(
                UUID.fromString(userTeamRequest.getUserId())
        ).orElseThrow(() -> new EntityNotFoundException("User not found"));

        UserTeamEntity userTeamEntity = new UserTeamEntity(UUID.randomUUID(), user, team);
        return userTeamRepository.save(userTeamEntity);
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