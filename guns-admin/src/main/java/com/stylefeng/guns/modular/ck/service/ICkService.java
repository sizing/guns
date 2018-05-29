package com.stylefeng.guns.modular.ck.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Ck;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 出库表 服务类
 * </p>
 *
 * @author lishuai123
 * @since 2018-04-20
 */
public interface ICkService extends IService<Ck> {
    //list
    List<Map<String,Object>> getFzSerch(@Param("page")Page<Object> page, @Param("beginTime")String beginTime, @Param("endTime")  String endTime, @Param("pnameFz") String pnameFz, @Param("traditionalFz")String traditionalFz , @Param("citicFz")String citicFz, @Param("orderByField")String orderByField, @Param("isAsc")boolean isAsc);

//object
    Ck selectByzd(Long fzid);

    List<Map<String,Object>> getFzSerchAll(@Param("beginTime")String beginTime, @Param("endTime")  String endTime, @Param("pnameFz") String pnameFz, @Param("traditionalFz")String traditionalFz);
}
