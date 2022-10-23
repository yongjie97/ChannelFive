package com.channelfive.easyuni.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.channelfive.easyuni.constants.ExceptionMessages;
import com.channelfive.easyuni.entities.Account;
import com.channelfive.easyuni.entities.Discussion;
import com.channelfive.easyuni.entities.DiscussionReply;
import com.channelfive.easyuni.exceptions.AccountNotFoundException;
import com.channelfive.easyuni.exceptions.DiscussionNotFoundException;
import com.channelfive.easyuni.services.repositories.AccountRepository;
import com.channelfive.easyuni.services.repositories.DiscussionReplyRepository;
import com.channelfive.easyuni.services.repositories.DiscussionRepository;
import com.channelfive.easyuni.validations.DiscussionForm;
import com.channelfive.easyuni.validations.DiscussionReplyForm;

@Service
public class DiscussionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private DiscussionReplyRepository discussionReplyRepository;

    public Discussion retrieveDiscussion(String id) throws DiscussionNotFoundException {
        Discussion discussion = discussionRepository.findById(id)
            .orElseThrow(() -> new DiscussionNotFoundException(ExceptionMessages.DIS_NF_MSG));
        
        discussion.setViewCount(discussion.getViewCount().intValue() + 1);
        discussionRepository.save(discussion);
        
        return discussion;
    }

    public List<Discussion> retrieveDiscussions(int page, int size) {
        List<Discussion> discussions = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);
        Page<Discussion> pageDiscussions = discussionRepository.findAllByOrderByDateDesc(paging);
        discussions = pageDiscussions.getContent();
        return discussions;
    }

    public Discussion createDiscussion(String email, DiscussionForm discussionForm) throws AccountNotFoundException {
        Account account = accountRepository.findByEmail(email)
        .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACC_NF_MSG));
        
        Discussion discussion = new Discussion();
        discussion.setAuthor(account);
        discussion.setTitle(discussionForm.getTitle());
        discussion.setMessage(discussionForm.getMessage());
        discussionRepository.save(discussion);
        return discussion;
    }

    public void createDiscussionReply(String email, String discussionId, DiscussionReplyForm discussionReplyForm) throws AccountNotFoundException {
        Account account = accountRepository.findByEmail(email)
        .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACC_NF_MSG));

        Discussion discussion = discussionRepository.findById(discussionId)
            .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.DIS_NF_MSG));
        List<DiscussionReply> replies;

        if (discussion.getReplies() == null) {
            replies = new ArrayList<>();
            discussion.setReplies(replies);
        } else {
            replies = discussion.getReplies();
        }

        DiscussionReply reply = new DiscussionReply();
        reply.setMessage(discussionReplyForm.getMessage());
        reply.setAuthor(account);
        reply.setDiscussionId(discussionId);
        replies.add(reply);
        discussionReplyRepository.save(reply);
        discussionRepository.save(discussion);
    }
    
}
