package com.yuan.demojpa.base.repository;

import com.yuan.demojpa.base.pojo.BaseDocument;
import com.yuan.demojpa.commons.dao.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseDocumentRepository extends BaseRepository<BaseDocument, String> {
}
