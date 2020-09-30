package com.hackathon2020.client;

import com.hackathon2020.dao.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping(value = "/client/services")
public class ServiceController {

    @Autowired
    private ServiceDao serviceDao;

    @GetMapping()
    public ResponseEntity<?> getServices() {
        return new ResponseEntity<>(serviceDao.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(serviceDao.getById(id), HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/groups/{groupId}")
    public ResponseEntity<?> getByGroupId(@PathVariable String groupId) {
        return new ResponseEntity<>(serviceDao.getByGroupId(groupId), HttpStatus.OK);
    }

}
