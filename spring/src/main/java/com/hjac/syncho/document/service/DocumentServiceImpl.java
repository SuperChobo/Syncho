package com.hjac.syncho.document.service;

import com.hjac.syncho.document.mapper.DocumentMapper;
import com.hjac.syncho.document.model.DocMember;
import com.hjac.syncho.document.model.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentMapper documentMapper) {
        this.documentMapper = documentMapper;
    }

    @Override
    public List<Document> findMyDocument(DocMember docMember) {
        return documentMapper.findMyDocument(docMember);
    }

    @Override
    public int myDocumentCount(DocMember docMember) {
        return documentMapper.myDocumentCount(docMember);
    }

    @Override
    public int createDocument(Document document) {
        return documentMapper.createDocument(document);
    }

    @Override
    public int createMember(DocMember docMember) {
        return documentMapper.createMember(docMember);
    }

    @Override
    public Document showDocument(String docNo) {
        return documentMapper.showDocument(docNo);
    }

    @Override
    public List<DocMember> showDocumentMember(String docNo) {
        return documentMapper.showDocumentMember(docNo);
    }

    @Override
    public String checkAuthority(String userId) {
        return documentMapper.checkAuthority(userId);
    }

    @Override
    public int changeMemberRole(DocMember docMember) {
        return documentMapper.changeMemberRole(docMember);
    }

    @Override
    public int deleteMember(DocMember docMember) {
        return documentMapper.deleteMember(docMember);
    }

    @Override
    public int deleteAllMember(String docNo) {
        return documentMapper.deleteAllMember(docNo);
    }

    @Override
    public int deleteDocument(String docNo) {
        return documentMapper.deleteDocument(docNo);
    }
}
