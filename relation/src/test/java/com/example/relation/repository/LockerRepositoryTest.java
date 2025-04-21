package com.example.relation.repository;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.relation.entity.sports.Locker;
import com.example.relation.entity.sports.SportsMember;
import com.example.relation.entity.team.TeamMember;
import com.example.relation.repository.sports.LockerRepository;
import com.example.relation.repository.sports.SportsMemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class LockerRepositoryTest {
    @Autowired
    private LockerRepository lockerRepository;
    @Autowired
    private SportsMemberRepository sportsMemberRepository;

    // 단방향 (SportsMember => Locker)
    @Test
    public void testInsert() {
        // Locker 생성
        IntStream.range(1, 6).forEach(i -> {
            Locker locker = Locker.builder().name("locker" + i).build();
            lockerRepository.save(locker);
        });

        // 스포츠 회원 생성
        LongStream.range(1, 6).forEach(i -> {
            SportsMember sportsMember = SportsMember.builder()
                    .locker(Locker.builder().id(i).build())
                    .name("memeber" + i)
                    .build();
            sportsMemberRepository.save(sportsMember);
        });
    }

    // 개별조회
    @Test
    public void testRead1() {
        System.out.println(lockerRepository.findById(1L).get());
        System.out.println(sportsMemberRepository.findById(1L).get());
    }

    @Transactional
    @Test
    public void testRead2() {
        SportsMember sportsMember = sportsMemberRepository.findById(1L).get();

        System.out.println(sportsMember);
        System.out.println(sportsMember.getLocker());
    }

    @Test
    public void testUpdate() {
        // 3번회원의 이름을 홍길동으로 변환
        SportsMember sportsMember = sportsMemberRepository.findById(3L).get();
        sportsMember.setName("홍길동");

        sportsMemberRepository.save(sportsMember);

    }

    @Test
    public void testDelete() {
        // 5번회원 삭제
        sportsMemberRepository.deleteById(5L);
    }

    @Test
    public void testDelete2() {
        // 4번 locker 삭제
        SportsMember sportsMember = sportsMemberRepository.findById(4L).get();
        Locker locker = lockerRepository.findById(5L).get();
        sportsMember.setLocker(locker);
        sportsMemberRepository.save(sportsMember);

        lockerRepository.deleteById(4L);

    }

    // -------------------------------
    // locker => sportsMember 접근
    // -------------------------------

    @Test
    public void testRead3() {
        Locker locker = lockerRepository.findById(1L).get();

        System.out.println(locker);
        System.out.println(locker.getSportsMember());
    }
}
