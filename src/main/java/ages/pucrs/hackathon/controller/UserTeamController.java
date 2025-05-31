package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.entity.UserTeamEntity;
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
    public List<UserTeamEntity> getAll() {
        return userTeamService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTeamEntity> getById(@PathVariable UUID id) {
        return userTeamService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserTeamEntity> create(@RequestBody UserTeamEntity userTeam) {
        return ResponseEntity.ok(userTeamService.create(userTeam));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserTeamEntity> update(@PathVariable UUID id, @RequestBody UserTeamEntity userTeam) {
        return ResponseEntity.ok(userTeamService.update(id, userTeam));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userTeamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}