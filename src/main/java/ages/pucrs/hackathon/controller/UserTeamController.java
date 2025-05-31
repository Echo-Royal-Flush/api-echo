package ages.pucrs.hackathon.controller;

import ages.pucrs.hackathon.entity.TeamEntity;
import ages.pucrs.hackathon.entity.UserEntity;
import ages.pucrs.hackathon.entity.UserTeamEntity;
import ages.pucrs.hackathon.service.UserTeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/{id}/users")
    public ResponseEntity<List<Map<String, Object>>> getUsersByTeamId(@PathVariable UUID id) {
        List<UserEntity> users = userTeamService.findUsersByTeamId(id);
        List<Map<String, Object>> response = users.stream().map(user -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("name", user.getName());
            return map;
        }).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/teams")
    public ResponseEntity<List<Map<String, Object>>> getTeamsByUser(@PathVariable UUID userId) {
        List<TeamEntity> teams = userTeamService.findTeamsByUserId(userId);
        List<Map<String, Object>> response = teams.stream().map(team -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", team.getId());
            map.put("name", team.getName());
            return map;
        }).toList();

        return ResponseEntity.ok(response);
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