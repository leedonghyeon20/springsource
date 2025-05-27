package com.example.novels.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.novels.entity.Genre;
import com.example.novels.entity.Grade;
import com.example.novels.entity.Member;
import com.example.novels.entity.Novel;

@SpringBootTest
public class NovelRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private NovelRepository novelRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void getNovelTest() {
        Object[] result = novelRepository.getNovelById(62L);
        // System.out.println(Arrays.toString(result));
        // [Novel(id=62, title=The Catcher in the Rye, author=J.D. Salinger,
        // publishedDate=1951-07-16, available=true), Genre(id=1, name=Fiction), 4.0]

        Novel novel = (Novel) result[0];
        Genre genre = (Genre) result[1];
        Double avgGrade = (Double) result[2];
        System.out.println(novel);
        System.out.println(genre);
        System.out.println(avgGrade);
        // Novel(id=62, title=The Catcher in the Rye, author=J.D. Salinger,
        // publishedDate=1951-07-16, available=true)
        // Genre(id=1, name=Fiction)
        // 4.0
    }

    @Test
    public void getNovelListTest() {

        Pageable pageable = PageRequest.of(1, 10, Sort.by("id").descending());

        Page<Object[]> result = novelRepository.list(pageable);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
    }

    // user 50 삽입
    @Test
    public void insertMemberTest() {

        IntStream.rangeClosed(1, 50).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@gmail.com")
                    .pw("1111")
                    .nickname("USER" + i)
                    .build();

            memberRepository.save(member);
        });
    }

    // grade 200 삽입
    @Test
    public void insertGradeTest() {

        IntStream.rangeClosed(1, 200).forEach(i -> {

            // novel id 생성
            long nid = (long) (Math.random() * 66) + 1;

            int rating = (int) (Math.random() * 5) + 1;

            // user id 생성
            int uid = (int) (Math.random() * 50) + 1;

            Grade grade = Grade.builder()
                    .member(Member.builder().email("user" + uid + "@gmail.com").build())
                    .novel(Novel.builder().id(nid).build())
                    .rating(rating)
                    .build();

            gradeRepository.save(grade);
        });
    }

}
