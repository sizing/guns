package com.stylefeng.guns.modular.finance.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.util.PoiUtil;
import com.stylefeng.guns.modular.splitFz.controller.FzController;
import com.stylefeng.guns.modular.splitFz.service.IFzService;
import com.stylefeng.guns.modular.system.model.Ck;
import com.stylefeng.guns.modular.system.model.Fz;
import com.stylefeng.guns.modular.system.warpper.FzWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Finance;
import com.stylefeng.guns.modular.finance.service.IFinanceService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.stylefeng.guns.modular.splitFz.controller.FzController.fileOutTOol;

/**
 * 财务添加控制器
 *
 * @author fengshuonan
 * @Date 2018-04-23 10:56:17
 */
@Controller
@RequestMapping("/finance")
public class FinanceController extends BaseController {

    private String PREFIX = "/finance/finance/";

    @Autowired
    private IFinanceService financeService;

    @Autowired
    private IFzService iFzService;

    /**
     * 跳转到财务添加首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "finance.html";
    }

    /**
     * 跳转到添加财务添加
     */
    @RequestMapping("/finance_add")
    public String financeAdd() {
        return PREFIX + "finance_add.html";
    }



    /**
     * 跳转到添加财务添加
     */
    @RequestMapping("/updateHml")
    public String updateHml() {
        return PREFIX + "finance_fileIn.html";
    }



    /**
     * 跳转到添加财务添加
     */
    @RequestMapping("/file_In")
    public String financeIn() {
        return PREFIX + "market_fileIn.html";
    }

    /**
     * 导出市场添加的excel
     */
  /*  @RequestMapping("/file_out")
    public void file_out(HttpServletRequest request, HttpServletResponse response) throws Exception {
     List<Map<String,Object>>  Gzsbs=  financeService.selectAll();
     FzController.fileOutTOol(response,request,Gzsbs);
     }*/
     /**
     * 修改出库操作
     */
    @RequestMapping(value = "/file_out")
    public void file_out(HttpServletResponse response, HttpServletRequest request, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String pnameFz, @RequestParam(required = false) String traditionalFz) throws Exception {

        List<Map<String, Object>> Gzsbs = financeService.selectAll(beginTime, endTime, pnameFz, traditionalFz);
        fileOutTOol(response, request, Gzsbs);

    }
    /**
     * 跳转到修改财务添加
     */
    @RequestMapping("/finance_update/{financeId}")
    public String financeUpdate(@PathVariable Integer financeId, Model model) {
        Finance finance = financeService.selectById(financeId);
        Fz fz=       iFzService.selectById(finance.getFid());
        model.addAttribute("fz",fz);
        model.addAttribute("item",finance);

        LogObjectHolder.me().set(fz);
        LogObjectHolder.me().set(finance);
        return PREFIX + "finance_edit.html";
    }

    /**
     * 获取财务添加列表
     */

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime1, @RequestParam(required = false) String endTime, @RequestParam(required = false) String pnameFz, @RequestParam(required = false) String ckType, @RequestParam(required = false) String traditionalFz) {
        Page<Object> page = new PageFactory<Object>().defaultPage();
        List<Map<String, Object>> result = financeService.getFzSerch(page, beginTime1, endTime, pnameFz,traditionalFz , page.getOrderByField(), page.isAsc());

        List<Object>  list=  (List<Object>) new FzWarpper(result).warp();
        page.setRecords((List<Object>) new FzWarpper(result).warp());


        return  super.packForBT(page);


    }
    /**
     * 新增财务添加
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Finance finance) {
        financeService.insert(finance);
        return SUCCESS_TIP;
    }


    /**
     * 新增财务添加
     */
    @RequestMapping(value = "/fileIn")
    @ResponseBody
    public Object market_fileIn(MultipartFile file, Map<String, String> assMap1,String update) {
        System.out.println("---in------"+update);
        try {
            List<Map<String, Object>> fileMap = PoiUtil.readExcel(file, assMap1);
            List<Fz> fzList = iFzService.selectList(null);
            List<Finance> financeList=new ArrayList<Finance>();
            for (Map<String, Object> map : fileMap
                    ) {
                Finance ck = new Finance();
                for (Fz fz : fzList) {

                    if (map.get("传统终端号") != null && map.get("传统终端号").toString().equals(fz.getTraditionalFz())) {
                        System.out.println(map.get("传统终端号").toString()+"<<<===>>"+fz.getTraditionalFz());
                        if (map.get("是否开发票")!= null &&map.get("是否开发票").toString().equals("否")) {
                           ck.setIsbill(0);
                        } else if (map.get("是否开发票")!= null &&map.get("是否开发票").toString().equals("是")) {
                            ck.setIsbill(1);
                        } else {

                        }
                        if (map.get("是否缴款")!= null &&map.get("是否缴款").toString().equals("否")) {
                            ck.setIsmake(0);
                        } else if (map.get("是否缴款")!= null &&map.get("是否缴款").toString().equals("是")) {
                            ck.setIsmake(1);
                        } else {

                        }
                        ck.setFmarketMoney(Integer.parseInt(map.get("销售款").equals("")?"0":map.get("销售款").toString()));
                        ck.setFmoney(Integer.parseInt(map.get("押金").equals("")?"0":map.get("押金").toString()));
                        ck.setFnameNo(map.get("缴款人员")==null?"":map.get("缴款人员").toString());
                        ck.setFno(map.get("票据编号")==null?"":map.get("票据编号").toString());
                        ck.setJkno(map.get("缴款单号")==null?"":map.get("缴款单号").toString());
                        ck.setFtime(map.get("缴款日期")==null?"":map.get("缴款日期").toString());
                        if (map.get("收款方式")!= null &&map.get("收款方式").toString().equals("pos机")) {
                            ck.setFtype(1);
                        } else if (map.get("收款方式")!= null &&map.get("收款方式").toString().equals("支付宝")) {
                            ck.setFtype(2);
                        } else if (map.get("收款方式")!= null &&map.get("收款方式").toString().equals("微信")) {
                            ck.setFtype(3);
                        } else {

                        }
                        ck.setFphoneMoney(Integer.parseInt(map.get("通讯费").equals("")?"0":map.get("通讯费").toString()));
                        ck.setFid(fz.getIdFz().intValue());
                        Finance fn = financeService.selectByzd(fz.getIdFz());
                        System.out.println(fn+"----"+update);
                        if (fn == null&&update==null) {

                            financeService.insert(ck);
                        }else{
                            if(update!=null){
                                ck.setId(fn.getId());
                                financeList.add(ck);
                                System.out.println("__null");

                            }

                        }

                    }

                }


            }
            if(financeList!=null&&financeList.size()>1){
                financeService.updateBatchById(financeList);

            }else{
                throw new GunsException(BizExceptionEnum.FILE_READING_ERROR);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return SUCCESS_TIP;
    }

    /**
     * 删除财务添加
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        financeService.deleteById(Long.parseLong(id));
        return SUCCESS_TIP;
    }

    /**
     * 修改财务添加
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Finance finance) {
        financeService.updateById(finance);
        return SUCCESS_TIP;
    }

    /**
     * 财务添加详情
     */
    @RequestMapping(value = "/detail/{financeId}")
    @ResponseBody
    public Object detail(@PathVariable("financeId") Integer financeId) {
        return financeService.selectById(financeId);
    }
}
