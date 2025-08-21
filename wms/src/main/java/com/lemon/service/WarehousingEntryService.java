package com.lemon.service;

import com.lemon.domain.PageResult;
import com.lemon.domain.WarehousingEntry;
import com.lemon.domain.dto.WarehousingEntryDto;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WarehousingEntryService extends IService<WarehousingEntry>{

    PageResult<WarehousingEntry> getPageAll(WarehousingEntryDto warehousingEntryDto);
}
