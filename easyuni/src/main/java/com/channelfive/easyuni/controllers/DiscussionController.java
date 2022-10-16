package com.channelfive.easyuni.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.channelfive.easyuni.constants.ApplicationMessages;
import com.channelfive.easyuni.entities.Discussion;
import com.channelfive.easyuni.exceptions.DiscussionNotFoundException;
import com.channelfive.easyuni.payload.JSONReponseBuilder;
import com.channelfive.easyuni.security.jwt.JwtUtils;
import com.channelfive.easyuni.services.DiscussionService;
import com.channelfive.easyuni.validations.DiscussionForm;
import com.channelfive.easyuni.validations.DiscussionReplyForm;

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
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, null, discussion));
        } catch (DiscussionNotFoundException e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null)); 
        }
    }

    @PostMapping("/discussion/create")
    public ResponseEntity<?> createDiscussion(HttpServletRequest request, DiscussionForm discussionForm) {
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            discussionService.createDiscussion(email, discussionForm);
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, ApplicationMessages.DIS_MSG, null));
        } catch (Exception e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null)); 
        }
    }

    @PostMapping("/discussion/{id}/reply")
    public ResponseEntity<?> createDiscussionReply(@PathVariable String id, HttpServletRequest request, DiscussionReplyForm discussionReplyForm) {
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            String email = jwtUtils.getEmailFromJwtToken(token);
            discussionService.createDiscussionReply(email, id, discussionReplyForm);
            return ResponseEntity.ok().body(JSONReponseBuilder.build(true, ApplicationMessages.DIS_MSG, null));
        } catch (Exception e) {
            return ResponseEntity.ok().body(JSONReponseBuilder.build(false, e.getMessage(), null)); 
        }
    }
    
}
