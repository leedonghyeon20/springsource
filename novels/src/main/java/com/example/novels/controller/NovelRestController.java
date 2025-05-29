package com.example.novels.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.novels.dto.NovelDTO;
import com.example.novels.dto.PageRequestDTO;
import com.example.novels.dto.PageResultDTO;
import com.example.novels.service.NovelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@RequestMapping("/api/books")
@RestController
@RequiredArgsConstructor
public class NovelRestController {

    private final NovelService novelService;

    // 입력 : http://localhost:8080/api/books/add + POST

    // 수정 : http://localhost:8080/api/books/1 + PUT
    @PutMapping("/{id}")
    public Long putNovel(@RequestBody NovelDTO novelDTO) {
        log.info("수정 {}", novelDTO);

        return novelService.avaUpdate(novelDTO);

    }

    @PutMapping("/edit/{id}")
    public Long putPubNovel(@RequestBody NovelDTO novelDTO) {
        log.info("출판일자 수정 {}", novelDTO);

        return novelService.pubUpdate(novelDTO);

    }

    @PostMapping("/add")
    public Long postNovel(@RequestBody NovelDTO novelDTO) {
        log.info("추가 {} ", novelDTO);

        return novelService.novelInsert(novelDTO);

    }

    // 삭제 : http://localhost:8080/api/books/1 + DELETE
    @DeleteMapping("/{id}")
    public Long removeNovel(@PathVariable Long id) {
        log.info("영화 삭제 {} ");

        novelService.novelRemove(id);

        return id;
    }

    // 전체리스트
    // 전체조회 : http://localhost:8080/api/books + GET
    @GetMapping("")
    public PageResultDTO<NovelDTO> getList(PageRequestDTO requestDTO) {
        log.info("전체 도서 정보 {}", requestDTO);
        PageResultDTO<NovelDTO> result = novelService.getList(requestDTO);
        return result;

    }

    // 하나조회 : http://localhost:8080/api/books/1 + GET
    @GetMapping("/{id}")
    public NovelDTO getRow(@PathVariable Long id) {
        log.info("도서 get {} ", id);
        NovelDTO novelDTO = novelService.getRow(id);
        return novelDTO;
    }

}
