package com.oasis.acquiesce.domain;

import lombok.Data;

@Data
public class Attach {

    private Long ano;
    private Long bno;
    private String uuid;
    private String fileName;

    public String getFullName() {
        if (ano == null) {
            return null;
        }
        return uuid + "_" + fileName;
    }
}
