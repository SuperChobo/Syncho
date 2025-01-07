package com.hjac.syncho.document.controller;

import com.hjac.syncho.document.model.DocMember;
import com.hjac.syncho.document.model.Document;
import com.hjac.syncho.document.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doc")
@Tag(name = "문서 관리 컨트롤러 ", description = "문서 전반적인 내용 관리하는 클래스")
public class DocumentController {

    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // 1. 로그인 -> 소속되어 있는 문서 있는지 확인하기 + 소속되어 있는 문서 목록 출력
    @Operation(summary = "소속되어 있는 문서 반환", description = "문서 멤버 DB에서 내 아이디 찾아 문서 반환하기")
    @GetMapping("/list/{userId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> findMyDocument(@PathVariable("userId") String userId) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        try {
            DocMember member = new DocMember();
            member.setUserId(userId);

            int docCnt = documentService.myDocumentCount(member);

            // 만약 소속된 문서가 있으면
            if (docCnt > 0) {

                resultMap.put("docCount", docCnt);

                // 문서 목록 리턴
                List<Document> doc = documentService.findMyDocument(member);

                resultMap.put("doc", doc);
                status = HttpStatus.OK;

            } else {

                // 없으면 0만 리턴하기
                resultMap.put("docCount", docCnt);
                status = HttpStatus.OK;

                /*
                * 프론트엔드
                * 먼저 docCount 세고 0이 아니라면
                * doc 받아서 출력하기
                * 0이라면 문서 생성 버튼 띄우기
                */

            }
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 2. 문서 생성
    @Operation(summary = "문서 생성", description = "제목, 생성자 ID, 멤버 ID, 상위 문서 번호 받아 문서 생성하기")
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createDocument(@RequestParam(name = "docName", required = true) String docName, @RequestParam(name = "userId", required = true) String userId,
                                                              @RequestParam(name = "parentNo", required = false, defaultValue = "") String parentNo,
                                                              @RequestParam(name = "docMember", required = false) String[] memberId, @RequestParam(name = "docRole", required = false) String[] memberRole) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        try {
            /*
            프론트에서 보낼 데이터 -> json 형태로 보내기
            1. 문서 이름
            2. 문서 생성자 아이디
            3. 문서에 포함할 사람들 -> 배열로 생성
            3 - 1. 문서 포함 사람의 권한 -> 배열로 생성
            4. 만약 상위 문서가 있다면 상위 문서
             */
            Document doc = new Document();
            doc.setDocName(docName);
            if (parentNo != null) {
                doc.setParentNo(parentNo);
            }

            // mapper에서 생성된 docNo의 값을 리턴하여 doc의 docNo에 저장함
            documentService.createDocument(doc);

            // 문서 생성 -> 그룹장 멤버 DB 생성하기
            DocMember member = new DocMember();
            member.setUserId(userId);
            member.setDocNo(doc.getDocNo());
            member.setMemberRole("Owner");

            documentService.createMember(member);

            // 그룹원이 존재한다면
            if (memberId != null && memberId.length > 0) {
                for (int i = 0 ; i < memberId.length ; i++) {
                    DocMember docMember = new DocMember();

                    docMember.setDocNo(doc.getDocNo());
                    docMember.setUserId(memberId[i]);
                    docMember.setMemberRole(memberRole[i]);

                    documentService.createMember(docMember);
                }
            }


        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 흠 -> 로그인 하면 목록 가장 위에 있는 친구 자동 클릭으로 갑시다.
    // 3 - 2. 선택한 문서 출력
    @Operation(summary = "클릭한 문서 출력", description = "문서 DB에서 docNo, 문서원 DB에서 docNo 찾아 반환")
    @GetMapping("/detail/{docNo}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> showDocument(@PathVariable("docNo") String docNo) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        try {

            Document doc = documentService.showDocument(docNo);
            List<DocMember> member = documentService.showDocumentMember(docNo);
            resultMap.put("doc", doc);
            resultMap.put("member", member);
            status = HttpStatus.OK;

        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);

    }

    // 4. 문서에 다른사람 추가하기 (그룹장만 가능)
    @Operation(summary = "문서원 추가", description = "여러개 받아 권한 추가하기")
    @PostMapping("/addMember")
    public ResponseEntity<Map<String, Object>> addDocMember(@RequestParam(name = "userId") String userId,
                                                            @RequestParam(name = "docNo") String docNo,
                                                            @RequestParam(name = "docMember", required = false) String[] memberId,
                                                            @RequestParam(name = "docRole", required = false) String[] memberRole) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        try {
            // 나의 권한 체크
            String autho = documentService.checkAuthority(userId);

            // 만약 그룹장이 아니면 추가 불가
            if (autho.equals("Owner")) {
                for (int i = 0 ; i < memberId.length ; i++) {
                    DocMember member = new DocMember();
                    member.setDocNo(docNo);
                    member.setUserId(memberId[i]);
                    member.setMemberRole(memberRole[i]);

                    documentService.createMember(member);
                }

                resultMap.put("message", "문서원 추가 완료");
            } else {
                resultMap.put("message", "그룹장만 추가가 가능합니다.");
            }

            status = HttpStatus.OK;

        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);

    }

    // 5. 역할 변경하기 -> 셀렉트 박스 체크 감지해서 변경하기 + 그룹장만 가능 나중에 그룹장 체크 넣거나 프론트에서 판단하기
    @Operation(summary = "문서원 역할 변경", description = "역할 변경하기")
    @PostMapping("/changeRole")
    public ResponseEntity<Map<String, Object>> changeMemberRole(@RequestBody DocMember member) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        try {
            documentService.changeMemberRole(member);
            resultMap.put("message", "변경 완료");
            status = HttpStatus.OK;

        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);

    }

    // 6 - 1. 문서 탈퇴
    @Operation(summary = "문서원 탈퇴", description = "문서 탈퇴하기")
    @PostMapping("/exitDoc")
    public ResponseEntity<Map<String, Object>> exitDocument(@RequestBody DocMember member) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        try {

            documentService.deleteMember(member);
            resultMap.put("message", "문서 탈퇴 완료");
            status = HttpStatus.OK;

        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    // 6 - 3. 문서 삭제 (그룹장만 가능)
    @Operation(summary = "문서 삭제", description = "문서원 다 탈퇴시키고 문서 삭제하기")
    @PostMapping("/deleteDoc")
    public ResponseEntity<Map<String, Object>> deleteDocument(@RequestParam(name = "userId", required = true) String userId,
                                                              @RequestParam(name = "docNo", required = true) String docNo) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        try {
            String autho = documentService.checkAuthority(userId);

            if (autho.equals("Owner")) {
                // 문서원 전체 삭제
                documentService.deleteAllMember(docNo);
                // 문서 삭제
                documentService.deleteDocument(docNo);

                resultMap.put("message", "그룹원 탈퇴 및 문서 삭제 완료");
                status = HttpStatus.OK;

            } else {
                resultMap.put("message", "그룹장만 가능합니다.");
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);

    }

}
