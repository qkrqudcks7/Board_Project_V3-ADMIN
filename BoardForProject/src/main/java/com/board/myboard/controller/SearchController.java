package com.board.myboard.controller;

import com.board.myboard.domain.Content;
import com.board.myboard.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final ContentRepository contentRepository;

    @GetMapping("/search")
    public String search(String keyword, Model model,
                         @PageableDefault(size = 10) Pageable pageable){

        Page<Content> contentList = contentRepository.findByKeyword(keyword, pageable);

        model.addAttribute("contentList",contentList);
        model.addAttribute(keyword);
        model.addAttribute("maxPage",10);
        model.addAttribute("check",0);

        return "board/searchResult";
    }
}
