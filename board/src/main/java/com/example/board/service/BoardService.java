package com.example.board.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    public void insert() {

    }

    public void read() {

    }

    public void readAll() {

    }

    public void modify() {

    }

    public void remove() {

    }

}
