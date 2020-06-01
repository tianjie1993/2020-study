package com.tianjie.study.spring.aspect;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * 批发价实体
 *
 * @author tian.jie
 * @version 1.0
 * @email tian.jie@rongzer.com
 * @date 2020/5/28 14:53
 */
@Data
public class NxsopriceDto {
    private Integer code;
    private String message;
    private String itemcode;
    private String businessdate;
    private String customercode;
    private String issku;
    private List<NxsoCompoentline> componentline;
    private String mfg;
    private BigDecimal discountrate;
    private BigDecimal finalprice;
    private BigDecimal price;
}
