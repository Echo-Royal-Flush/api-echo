package ages.pucrs.hackathon.service;

import ages.pucrs.hackathon.dto.TeamRequest;
import ages.pucrs.hackathon.entity.CompanyEntity;
import ages.pucrs.hackathon.entity.TeamEntity;
import ages.pucrs.hackathon.entity.UserEntity;
import ages.pucrs.hackathon.entity.UserTeamEntity;
import ages.pucrs.hackathon.repository.CompanyRepository;
import ages.pucrs.hackathon.repository.TeamRepository;
import ages.pucrs.hackathon.repository.UserRepository;
import ages.pucrs.hackathon.repository.UserTeamRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private final UserTeamRepository userTeamRepository;
    private final UserRepository userRepository;
    private final WebClient webClient;
    private final CompanyRepository companyRepository;

    public TeamService(TeamRepository teamRepository,
                       UserTeamRepository userTeamRepository,
                       UserRepository userRepository,
                       WebClient.Builder webClientBuilder,
                       CompanyRepository companyRepository) {
        this.teamRepository = teamRepository;
        this.userTeamRepository = userTeamRepository;
        this.userRepository = userRepository;
        this.webClient = webClientBuilder.baseUrl("https://graph.microsoft.com/beta").build();
        this.companyRepository = companyRepository;
    }

    public List<TeamEntity> listAll() {
        return teamRepository.findAll();
    }

    public Optional<TeamEntity> findById(UUID id) {
        return teamRepository.findById(id);
    }

    public TeamEntity create(TeamRequest teamRequest) {
        CompanyEntity companyEntity = companyRepository.findById(UUID.fromString(teamRequest.getCompanyId())).orElseThrow(()-> new EntityNotFoundException()); 
        TeamEntity teamEntity = new TeamEntity(UUID.randomUUID(),
                teamRequest.getName(),
                teamRequest.getLength(),
                teamRequest.getService(),
                companyEntity); // necessario buscar por company_id recebido pelo Auth

        teamEntity = teamRepository.save(teamEntity);

        for (String userId : teamRequest.getMembers()) {
            Optional<UserEntity> userOpt = userRepository.findById(UUID.fromString(userId));

            if (userOpt.isPresent()) {
                UserTeamEntity userTeam = new UserTeamEntity(
                        UUID.randomUUID(),
                        userOpt.get(),
                        teamEntity
                );
                userTeamRepository.save(userTeam);
            } else {
                throw new EntityNotFoundException("User not found: " + userId);
            }
        }

        return teamEntity;
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