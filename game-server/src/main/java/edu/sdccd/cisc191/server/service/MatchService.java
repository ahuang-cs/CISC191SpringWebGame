package edu.sdccd.cisc191.server.service;

import edu.sdccd.cisc191.server.dto.JoinMatchRequest;
import edu.sdccd.cisc191.server.dto.JoinMatchResponse;
import edu.sdccd.cisc191.server.dto.MatchHistoryResponse;
import edu.sdccd.cisc191.server.dto.MatchResultResponse;
import edu.sdccd.cisc191.server.model.Match;
import edu.sdccd.cisc191.server.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MatchService {
    private final Map<String, Match> matches = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public JoinMatchResponse joinMatch(JoinMatchRequest request) {
        Player player = new Player(request.playerName());
        Match match = new Match(player, request.difficulty(), request.ranked());
        matches.put(match.getMatchId(), match);

        return new JoinMatchResponse(
                match.getMatchId(),
                match.getPlayer().getName(),
                match.getOpponent().getName(),
                match.getDifficulty(),
                match.isRanked(),
                "Joined " + match.getMatchType() + " match " + match.getMatchId()
                        + " on " + match.getDifficulty() + " difficulty."
        );
    }

    public MatchResultResponse playMatch(String matchId) {
        Match match = matches.get(matchId);
        if (match == null) {
            throw new IllegalArgumentException("Match not found. Join a match first.");
        }

        if (match.isComplete()) {
            boolean playerWon = match.getWinnerName().equals(match.getPlayer().getName());
            String loser = playerWon ? match.getOpponent().getName() : match.getPlayer().getName();
            return new MatchResultResponse(
                    match.getMatchId(),
                    match.getWinnerName(),
                    loser,
                    playerWon,
                    "Match already completed. Winner: " + match.getWinnerName()
            );
        }

        boolean playerWon = random.nextBoolean();
        String winner = playerWon ? match.getPlayer().getName() : match.getOpponent().getName();
        String loser = playerWon ? match.getOpponent().getName() : match.getPlayer().getName();

        match.completeWithWinner(winner);

        return new MatchResultResponse(
                match.getMatchId(),
                winner,
                loser,
                playerWon,
                "Server result: " + winner + " defeated " + loser
                        + " in a " + match.getMatchType() + " " + match.getDifficulty() + " match."
        );
    }

    public MatchHistoryResponse loadHistory(String playerName) {
        String safeName = new Player(playerName).getName();
        return new MatchHistoryResponse(List.of(
                safeName + " vs Bot: Win",
                safeName + " vs Bot: Loss",
                safeName + " vs Bot: Win"
        ));
    }
}
