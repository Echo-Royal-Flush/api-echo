package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.entity.TeamEntity;
import ages.pucrs.hackathon.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.accepted;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamEntity> getAll() {
        return teamService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamEntity> getById(@PathVariable UUID id) {
        return teamService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TeamEntity> create(@RequestBody TeamEntity team) {
        return ResponseEntity.ok(teamService.create(team));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamEntity> update(@PathVariable UUID id, @RequestBody TeamEntity team) {
        return ResponseEntity.ok(teamService.update(id, team));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("trigger")
    public ResponseEntity<String> triggerTeamFeedbackEvent(@RequestBody UUID teamId) {
        teamService.triggerTeam(teamId);
        return ResponseEntity.accepted().body("Disparado");
    }

}