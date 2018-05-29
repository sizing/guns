package com.stylefeng.guns.modular.ck.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Ck;
import com.stylefeng.guns.modular.system.dao.CkMapper;
import com.stylefeng.guns.modular.ck.service.ICkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 出库表 服务实现类
 * </p>
 *
 * @author lishuai123
 * @since 2018-04-20
 */
@Service
public class CkServiceImpl extends ServiceImpl<CkMapper, Ck> implements ICkService {


    @Override
    public List<Map<String, Object>> getFzSerch(Page<Object> page, String beginTime, String endTime, String pnameFz, String traditionalFz, String citicFz, String orderByField, boolean isAsc) {
        return this.baseMapper.getFzSerch(page,beginTime,endTime,pnameFz,traditionalFz,citicFz,orderByField,isAsc);
    }

    //终端号查询类
    @Override
    public Ck selectByzd(Long fzid) {
        return  this.baseMapper.selectByzd(fzid);
    }

    @Override
    public List<Map<String, Object>> getFzSerchAll(String beginTime, String endTime, String pnameFz, String traditionalFz) {
        return this.baseMapper.getFzSerchAll( beginTime, endTime, pnameFz, traditionalFz);

    }
}
