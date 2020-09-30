package com.hackathon2020.client;

import com.hackathon2020.dao.MeetingDao;
import com.hackathon2020.dao.ServiceDao;
import com.hackathon2020.dao.UserDao;
import com.hackathon2020.domain.Meeting;
import com.hackathon2020.domain.Service;
import com.hackathon2020.domain.User;
import com.hackathon2020.security.CredentialUtils;
import com.hackathon2020.security.jwt.JwtTokenUtil;
import com.hackathon2020.socket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@Controller
@RequestMapping(value = "/client/services/")
public class ClientController {

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private MeetingDao meetingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CredentialUtils credentialUtils;

    @Autowired
    private WebSocketHandler webSocketHandler;

    @PostMapping(value = "/{serviceId}/call")
    public ResponseEntity<?> createNowMeeting(@PathVariable String serviceId) throws InterruptedException {
        User user = credentialUtils.getUserInfo();
        Service service = serviceDao.getById(serviceId);
        Meeting meeting = new Meeting(UUID.randomUUID().toString(), null,
                user, null, service, LocalDateTime.now());
        meetingDao.save(meeting);
        webSocketHandler.sendList(user.getLogin(), null);
        while (meeting.getEmployee() == null) {
            TimeUnit.SECONDS.sleep(3);
            meeting = meetingDao.getById(meeting.getId());
            System.out.println("qqqq");
        }
//        webSocketHandler.sendList(user.getLogin(), null);
        return ResponseEntity.ok(new MeetingResponse(meeting.getUrl()));
    }

    @GetMapping(value = "/{serviceId}/callScheduled")
    public ResponseEntity<String> createScheduledMeeting(@PathVariable String serviceId, LocalDateTime dateTime) {
        User user = credentialUtils.getUserInfo();
        Service service = serviceDao.getById(serviceId);
        Meeting meeting = new Meeting(UUID.randomUUID().toString(), null,
                user, null, service, LocalDateTime.now());
        meetingDao.save(meeting);
        return ResponseEntity.ok("123");
    }

    @GetMapping(value= "/getUserScheduledMeetings")
    @Transactional(timeout = 120)
    public List<Meeting> getUserScheduledMeetings(String login) {
        String userId = userDao.getByLogin(login).getId();
        List<Meeting> meetings = meetingDao.getByUserId(userId);
        return meetings;
    }
}
