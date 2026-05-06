package edu.sdccd.cisc191.server.dto;

public record MatchResultResponse(
        String matchId,
        String winnerName,
        String loserName,
        boolean playerWon,
        String message
) {}
