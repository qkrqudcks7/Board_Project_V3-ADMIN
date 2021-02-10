package com.board.myboard.controller;

import com.board.myboard.domain.Account;
import com.board.myboard.domain.Content;
import com.board.myboard.repository.AccountRepository;
import com.board.myboard.repository.ContentRepository;
import com.board.myboard.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final ContentRepository contentRepository;

    @GetMapping("/admin/main")
    public String main() {

        return "admin/main";
    }

    @GetMapping("/admin/account")
    public String account(Model model) {

        List<Account> accounts = accountRepository.findAll();

        model.addAttribute("accounts",accounts);

        return "admin/account";
    }

    @GetMapping("/admin/showAccount")
    public String showAccount(@RequestParam("id") Long id,Model model) {

        Account account = accountRepository.findById(id).get();
        model.addAttribute("account",account);
        model.addAttribute("id",id);

        return "admin/showAccount";
    }

    @PostMapping("/admin/showAccount")
    public String showAccountModify(@RequestParam("id") Long id,
                                    @ModelAttribute Account account){
        accountService.saveAdmin(account,id);
        String url="redirect:/admin/showAccount?id="+id;
        return url;
    }

    @GetMapping("/admin/board")
    public String board(Model model) {
        List<Content> contents = contentRepository.findAll();
        model.addAttribute("contents",contents);

        return "admin/board";
    }

    @GetMapping("/admin/delete")
    public String deleteAccount(@RequestParam("id") Long id,
                         RedirectAttributes attributes){
        accountRepository.deleteById(id);
        attributes.addFlashAttribute("message","계정을 삭제했습니다");
        return "redirect:/admin/account";
    }

    @GetMapping("/admin/deleteBoard")
    public String deleteBoard(@RequestParam("id") Long id,
                         RedirectAttributes attributes){
        contentRepository.deleteById(id);
        attributes.addFlashAttribute("message","게시글을 삭제했습니다");
        return "redirect:/admin/board";
    }
}
