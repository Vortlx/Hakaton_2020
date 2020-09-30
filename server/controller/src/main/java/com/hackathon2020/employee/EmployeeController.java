package com.hackathon2020.employee;

import com.hackathon2020.client.MeetingResponse;
import com.hackathon2020.dao.GroupDao;
import com.hackathon2020.dao.MeetingDao;
import com.hackathon2020.dao.ServiceDao;
import com.hackathon2020.dao.UserDao;
import com.hackathon2020.domain.Group;
import com.hackathon2020.domain.Meeting;
import com.hackathon2020.domain.Service;
import com.hackathon2020.domain.User;
import com.hackathon2020.security.CredentialUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping(value = "/employee/services/")
public class EmployeeController {

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private MeetingDao meetingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CredentialUtils credentialUtils;

    @PostMapping(value = "/{meetingId}/join")
    public ResponseEntity<MeetingResponse> joinMeeting(@PathVariable String meetingId) {
        User user = credentialUtils.getUserInfo();
        if (meetingId.equals("1") && meetingDao.getAll().size() != 0) {
            meetingId = meetingDao.getAll().get(0).getId();
        }
        Meeting meeting = meetingDao.getById(meetingId);
        meeting.setEmployee(user);
        String url = "fishingsite/" + meetingId;
        meeting.setUrl(url);
        meetingDao.update(meeting);
        return ResponseEntity.ok(new MeetingResponse(url));
    }

    @GetMapping(value = "/{meetingId}/joinScheduled")
    public ResponseEntity<String> joinScheduledMeeting(@PathVariable String meetingId, LocalDateTime dateTime) {
        User user = credentialUtils.getUserInfo();
        Service service = serviceDao.getById(meetingId);
        Meeting meeting = new Meeting(UUID.randomUUID().toString(), null,
                user, null, service, LocalDateTime.now());
        meetingDao.save(meeting);
        return ResponseEntity.ok("123");
    }

    @GetMapping(value= "/getAccessibleMeetings")
    @Transactional(timeout = 120)
    public List<Meeting> getAccessibleMeetings(String login) {
        String userId = userDao.getByLogin(login).getId();
        List<Meeting> meetings = meetingDao.getAll();
        return meetings;
    }
}
