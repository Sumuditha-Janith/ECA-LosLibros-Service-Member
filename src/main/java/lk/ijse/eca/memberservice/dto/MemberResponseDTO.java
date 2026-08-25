package lk.ijse.eca.memberservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class MemberResponseDTO {

    private String memberId;
    private String fullName;
    private String email;
    private String phone;
    private String membershipType;
    private String joinedDate;
}
