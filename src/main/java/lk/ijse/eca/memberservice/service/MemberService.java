package lk.ijse.eca.memberservice.service;

import lk.ijse.eca.memberservice.dto.MemberRequestDTO;
import lk.ijse.eca.memberservice.dto.MemberResponseDTO;

import java.util.List;

public interface MemberService {

    MemberResponseDTO createMember(MemberRequestDTO dto);

    MemberResponseDTO updateMember(String memberId, MemberRequestDTO dto);

    void deleteMember(String memberId);

    MemberResponseDTO getMember(String memberId);

    List<MemberResponseDTO> getAllMembers();
}
