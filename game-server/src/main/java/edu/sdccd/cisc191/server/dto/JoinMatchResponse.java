package edu.sdccd.cisc191.server.dto;

public record JoinMatchResponse(
        String matchId,
        String playerName,
        String opponentName,
        String difficulty,
        boolean ranked,
        String message
) {}
