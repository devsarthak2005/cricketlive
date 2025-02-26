package com.myproject.cricketlivescore.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.cricketlivescore.model.Match;
import com.myproject.cricketlivescore.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@RequiredArgsConstructor
public class LiveScoreHandler extends TextWebSocketHandler {

    private final MatchService matchService;
    private final ObjectMapper objectMapper;
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        Long matchId = Long.parseLong(message.getPayload());
        Optional<Match> match = matchService.getMatchById(matchId);

        if (match.isPresent()) {
            String matchData = objectMapper.writeValueAsString(match);
            session.sendMessage(new TextMessage(matchData));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session);
    }

    public void sendLiveScoreUpdate(Match match) throws IOException {
        String matchData = objectMapper.writeValueAsString(match);
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(matchData));
            }
        }
    }
}
