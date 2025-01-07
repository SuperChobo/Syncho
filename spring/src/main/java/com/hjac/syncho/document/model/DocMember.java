package com.hjac.syncho.document.model;

public class DocMember {

    private String memberNo;
    private String docNo;
    private String userId;
    private String joinDate;
    private String memberRole;
    private String lastUpdate;

    public DocMember() {
        super();
    }

    public DocMember(String memberNo, String docNo, String userId, String joinDate, String memberRole, String lastUpdate) {
        this.memberNo = memberNo;
        this.docNo = docNo;
        this.userId = userId;
        this.joinDate = joinDate;
        this.memberRole = memberRole;
        this.lastUpdate = lastUpdate;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "DocMember{" +
                "memberNo='" + memberNo + '\'' +
                ", docNo='" + docNo + '\'' +
                ", userId='" + userId + '\'' +
                ", joinDate='" + joinDate + '\'' +
                ", memberRole='" + memberRole + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }

}
