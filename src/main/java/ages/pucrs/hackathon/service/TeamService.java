package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.Team;
import ages.pucrs.hackathon.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> listAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> findById(UUID id) {
        return teamRepository.findById(id);
    }

    public Team create(Team team) {
        return teamRepository.save(team);
    }

    public Team update(UUID id, Team teamData) {
        return teamRepository.findById(id)
                .map(team -> {
                    team.setName(teamData.getName());
                    team.setLength(teamData.getLength());
                    team.setService(teamData.getService());
                    team.setCompany(teamData.getCompany());
                    return teamRepository.save(team);
                }).orElseThrow(() -> new RuntimeException("Team not found"));
    }

    public void delete(UUID id) {
        teamRepository.deleteById(id);
    }
}