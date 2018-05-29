package com.stylefeng.guns.modular.splitFz.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Fz;
import com.stylefeng.guns.modular.system.dao.FzMapper;
import com.stylefeng.guns.modular.splitFz.service.IFzService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.model.OperationLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分账表 服务实现类
 * </p>
 *
 * @author ls123
 * @since 2018-04-18
 */
@Service
public class FzServiceImpl extends ServiceImpl<FzMapper, Fz> implements IFzService {
    @Override
    public List<Map<String, Object>> getFzSerch(Page<Fz> page, String beginTime, String endTime, String pnameFz, String traditionalFz, String nameFz ,String pakFz,String orderByField, boolean isAsc) {
        return this.baseMapper.getFzSerch(page, beginTime, endTime, pnameFz, traditionalFz,nameFz,pakFz, orderByField, isAsc);
    }

    @Override
    public List<Map<String, Object>> selectAll() {
        return this.baseMapper.selectAll();
    }

    @Override
    public Fz selectByGetZd(String traditionalFz) {
        return this.baseMapper.selectByGetZd(traditionalFz);
    }


    @Override
    public List<Map<String, Object>> getFzSerchAll(String beginTime, String endTime, String pnameFz, String traditionalFz, String nameFz, String pakFz) {
        return this.baseMapper.getFzSerchAll( beginTime, endTime, pnameFz, traditionalFz,nameFz,pakFz);

    }

    @Override
    public List<Map<String, Object>> getFzSerchAll_fileOut(String beginTime, String endTime, String pnameFz, String traditionalFz, String nameFz, String pakFz) {
        return this.baseMapper.getFzSerchAll_fileOut( beginTime, endTime, pnameFz, traditionalFz,nameFz,pakFz);
    }

}
