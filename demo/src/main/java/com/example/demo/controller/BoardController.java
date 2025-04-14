package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/board")
@Controller

public class BoardController {
    // http://localhost:8080/
    @GetMapping("/create")
    public void getCreater() {
        // return "./board/create";
    }

    @GetMapping("/list")
    public void getList() {
        // return "./board/list";
    }

    @GetMapping("/read")
    public void getRead() {
        // return "./board/read";
    }

    @GetMapping("/update")
    public void getUpdate() {
        // return "./board/update";
    }
}
