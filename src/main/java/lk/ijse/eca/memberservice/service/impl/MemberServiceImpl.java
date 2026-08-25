package lk.ijse.eca.memberservice.service.impl;

import lk.ijse.eca.memberservice.dto.MemberRequestDTO;
import lk.ijse.eca.memberservice.dto.MemberResponseDTO;
import lk.ijse.eca.memberservice.entity.Member;
import lk.ijse.eca.memberservice.exception.DuplicateMemberException;
import lk.ijse.eca.memberservice.exception.MemberNotFoundException;
import lk.ijse.eca.memberservice.mapper.MemberMapper;
import lk.ijse.eca.memberservice.repository.MemberRepository;
import lk.ijse.eca.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public MemberResponseDTO createMember(MemberRequestDTO dto) {
        log.debug("Creating member with ID: {}", dto.getMemberId());

        if (memberRepository.existsById(dto.getMemberId())) {
            log.warn("Duplicate member ID detected: {}", dto.getMemberId());
            throw new DuplicateMemberException(dto.getMemberId());
        }

        Member member = memberMapper.toEntity(dto);
        member = memberRepository.save(member);
        log.info("Member created successfully: {}", dto.getMemberId());
        return memberMapper.toResponseDto(member);
    }

    @Override
    public MemberResponseDTO updateMember(String memberId, MemberRequestDTO dto) {
        log.debug("Updating member with ID: {}", memberId);

        Member existing = memberRepository.findById(memberId)
                .orElseThrow(() -> {
                    log.warn("Member not found for update: {}", memberId);
                    return new MemberNotFoundException(memberId);
                });

        memberMapper.updateEntity(dto, existing);
        Member updated = memberRepository.save(existing);
        log.info("Member updated successfully: {}", memberId);
        return memberMapper.toResponseDto(updated);
    }

    @Override
    public void deleteMember(String memberId) {
        log.debug("Deleting member with ID: {}", memberId);

        if (!memberRepository.existsById(memberId)) {
            log.warn("Member not found for deletion: {}", memberId);
            throw new MemberNotFoundException(memberId);
        }

        memberRepository.deleteById(memberId);
        log.info("Member deleted successfully: {}", memberId);
    }

    @Override
    public MemberResponseDTO getMember(String memberId) {
        log.debug("Fetching member with ID: {}", memberId);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> {
                    log.warn("Member not found: {}", memberId);
                    return new MemberNotFoundException(memberId);
                });
        return memberMapper.toResponseDto(member);
    }

    @Override
    public List<MemberResponseDTO> getAllMembers() {
        log.debug("Fetching all members");
        List<MemberResponseDTO> members = memberRepository.findAll()
                .stream()
                .map(memberMapper::toResponseDto)
                .collect(Collectors.toList());
        log.debug("Fetched {} members", members.size());
        return members;
    }
}
