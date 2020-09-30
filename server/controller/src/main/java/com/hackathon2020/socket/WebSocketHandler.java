package com.hackathon2020.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon2020.dao.MeetingDao;
import com.hackathon2020.domain.Meeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MeetingDao meetingDao;

    private Map<String, List<WebSocketSession>> activeSessionsByGroup = new ConcurrentHashMap<>();
//    private final List<WebSocketSession> activeSessions = new CopyOnWriteArrayList<>();
    private final Map<String, List<WebSocketSession>> activeSessions = new ConcurrentHashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        Message incomingMessage = parseMessage(message);
        if (incomingMessage != null) {
            log.info("message {}", incomingMessage);
            switch (incomingMessage.getCommand()) {
                case SUBSCRIBE: {
                    log.info("SUBSCRIBED");
//                    activeSessions.computeIfAbsent(session.getId(), (k) -> new ArrayList<>())
//                            .add(session);
                    log.info("session {} added to list", session.getId());
                    sendList(incomingMessage.getMessage(), session);
                    break;
                }
                case SEND: {

                    break;
                }
                case UNSUBSCRIBE: {

                    break;
                }
                case PING: {
                    Message msg = new Message();
                    msg.setCommand(Command.PONG);
                    sendMessage(session, msg);
                    break;
                }
                default: {
                    throw new IllegalArgumentException(String.format("Command %s is not supported", incomingMessage.getCommand()));
                }
            }
        }

    }

    public void sendList(String login, WebSocketSession session) {
        log.info("Send list");
        Message msg = new Message();
        msg.setCommand(Command.LIST);
        List<Meeting> meetings = meetingDao
                .getActiveMeetingsByEmployeeLogin(login);
        try {
            msg.setMessage(objectMapper.writeValueAsString(meetings));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (session == null) {
            log.info("Send list to all, amount of sessions = {}", activeSessions.size());
            activeSessions.forEach((sid, s)  -> s.forEach(ses -> sendMessage(ses, msg)));
        } else {
            log.info("Send list to session");
            sendMessage(session, msg);
        }
    }

    public void sendCreateMeeting(Meeting meeting) {
        Message message = new Message();
        message.setCommand(Command.NEW_MEETING);
        try {
            message.setMessage(objectMapper.writeValueAsString(meeting));
        } catch (JsonProcessingException e) {
            log.error("Error ", e);
        }
        activeSessions.forEach((sid, s)  -> s.forEach(ses -> sendMessage(ses, message)));
    }

    private Message parseMessage(TextMessage message) {
        try {
            return objectMapper.readValue(message.getPayload(), Message.class);
        } catch (IOException e) {
            log.error("Cannot parse web socket message= {}. Exception: {}", message.getPayloadLength(), e);
        }
        return null;
    }

    private void sendMessage(WebSocketSession webSocketSession, Message message) {
        try {
            if (webSocketSession.isOpen()) {
                log.info("session {} is opened", webSocketSession.getId());
                webSocketSession.sendMessage(new TextMessage(
                        Objects.requireNonNull(serializeMessage(message))));
            } else {
                log.info("session {} is closed", webSocketSession.getId());
            }
        } catch (Exception e) {
            log.error("Error while sending message {}. Exception: {}", message, e);
        }
    }

    private String serializeMessage(Object message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (IOException e) {
            log.error("Cannot send message to application: {}. Exception: {}", message, e);
        }
        return null;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //the messages will be broadcasted to all users.
        activeSessions.computeIfAbsent(session.getId(), (k) -> new ArrayList<>())
                .add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("after connection closed is called");
//        if (activeSessions.containsKey(session.getId())) {
//            activeSessions.get(session.getId()).remove(session);
//        }
//        super.afterConnectionClosed(session, status);
    }
}
