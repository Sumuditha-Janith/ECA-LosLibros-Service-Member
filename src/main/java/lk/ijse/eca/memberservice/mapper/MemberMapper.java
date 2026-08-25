package lk.ijse.eca.memberservice.mapper;

import lk.ijse.eca.memberservice.dto.MemberRequestDTO;
import lk.ijse.eca.memberservice.dto.MemberResponseDTO;
import lk.ijse.eca.memberservice.entity.Member;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MemberMapper {

    MemberRequestDTO toDto(Member member);

    MemberResponseDTO toResponseDto(Member member);

    Member toEntity(MemberRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(MemberRequestDTO dto, @MappingTarget Member member);
}
