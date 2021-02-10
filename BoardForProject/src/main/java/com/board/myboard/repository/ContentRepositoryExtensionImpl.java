package com.board.myboard.repository;

import com.board.myboard.domain.Content;
import com.board.myboard.domain.QContent;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ContentRepositoryExtensionImpl extends QuerydslRepositorySupport implements ContentRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    public ContentRepositoryExtensionImpl(JPAQueryFactory queryFactory) {
        super(Content.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Content> findByKeyword(String keyword, Pageable pageable) {
        QContent content = QContent.content;

        JPQLQuery<Content> query = from(content).where(content.subject.contains(keyword)
                                        .or(content.text.contains(keyword))
                                        .or(content.account.nickname.contains(keyword)));

        JPQLQuery<Content> pagination = getQuerydsl().applyPagination(pageable,query);
        QueryResults<Content> fetchResults = pagination.fetchResults();

        return new PageImpl<>(fetchResults.getResults(),pageable,fetchResults.getTotal());
    }

    @Override
    public List<Content> findByViewCount() {
        QContent content = QContent.content;

        QueryResults<Content> query = from(content).orderBy(content.viewCount.desc()).limit(3).fetchResults();


        return query.getResults();

    }
}
