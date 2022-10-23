package com.channelfive.easyuni.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.channelfive.easyuni.constants.ApplicationMessages;
import com.channelfive.easyuni.entities.Discussion;
import com.channelfive.easyuni.exceptions.DiscussionNotFoundException;
import com.channelfive.easyuni.payload.JSONResponseBuilder;
import com.channelfive.easyuni.security.jwt.JwtUtils;
import com.channelfive.easyuni.services.DiscussionService;
import com.channelfive.easyuni.validations.DiscussionForm;
import com.channelfive.easyuni.validations.DiscussionReplyForm;

@CrossOrigin(origins = "http://localhost:8081", allowCredentials="true", maxAge = 3600)
@Controller
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/discussion/{id}")
    public ResponseEntity<?> viewDiscussion(@PathVariable String id) {
        try {
            Discussion discussion = discussionService.retrieveDiscussion(id);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, null, discussion));
        } catch (DiscussionNotFoundException e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null));
        }
    }

    @GetMapping("/discussion")
    public ResponseEntity<?> viewDiscussions(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<Discussion> discussions = discussionService.retrieveDiscussions(page, size);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, null, discussions));
        } catch (Exception e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null));
        }
    }

    @PostMapping("/discussion/create")
    public ResponseEntity<?> createDiscussion(HttpServletRequest request, DiscussionForm discussionForm) {
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            Discussion discussion = discussionService.createDiscussion(email, discussionForm);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, ApplicationMessages.DIS_MSG, discussion));
        } catch (Exception e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null));
        }
    }

    @PostMapping("/discussion/{id}/reply")
    public ResponseEntity<?> createDiscussionReply(@PathVariable String id, HttpServletRequest request,
            DiscussionReplyForm discussionReplyForm) {
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            discussionService.createDiscussionReply(email, id, discussionReplyForm);
            return ResponseEntity.ok().body(JSONResponseBuilder.build(true, ApplicationMessages.DIS_REPLY_MSG, null));
        } catch (Exception e) {
            return ResponseEntity.ok().body(JSONResponseBuilder.build(false, e.getMessage(), null));
        }
    }

}
