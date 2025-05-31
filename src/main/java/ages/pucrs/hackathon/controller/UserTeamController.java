package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.entity.UserTeam;
import ages.pucrs.hackathon.service.UserTeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-teams")
public class UserTeamController {

    private final UserTeamService userTeamService;
    public UserTeamController(UserTeamService userTeamService) {
        this.userTeamService = userTeamService;
    }

    @GetMapping
    public List<UserTeam> getAll() {
        return userTeamService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTeam> getById(@PathVariable UUID id) {
        return userTeamService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserTeam> create(@RequestBody UserTeam userTeam) {
        return ResponseEntity.ok(userTeamService.create(userTeam));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserTeam> update(@PathVariable UUID id, @RequestBody UserTeam userTeam) {
        return ResponseEntity.ok(userTeamService.update(id, userTeam));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userTeamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}