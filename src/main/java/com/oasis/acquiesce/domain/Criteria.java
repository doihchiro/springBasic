package com.oasis.acquiesce.domain;

import lombok.Data;

@Data
public class Criteria {

    private int pageNum = 1;
    // limit offset, amount
    private int amount = 10;

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

    // offset
    public int getSkip() {
        return (pageNum - 1) * amount;
    }
}
