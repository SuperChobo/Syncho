package com.hjac.syncho.document.model;

public class Document {

    // 문서 번호
    private String docNo;
    // 문서 이름
    private String docName;
    // 문서 내용
    private String docDetail;
    // 문서 생성 날짜
    private String docDate;
    // 문서 마지막 수정 날짜
    private String lastUpdated;
    // 문서 마지막 수정한 사람 ID
    private String lastMemberId;
    // 상위 문서 번호
    private String parentNo;

    public Document() {
        super();
    }

    public Document(String docNo, String docName, String docDetail, String docDate, String lastUpdated, String lastMemberId, String parentNo) {
        super();
        this.docNo = docNo;
        this.docName = docName;
        this.docDetail = docDetail;
        this.docDate = docDate;
        this.lastUpdated = lastUpdated;
        this.lastMemberId = lastMemberId;
        this.parentNo = parentNo;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocDetail() {
        return docDetail;
    }

    public void setDocDetail(String docDetail) {
        this.docDetail = docDetail;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastMemberId() {
        return lastMemberId;
    }

    public void setLastMemberId(String lastMemberId) {
        this.lastMemberId = lastMemberId;
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }

    @Override
    public String toString() {
        return "Document{" +
                "docNo='" + docNo + '\'' +
                ", docName='" + docName + '\'' +
                ", docDetail='" + docDetail + '\'' +
                ", docDate='" + docDate + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", lastMemberId='" + lastMemberId + '\'' +
                ", parentNo='" + parentNo + '\'' +
                '}';
    }

}