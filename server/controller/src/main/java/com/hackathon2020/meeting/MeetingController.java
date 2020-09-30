package com.hackathon2020.meeting;

import com.hackathon2020.client.MeetingResponse;
import com.hackathon2020.dao.MeetingDao;
import com.hackathon2020.dao.ServiceDao;
import com.hackathon2020.dao.UserDao;
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
@RequestMapping(value = "/meeting/services/")
public class MeetingController {

    @Autowired
    private MeetingDao meetingDao;

    @PostMapping(value = "/{meetingId}")
    public ResponseEntity<MeetingResponse> getMeeting(@PathVariable String meetingId) {
        if (meetingId.equals("1") && meetingDao.getAll().size() != 0) {
            meetingId = meetingDao.getAll().get(0).getId();
        }

        Meeting meeting = meetingDao.getById(meetingId);
        MeetingResponse meetingResponse = new MeetingResponse();
        meetingResponse.setServiceName(meeting.getService().getName());
        meetingResponse.setClientLogin(meeting.getClient().getLogin());
        meetingResponse.setEmployeeLogin(meeting.getEmployee().getLogin());

        return ResponseEntity.ok(meetingResponse);
    }
}
