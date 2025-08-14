package com.lemon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDto {
    /**
     * 商品标题、name
     */
  private String title;
    /**
     * 条码
     */
    private  String code;
    /**
     * 分类id
     */
    private Integer categoryId;
    private Integer page = 1;
    private Integer pageSize = 10;
  public void setTitle(String title) {
    this.title = "undefined".equals(title) ? null : title;
  }

  public void setCode(String code) {
    this.code = "undefined".equals(code) ? null : code;
  }
}
