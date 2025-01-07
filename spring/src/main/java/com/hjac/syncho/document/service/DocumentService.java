package com.hjac.syncho.document.service;

import com.hjac.syncho.document.model.DocMember;
import com.hjac.syncho.document.model.Document;

import java.util.List;

public interface DocumentService {

    // 내가 소속되어 있는 문서 찾기
    List<Document> findMyDocument(DocMember docMember);

    // 내가 소속되어 있는 문서 수 리턴
    int myDocumentCount(DocMember docMember);

    // 문서 생성하기
    int createDocument(Document document);

    // 멤버 생성하기
    int createMember(DocMember docMember);

    // 선택한 문서 출력하기
    Document showDocument(String docNo);

    // 선택한 문서의 그룹원 출력하기
    List<DocMember> showDocumentMember(String docNo);

    // 문서 권한 확인
    String checkAuthority(String userId);

    // 권한 변경
    int changeMemberRole(DocMember docMember);

    // 문서 탈퇴
    int deleteMember(DocMember docMember);

    // 문서원 전체 탈퇴
    int deleteAllMember(String docNo);

    // 문서 삭제
    int deleteDocument(String docNo);

}
