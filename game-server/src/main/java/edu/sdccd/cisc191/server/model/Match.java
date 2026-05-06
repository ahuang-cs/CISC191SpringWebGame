package edu.sdccd.cisc191.server.model;

import java.util.UUID;

public class Match {
    private final String matchId;
    private final Player player;
    private final Player opponent;
    private final String difficulty;
    private final boolean ranked;
    private boolean complete;
    private String winnerName = "";

    public Match(Player player, String difficulty, boolean ranked) {
        this.matchId = UUID.randomUUID().toString();
        this.player = player;
        this.difficulty = normalizeDifficulty(difficulty);
        this.ranked = ranked;
        this.opponent = new Player("Bot (" + this.difficulty + ")");
    }

    public String getMatchId() { return matchId; }
    public Player getPlayer() { return player; }
    public Player getOpponent() { return opponent; }
    public String getDifficulty() { return difficulty; }
    public boolean isRanked() { return ranked; }
    public boolean isComplete() { return complete; }
    public String getWinnerName() { return winnerName; }

    public String getMatchType() {
        return ranked ? "ranked" : "casual";
    }

    public void completeWithWinner(String winnerName) {
        this.winnerName = winnerName;
        this.complete = true;
    }

    private String normalizeDifficulty(String value) {
        if (value == null || value.isBlank()) return "Normal";
        return switch (value.trim().toLowerCase()) {
            case "easy" -> "Easy";
            case "hard" -> "Hard";
            default -> "Normal";
        };
    }
}
