package com.oasis.acquiesce.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardVO {

    private Long bno;
    private String title;
    private String content;
    private String writer;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    private boolean delFlag;

    // 파일 업로드 추가
    private List<Attach> attachList;
}
