package com.hackathon2020.client;

import com.hackathon2020.dao.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping(value = "/client/groups")
public class GroupController {

    @Autowired
    private GroupDao groupDao;

    @GetMapping
    public ResponseEntity<?> getGroups() {
        return new ResponseEntity<>(groupDao.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getGroupById(@PathVariable String id) {
        return new ResponseEntity<>(groupDao.getById(id), HttpStatus.OK);
    }
}
