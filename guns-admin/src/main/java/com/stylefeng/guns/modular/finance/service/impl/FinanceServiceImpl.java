package com.stylefeng.guns.modular.finance.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Finance;
import com.stylefeng.guns.modular.system.dao.FinanceMapper;
import com.stylefeng.guns.modular.finance.service.IFinanceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 财务添加 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-04-23
 */
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance> implements IFinanceService {


    @Override
    public List<Map<String, Object>> getFzSerch(Page<Object> page, String beginTime1, String endTime, String pnameFz, String traditionalFz, String orderByField, boolean isAsc) {
        return this.baseMapper.getFzSerch(page,beginTime1,endTime,pnameFz,traditionalFz,orderByField,isAsc);

    }

    @Override
    public List<Map<String, Object>> selectAll(String beginTime, String endTime, String pnameFz, String traditionalFz) {
        return this.baseMapper.selectAll( beginTime, endTime, pnameFz, traditionalFz);

    }


    @Override
    public Finance selectByzd(Long idFz) {
        return this.baseMapper.selectByzd(idFz);
    }
}
