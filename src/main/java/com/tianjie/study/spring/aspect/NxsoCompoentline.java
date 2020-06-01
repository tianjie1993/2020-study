package com.tianjie.study.spring.aspect;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 子件信息
 *
 * @author tian.jie
 * @version 1.0
 * @email tian.jie@rongzer.com
 * @date 2020/5/28 15:00
 */
@Data
public class NxsoCompoentline {

    private String itemcode	;
    private String preitemcode	;
    private String mfg	;
    private BigDecimal usageqty	;

    private BigDecimal discountrate	;

    private BigDecimal finalprice	;

    private BigDecimal price	;

    private BigDecimal percentage	;

}
