package com.oasis.acquiesce.domain;

import lombok.Data;

@Data
public class Criteria {

    private int pageNum = 1;
    // limit offset, amount
    // offset: 건너뛸 행의 수를 의미
    // amount: 가져올 행의 수를 의미
    // 예를 들어, LIMIT 5, 10은 앞에서 5개의 행을 건너뛰고, 그 다음 10개의 행을 가져온다.
    private int amount = 10;

    // 동적 쿼리 추가
    // null, T, C, W, TC, TW, TCW
    private String[] types;
    private String keyword;

    private String typeStr;

    public void setTypes(String[] types) {
        this.types = types;

        if (types != null && types.length > 0) {
            this.typeStr = String.join("", types);
        }
    }

    public void setPageNum(int pageNum) {
        if (pageNum <= 0) {
            this.pageNum = 1;
            return;
        }
        this.pageNum = pageNum;
    }

    public void setAmount(int amount) {
        if (amount <= 10 || amount > 100) {
            this.amount = 10;
            return;
        }
        this.amount = amount;
    }

    // 이 getSkip()의 리턴 값이 #{skip} 에 맵핑 됩
    // limit #{skip}, #{amount}, offset = #{skip}
    // 10개 행을 건너 뛰고 다음 10개 행을 가져옴
    public int getSkip() {
        return (pageNum - 1) * amount;
    }
}
