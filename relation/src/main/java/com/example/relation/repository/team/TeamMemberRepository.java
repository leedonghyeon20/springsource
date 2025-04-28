package com.example.relation.repository.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.relation.entity.team.Team;
import com.example.relation.entity.team.TeamMember;
import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    // team 을 기준으로 멤버 찾기
    List<TeamMember> findByTeam(Team team);

    // team 을 기준으로 멤버 찾기 => 멤버, 팀정보 조회
    @Query("Select m,t from TeamMember m join m.team t where t.id = :id")
    List<Object[]> findByMemberEqualTeam(Long id);
}
