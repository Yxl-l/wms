package com.lemon.service;

import com.lemon.domain.PageResult;
import com.lemon.domain.WarehousingEntryDetails;
import com.lemon.domain.dto.WarehousingEntryDetailsDto;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WarehousingEntryDetailsService extends IService<WarehousingEntryDetails>{

    PageResult<WarehousingEntryDetails> getPageAll(WarehousingEntryDetailsDto warehousingEntryDetailsDto);
}
