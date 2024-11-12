package com.oasis.acquiesce.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

    private int startPage;  // 시작페이지 번호
    private int endPage;    // 화면에서 마지막 페이지 번호
    private boolean prev, next; // 이전, 다음 페이지

    private int total;
    private Criteria cri;

    public PageDTO(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        this.endPage = (int) (Math.ceil (cri.getPageNum() / 10.0) * 10);
        this.startPage = this.endPage - 9;

        // total 로 만들어 지는 진짜 마지막 페이지 번호 예) total = 123 -> 11 ~ 13, 13이 진짜 realEnd 페이지
        int realEnd = (int) (Math.ceil ((total * 1.0) / cri.getAmount()));

        if (realEnd <= endPage) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}
