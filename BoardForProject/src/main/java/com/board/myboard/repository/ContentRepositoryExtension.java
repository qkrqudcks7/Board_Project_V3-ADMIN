package com.board.myboard.repository;

import com.board.myboard.domain.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ContentRepositoryExtension {

    Page<Content> findByKeyword(String keyword, Pageable pageable);

    List<Content> findByViewCount();
}
