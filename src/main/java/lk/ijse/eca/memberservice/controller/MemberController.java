package lk.ijse.eca.memberservice.controller;

import jakarta.validation.Valid;
import lk.ijse.eca.memberservice.dto.MemberRequestDTO;
import lk.ijse.eca.memberservice.dto.MemberResponseDTO;
import lk.ijse.eca.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDTO> createMember(@Valid @RequestBody MemberRequestDTO dto) {
        log.info("POST /api/v1/members - Member ID: {}", dto.getMemberId());
        MemberResponseDTO response = memberService.createMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponseDTO> updateMember(
            @PathVariable String memberId, @Valid @RequestBody MemberRequestDTO dto) {
        log.info("PUT /api/v1/members/{}", memberId);
        MemberResponseDTO response = memberService.updateMember(memberId, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberId) {
        log.info("DELETE /api/v1/members/{}", memberId);
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDTO> getMember(@PathVariable String memberId) {
        log.info("GET /api/v1/members/{}", memberId);
        MemberResponseDTO response = memberService.getMember(memberId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getAllMembers() {
        log.info("GET /api/v1/members");
        List<MemberResponseDTO> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }
}
