package com.ssw.fssw.service;

import com.ssw.fssw.domain.Community;
import com.ssw.fssw.repository.community.CommunityApiRepository;
import com.ssw.fssw.repository.community.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityApiRepository communityApiRepository;
    private final CommunityRepository communityRepository;

    @Transactional
    public void saveCommunity(Community community) {
        communityRepository.save(community);
    }

    @Transactional
    public void updateCommunity(Long id, String title, String contents, String category) {
        Community community = communityRepository.findOne(id);
        community.setTitle(title);
        community.setContents(contents);
        community.setCategory(category);
    }

    public List<Community> communityList() {
        return communityRepository.findAll();
    }

    public Community findOne(Long id) {
        return communityRepository.findOne(id);
    }

    public void delete(Long id) {
        communityApiRepository.deleteById(id);
    }

//    @Transactional
//    public int countView(Long id) {
//        return communityApiRepository.countView(id);
//    }

}
