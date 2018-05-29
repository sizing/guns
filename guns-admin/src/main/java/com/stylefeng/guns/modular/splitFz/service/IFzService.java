package com.stylefeng.guns.modular.splitFz.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Fz;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分账表 服务类
 * </p>
 *
 * @author ls123
 * @since 2018-04-18
 */
public interface IFzService extends IService<Fz> {
    List<Map<String,Object>> getFzSerch(@Param("page")Page<Fz> page, @Param("beginTime")String beginTime, @Param("endTime")  String endTime, @Param("pnameFz") String pnameFz, @Param("traditionalFz")String traditionalFz, @Param("nameFz") String nameFz, @Param("pakFz")String pakFz, @Param("orderByField")String orderByField, @Param("isAsc")boolean isAsc);

    List<Map<String, Object>> selectAll();

    Fz selectByGetZd(String traditionalFz);

    List<Map<String,Object>> getFzSerchAll(@Param("beginTime")String beginTime, @Param("endTime")  String endTime, @Param("pnameFz") String pnameFz, @Param("traditionalFz")String traditionalFz, @Param("nameFz") String nameFz, @Param("pakFz")String pakFz);


    List<Map<String,Object>> getFzSerchAll_fileOut(@Param("beginTime")String beginTime, @Param("endTime")  String endTime, @Param("pnameFz") String pnameFz, @Param("traditionalFz")String traditionalFz, @Param("nameFz") String nameFz, @Param("pakFz")String pakFz);

}
