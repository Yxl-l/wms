package com.lemon.service;

import com.lemon.domain.PageResult;
import com.lemon.domain.WarehousingEntryDetailsSort;
import com.lemon.domain.dto.WarehousingEntryDetailsSortDto;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WarehousingEntryDetailsSortService extends IService<WarehousingEntryDetailsSort>{

    PageResult<WarehousingEntryDetailsSort> getPageAll(WarehousingEntryDetailsSortDto warehousingEntryDetailsSortDto);
}
