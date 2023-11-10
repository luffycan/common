package com.luffycan.commonutils.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : luffy
 * @version : 1.0
 * @date : 2023/11/04 10:28
 */
@RestController
@RequestMapping("author")
public class AuthorController {


    @GetMapping("name")
    public String name() {
        return "hello";
    }
}
