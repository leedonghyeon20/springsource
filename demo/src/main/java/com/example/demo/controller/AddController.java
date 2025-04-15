package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.CalcDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller

public class AddController {
    // http://localhost:8080/calc => calc.html
    @GetMapping("/calc")
    public void getCalc() {
        log.info("사칙연산 페이지 요청");
    }

    @PostMapping("/calc")
    public String postCalc(CalcDTO calcDTO) {
        log.info("사칙연산 요청 {}", calcDTO);
        int num1 = calcDTO.getNum1();
        int num2 = calcDTO.getNum2();
        String op = calcDTO.getOp();
        int result = 0;
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }

        log.info("식 : {} {} {}", num1, op, num2);
        log.info("값 : {}", result);
        calcDTO.setResult(result);

        return "result";
        // http://localhost:8080/calc + String => templates/result.html
    }

}
