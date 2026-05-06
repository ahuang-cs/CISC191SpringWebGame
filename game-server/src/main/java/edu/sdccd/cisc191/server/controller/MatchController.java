package edu.sdccd.cisc191.server.controller;

import edu.sdccd.cisc191.server.dto.ErrorResponse;
import edu.sdccd.cisc191.server.dto.JoinMatchRequest;
import edu.sdccd.cisc191.server.dto.JoinMatchResponse;
import edu.sdccd.cisc191.server.dto.MatchHistoryResponse;
import edu.sdccd.cisc191.server.dto.MatchResultResponse;
import edu.sdccd.cisc191.server.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = "http://localhost:9091")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public JoinMatchResponse joinMatch(@RequestBody JoinMatchRequest request) {
        return matchService.joinMatch(request);
    }

    @PostMapping("/{matchId}/play")
    public MatchResultResponse playMatch(@PathVariable String matchId) {
        return matchService.playMatch(matchId);
    }

    @GetMapping("/history")
    public MatchHistoryResponse loadHistory(@RequestParam(defaultValue = "Player") String playerName) {
        return matchService.loadHistory(playerName);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(exception.getMessage()));
    }
}
