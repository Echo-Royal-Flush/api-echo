package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.entity.Team;
import ages.pucrs.hackathon.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAll() {
        return teamService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable UUID id) {
        return teamService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Team> create(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.create(team));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@PathVariable UUID id, @RequestBody Team team) {
        return ResponseEntity.ok(teamService.update(id, team));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}