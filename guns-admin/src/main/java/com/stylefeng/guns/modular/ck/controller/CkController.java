package com.stylefeng.guns.modular.ck.controller;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.constant.dictmap.UserDict;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.util.PoiUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.splitFz.controller.FzController;
import com.stylefeng.guns.modular.splitFz.service.IFzService;
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
import com.stylefeng.guns.modular.system.model.Ck;
import com.stylefeng.guns.modular.ck.service.ICkService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.stylefeng.guns.modular.splitFz.controller.FzController.fileOutTOol;

/**
 * 出库操作控制器
 *
 * @author fengshuonan
 * @Date 2018-04-20 16:11:26
 */
@Controller
@RequestMapping("/ck")
public class CkController extends BaseController {

    private String PREFIX = "/system/ck/";

    @Autowired
    private ICkService ckService;
    @Autowired
    private IFzService iFzService;

    /**
     * 跳转到出库操作首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ck.html";
    }


    /**
     * 添加出库操作
     */
    @RequestMapping("/ck_market")
    public String ckAdd() {
       /* System.out.println(fzId+"<<<<<<");
        model.addAttribute("fzId", fzId);
        LogObjectHolder.me().set(fzId);*/
        return PREFIX + "fz_market.html";
    }


    /**
     * 跳转到修改出库操作
     */
    @RequestMapping("/ck_update/{ckId}")
    public String ckUpdate(@PathVariable Integer ckId, Model model) {
        Ck ck = ckService.selectById(ckId);
        model.addAttribute("item", ck);
        LogObjectHolder.me().set(ck);
        return PREFIX + "ck_edit.html";
    }

    /**
     * 获取出库操作列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String pnameFz, @RequestParam(required = false) String citicFz, @RequestParam(required = false) String traditionalFz) {
        Page<Object> page = new PageFactory<Object>().defaultPage();
        List<Map<String, Object>> result = ckService.getFzSerch(page, beginTime, endTime, pnameFz, traditionalFz, citicFz, page.getOrderByField(), page.isAsc());

        List<Object> list = (List<Object>) new FzWarpper(result).warp();
        page.setRecords((List<Object>) new FzWarpper(result).warp());


        return super.packForBT(page);


    }

    /**
     * 新增出库操作
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Ck ck) {
        System.out.println(ck.toString());
        ckService.insert(ck);
        return SUCCESS_TIP;
    }

    /**
     * 删除出库操作
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        ckService.deleteById(Long.parseLong(id));
        return SUCCESS_TIP;
    }

    /**
     * 修改出库操作
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Ck ck) {
        ckService.updateById(ck);
        return SUCCESS_TIP;
    }



    /**
     * 修改出库操作
     */
    @RequestMapping(value = "/fileOut")
    public void fileOut(HttpServletResponse response, HttpServletRequest request, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String pnameFz, @RequestParam(required = false) String traditionalFz) throws Exception {

        List<Map<String, Object>> Gzsbs = ckService.getFzSerchAll(beginTime, endTime, pnameFz, traditionalFz);
        fileOutTOol(response, request, Gzsbs);

    }



    /**
     * 出库操作详情
     */
    @RequestMapping(value = "/detail/{ckId}")
    @ResponseBody
    public Object detail(@PathVariable("ckId") Integer ckId) {
        return ckService.selectById(ckId);
    }


    /**
     * 导入页面
     */
    @RequestMapping("/fz_fileIn")
    public String fz_fileIn() {
        return PREFIX + "fz_fileIn.html";
    }


    /**
     * 导入页面
     */
    @RequestMapping("/marketFileIn")
    public String ck_file() {
        return PREFIX + "market_fileIn.html";
    }

    /**
     * @param file
     * @return
     */

    @RequestMapping(value = "/market_fileIn")
    @ResponseBody
    public Object market_fileIn(MultipartFile file, Map<String, String> assMap1) {
        try {
            List<Map<String, Object>> fileMap = PoiUtil.readExcel(file, assMap1);
            List<Fz> fzList = iFzService.selectList(null);

            for (Map<String, Object> map : fileMap
                    ) {
                Ck ck = new Ck();
                for (Fz fz : fzList) {

                    if (map.get("终端号")!=null&& map.get("终端号").equals(fz.getTraditionalFz())) {
                        if (map.get("发票类型").toString().equals("收据")) {
                            ck.setcBillType(1);
                        } else if (map.get("发票类型").toString().equals("普票")) {
                            ck.setcBillType(2);
                        } else {
                            ck.setcBillType(3);
                        }
                        ck.setcTime(map.get("日期")==null?"":map.get("日期").toString());
                        ck.setcModel(map.get("出库机型")==null?"":map.get("出库机型").toString());
                        ck.setcName(map.get("出库人")==null?"":map.get("出库人").toString());
                        ck.setcDutyNo(map.get("税号")==null?"":map.get("税号").toString());
                        ck.setcBank(map.get("开户行")==null?"":map.get("开户行").toString());
                        ck.setcNo(map.get("账号")==null?"":map.get("账号").toString());
                        ck.setcAddre(map.get("地址")==null?"":map.get("地址").toString());
                        ck.setcPhone(map.get("电话")==null?"0":map.get("电话").toString());
                        ck.setFzId(fz.getIdFz().intValue());
                        Ck ck1 = ckService.selectByzd(fz.getIdFz());
                        if (ToolUtil.isEmpty(ck1)) {
                         ckService.insert(ck);
                        } else {
                        System.out.println("have been");
                        }

                    }

                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return SUCCESS_TIP;
    }

}
