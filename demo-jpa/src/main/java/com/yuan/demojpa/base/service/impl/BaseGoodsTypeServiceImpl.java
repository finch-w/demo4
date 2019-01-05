package com.yuan.demojpa.base.service.impl;

import com.yuan.demojpa.base.dto.BaseGoodsTypeDto;
import com.yuan.demojpa.base.pojo.BaseAirways;
import com.yuan.demojpa.base.pojo.BaseGoodsType;
import com.yuan.demojpa.base.repository.BaseAirwaysRepository;
import com.yuan.demojpa.base.repository.BaseGoodsTypeRepository;
import com.yuan.demojpa.base.service.BaseGoodsTypeService;
import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BaseGoodsTypeServiceImpl extends BaseServiceImpl<BaseGoodsType, String, BaseGoodsTypeRepository> implements BaseGoodsTypeService {
    private final BaseGoodsTypeRepository baseGoodsTypeRepository;
    private final BaseAirwaysRepository baseAirwaysRepository;

    @Autowired
    public BaseGoodsTypeServiceImpl(BaseGoodsTypeRepository baseGoodsTypeRepository, BaseAirwaysRepository baseAirwaysRepository) {
        this.baseGoodsTypeRepository = baseGoodsTypeRepository;
        this.baseAirwaysRepository = baseAirwaysRepository;
    }

    @Override
    public BaseGoodsTypeRepository getBaseRepository() {
        return baseGoodsTypeRepository;
    }

    public Page<BaseGoodsType> data(BaseGoodsTypeDto dto) {
        return getBaseRepository().findAll(dto, PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Order.desc("createDate")))).map(baseGoodsType -> {
            List<BaseAirways> baseAirways = baseAirwaysRepository.findByIdIn(Arrays.asList(baseGoodsType.getAirways().split(",")));
            StringBuilder stringBuilder = new StringBuilder();
            for (BaseAirways baseAirway : baseAirways) {
                stringBuilder.append(baseAirway.getCnameShort()).append(",");
            }
            baseGoodsType.setAirways(stringBuilder.toString());
            return baseGoodsType;
        });
    }
}
