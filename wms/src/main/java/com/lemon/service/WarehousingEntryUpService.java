package com.lemon.service;

import com.lemon.domain.PageResult;
import com.lemon.domain.WarehousingEntryUp;
import com.lemon.domain.dto.WarehousingEntryUpDto;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WarehousingEntryUpService extends IService<WarehousingEntryUp>{

    PageResult<WarehousingEntryUp> getPageAll(WarehousingEntryUpDto warehousingEntryUpDto);
}
