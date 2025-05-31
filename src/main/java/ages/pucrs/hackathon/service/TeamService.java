package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.entity.TeamEntity;
import ages.pucrs.hackathon.entity.UserEntity;
import ages.pucrs.hackathon.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.*;

@Service
public class TeamService {

    @Value("${teams.token}")
    private String token;
    private final TeamRepository teamRepository;
    private final WebClient webClient;

    public TeamService(TeamRepository teamRepository, WebClient.Builder webClientBuilder) {
        this.teamRepository = teamRepository;
        this.webClient = webClientBuilder.baseUrl("https://graph.microsoft.com/beta").build();
    }

    public List<TeamEntity> listAll() {
        return teamRepository.findAll();
    }

    public Optional<TeamEntity> findById(UUID id) {
        return teamRepository.findById(id);
    }

    public TeamEntity create(TeamEntity team) {
        return teamRepository.save(team);
    }

    public TeamEntity update(UUID id, TeamEntity teamData) {
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

    @Async
    public void triggerTeam(UUID teamId) {
        List<UserEntity> usersToReport = teamRepository.findAllUsersByTeamId(teamId);
        for (UserEntity user : usersToReport) {


        }
        String chatId = "19:d9ddd12b-750e-4173-8ebe-1fed14e8e280_f5feea6a-117c-4c06-bcca-784739b7a23b@unq.gbl.spaces";
        System.out.println(token);
        String message = "Não esqueça de dar seu feedback para a hackthona. https://main.d17mfmedg0vka5.amplifyapp.com/";

        String response = sendMessageToChat(chatId, message, token);
    }

    @Async
    public void triggerUser(UUID userId) {
        String chatId = "19:d9ddd12b-750e-4173-8ebe-1fed14e8e280_f5feea6a-117c-4c06-bcca-784739b7a23b@unq.gbl.spaces";
        System.out.println(token);
        String message = "Não esqueça de dar seu feedback para a hackthona. https://main.d17mfmedg0vka5.amplifyapp.com/";
        sendMessageToChat(chatId, message, token);
    }

    public String sendMessageToChat(String chatId, String messageContent, String bearerToken) {
        Map<String, Object> body = new HashMap<>();
        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("content", messageContent);
        body.put("body", contentMap);

        return webClient.post()
                .uri("/chats/{chatId}/messages", chatId)
                .header("Authorization", bearerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}