package com.oasis.acquiesce.mappers;

import com.oasis.acquiesce.domain.Member;
import com.oasis.acquiesce.domain.MemberAuth;

public interface MemberMapper {

    void insertMember(Member member);

    void insertAuth(MemberAuth memberAuth);

    Member selectMember(String uid);
}
