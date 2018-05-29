package com.stylefeng.guns.modular.finance.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Finance;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 财务添加 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-04-23
 */
public interface IFinanceService extends IService<Finance> {

    List<Map<String,Object>> getFzSerch(@Param("page")Page<Object> page, @Param("beginTime1")String beginTime1, @Param("endTime")  String endTime, @Param("pnameFz") String pnameFz, @Param("traditionalFz")String traditionalFz , @Param("orderByField")String orderByField, @Param("isAsc")boolean isAsc);

    List<Map<String,Object>> selectAll(@Param("beginTime")String beginTime, @Param("endTime")  String endTime, @Param("pnameFz") String pnameFz, @Param("traditionalFz")String traditionalFz);

    Finance selectByzd(Long idFz);
}
