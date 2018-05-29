package com.stylefeng.guns.modular.splitFz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.common.constant.state.BizLogType;
import com.stylefeng.guns.core.util.PoiUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.OperationLog;
import com.stylefeng.guns.modular.system.warpper.DictWarpper;
import com.stylefeng.guns.modular.system.warpper.FzWarpper;
import com.stylefeng.guns.modular.system.warpper.LogWarpper;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.Workbook;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.model.Fz;
import com.stylefeng.guns.modular.splitFz.service.IFzService;
import org.springframework.web.multipart.MultipartFile;
import sun.applet.resources.MsgAppletViewer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.*;

/**
 * 分账管理控制器
 *
 * @author fengshuonan
 * @Date 2018-04-18 16:25:23
 */
@Controller
@RequestMapping("/fz")
public class FzController extends BaseController {

    private String PREFIX = "/splitFz/fz/";

    @Autowired
    private IFzService fzService;



    /**
     * 跳转到分账管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "fz.html";
    }

    /**
     * 跳转到添加分账管理
     */
    @RequestMapping("/fz_add")
    public String fzAdd() {
        return PREFIX + "fz_add.html";
    }


    /**
     * 导入页面
     */
    @RequestMapping("/fz_fileIn")
    public String fz_fileIn() {
        return PREFIX + "fz_fileIn.html";
    }


    /**
     * 批量修改页面
     */
    @RequestMapping("/fz_fileIn_updateAll")
    public String fz_fileIn_updateAll() {
        return PREFIX + "market_fileIn.html";
    }


    /**
     * @param file
     * @return
     */

    @RequestMapping(value = "/fz_fileIn_uplod")
    @ResponseBody
    public Object fz_fileIn_post(MultipartFile file, Map<String, String> assMap1, String up) throws SQLException {

        try {
            List<Map<String, Object>> fileMap = PoiUtil.readExcel(file, assMap1);
            List<Fz>   fzList=new ArrayList<Fz>();
            for (Map<String, Object> map : fileMap
                    ) {
                Fz fz = new Fz();
                for (String s : map.keySet()
                        ) {


                    fz.setBelongFz(map.get("所属").toString());
                    fz.setCheckFz(map.get("提交审核时间").toString().replace("/", "-"));
                    fz.setCiticFz(map.get("中信终端号") == null ? "无" : map.get("中信终端号").toString());
                    fz.setCostFz(map.get("终端购买费").equals("") ? 0 : Integer.parseInt(map.get("终端购买费").toString()));
                    fz.setLeasingFz(map.get("租赁金额").equals("") ? 0 : Integer.parseInt(map.get("租赁金额").toString()));
                    fz.setNoFz(map.get("编号").toString());
                    fz.setNameFz(map.get("发展人").toString());
                    fz.setMerchantFz(map.get("商户编号").toString());
                    fz.setNetWorkFz(map.get("入网模式").toString());
                    fz.setPakFz(map.get("商户简称").toString());
                    fz.setPhoneFz(map.get("通讯费") == null ? "无" : map.get("通讯费").toString());
                    fz.setTraditionalFz(map.get("传统终端号").toString());
                    fz.setZcountFz(Integer.parseInt(map.get("终端数量").toString()));
                    fz.setTypeFz(map.get("机型").toString());
                    fz.setPnameFz(map.get("商户名称").toString());


                }
                if (fz.getTraditionalFz() != null) {
               Fz resluts=   fzService.selectByGetZd(fz.getTraditionalFz());

                    if (resluts== null) {
                        if(up!=null){
                          System.out.println("---------");
                        }else{
                            fzList.add(fz);
                        }
                    } else {
                        if(up!=null) {
                            fz.setIdFz(resluts.getIdFz());
                            fzService.updateById(fz);
                        }
                    }

                }

            }
            if(fzList!=null&&fzList.size()>1){
                System.out.println("导入条数-----"+fzList.size());
                fzService.insertBatch(fzList);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return SUCCESS_TIP;
    }

    /**
     * 跳转到添加财务管理
     */
    @RequestMapping("/fz_finance/{fzId}")
    public String fzFinance(@PathVariable Integer fzId, Model model) {
        System.out.println(fzId + "<=====");
        Fz fz = fzService.selectById(fzId);
        model.addAttribute("fz", fz);
        LogObjectHolder.me().set(fzId);
        return PREFIX + "fz_finance.html";
    }


    /**
     * 跳转到修改分账管理
     */
    @RequestMapping("/fz_update/{fzId}")
    public String fzUpdate(@PathVariable Integer fzId, Model model) {
        System.out.println("fzid==>>" + fzId);
        Fz fz = fzService.selectById(fzId);
        model.addAttribute("item", fz);
        LogObjectHolder.me().set(fz);
        return PREFIX + "fz_edit.html";
    }

    /**
     * 跳转到添加财务管理
     */
    @RequestMapping("/fz_market/{fzId}")
    public String market(@PathVariable Integer fzId, Model model) {
        System.out.println(fzId + "<=====");
        Fz fz = fzService.selectById(fzId);
        model.addAttribute("fz", fz);
        LogObjectHolder.me().set(fzId);
        return PREFIX + "fz_market.html";
    }


    /**
     * fz_fileIn_updateAll
     * 获取分账管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String pnameFz, @RequestParam(required = false) String traditionalFz, @RequestParam(required = false) String nameFz, @RequestParam(required = false) String pakFz) {

        Page<Fz> page = new PageFactory<Fz>().defaultPage();
        List<Map<String, Object>> result = fzService.getFzSerch(page, beginTime, endTime, pnameFz, traditionalFz, nameFz, pakFz, page.getOrderByField(), page.isAsc());

        List<Fz> list = (List<Fz>) new FzWarpper(result).warp();
        page.setRecords((List<Fz>) new FzWarpper(result).warp());


        return super.packForBT(page);
    }

    /**
     * 新增分账管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Fz fz) {
        fzService.insert(fz);
        return SUCCESS_TIP;
    }

    /**
     * 删除分账管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String fzId) {
        fzService.deleteById(Long.parseLong(fzId));
        return SUCCESS_TIP;
    }

    /**
     * 删除分账管理
     */
    @RequestMapping(value = "/deleteAll")
    @ResponseBody
    public Object deleteAll(HttpServletResponse response, HttpServletRequest request, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String pnameFz, @RequestParam(required = false) String traditionalFz, @RequestParam(required = false) String nameFz, @RequestParam(required = false) String pakFz) {
        List<Map<String, Object>> Gzsbs = fzService.getFzSerchAll_fileOut(beginTime, endTime, pnameFz, traditionalFz, nameFz, pakFz);
        for (Map<String, Object> map : Gzsbs) {

               System.out.println(map.get("ID_FZ").toString());
             /*   fzService.deleteById(Long.parseLong(map.get("ID_FZ").toString()));
*/

            fzService.deleteByMap(map);
        }
        return SUCCESS_TIP;
    }


    /**
     * 修改分账管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Fz fz) {
        fzService.updateById(fz);
        return SUCCESS_TIP;
    }


    /**
     * 批量修改分账管理
     */
    @RequestMapping(value = "/updateAll")
    @ResponseBody
    public Object updateAll(Model model) {
        model.addAttribute("update", "update");
        LogObjectHolder.me().set("update");
        return SUCCESS_TIP;
    }


    /**
     * 导出
     */
    @RequestMapping(value = "/fileOut")
    @ResponseBody
    public void fileOut(HttpServletResponse response, HttpServletRequest request, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String pnameFz, @RequestParam(required = false) String traditionalFz, @RequestParam(required = false) String nameFz, @RequestParam(required = false) String pakFz) throws Exception {
        List<Map<String, Object>> Gzsbs = fzService.getFzSerchAll(beginTime, endTime, pnameFz, traditionalFz, nameFz, pakFz);
        fileOutTOol(response, request, Gzsbs);

    }


    /**
     * 分账管理详情
     */
    @RequestMapping(value = "/detail/{fzId}")
    @ResponseBody
    public Object detail(@PathVariable("fzId") Integer fzId) {
        return fzService.selectById(fzId);
    }

    //导出工具
    public static void fileOutTOol(HttpServletResponse response, HttpServletRequest request, List<Map<String, Object>> Gzsbs) throws Exception {
        String fileName = "file";
        response.setContentType("application/msexcel");// 定义输出类型
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        // 执行文件写入
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
        // 获取输出流
        OutputStream outputStream = response.getOutputStream();
        //response.reset();// 清空输出流
        WritableWorkbook wwb = Workbook.createWorkbook(outputStream); // 建立excel文件
        WritableSheet ws = wwb.createSheet("基础信息查询", Gzsbs.size() + 10);
        //    设置单元格的文字格式
        WritableFont wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false,
                UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat wcf = new WritableCellFormat(wf);//字体样式
        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
        //第一行 表头
        //ws.setRowView(1, 500);//设置高度
        ws.addCell(new Label(0, 0, "id", wcf));
        ws.addCell(new Label(1, 0, "编号", wcf));
        ws.addCell(new Label(2, 0, "商户名称", wcf));
        ws.addCell(new Label(3, 0, "发展人", wcf));
        ws.addCell(new Label(4, 0, "所属", wcf));
        ws.addCell(new Label(5, 0, "商户简称", wcf));
        ws.addCell(new Label(6, 0, "终端数量", wcf));
        ws.addCell(new Label(7, 0, "入网模式", wcf));
        ws.addCell(new Label(8, 0, "租赁金额", wcf));
        ws.addCell(new Label(9, 0, "终端购买费", wcf));
        ws.addCell(new Label(10, 0, "通讯费", wcf));
        ws.addCell(new Label(11, 0, "机型", wcf));
        ws.addCell(new Label(12, 0, "提交审核时间", wcf));
        ws.addCell(new Label(13, 0, "商户编号", wcf));
        ws.addCell(new Label(14, 0, "传统终端号", wcf));

        ws.addCell(new Label(15, 0, "中信终端号", wcf));
        //市场
        ws.addCell(new Label(16, 0, "出库人", wcf));
        ws.addCell(new Label(17, 0, "出库时间", wcf));
        ws.addCell(new Label(18, 0, "出库机型", wcf));
        ws.addCell(new Label(19, 0, "开票类型", wcf));

        ws.addCell(new Label(20, 0, "税号", wcf));
        ws.addCell(new Label(21, 0, "账号", wcf));
        ws.addCell(new Label(22, 0, "地址", wcf));
        ws.addCell(new Label(23, 0, "电话", wcf));
        ws.addCell(new Label(24, 0, "开户行", wcf));

        ws.addCell(new Label(25, 0, "缴款人员", wcf));

        ws.addCell(new Label(26, 0, "缴款单号", wcf));
        ws.addCell(new Label(27, 0, "销售款", wcf));
        ws.addCell(new Label(28, 0, "票据编号", wcf));
        ws.addCell(new Label(29, 0, "押金", wcf));
        ws.addCell(new Label(30, 0, "缴款日期", wcf));

        ws.addCell(new Label(31, 0, "收款方式", wcf));
        ws.addCell(new Label(32, 0, "通讯费(财务)", wcf));
        ws.addCell(new Label(33, 0, "是否缴款", wcf));
        ws.addCell(new Label(34, 0, "是否开发票", wcf));

        for (int i = 0; i < Gzsbs.size(); i++) {
            ws.addCell(new Label(0, i + 1, Gzsbs.get(i).get("ID_FZ")==null?"": Gzsbs.get(i).get("ID_FZ").toString(), wcf));
            ws.addCell(new Label(1, i + 1, Gzsbs.get(i).get("NO_FZ")==null?"": Gzsbs.get(i).get("NO_FZ").toString(), wcf));
            ws.addCell(new Label(2, i + 1, Gzsbs.get(i).get("PNAME_FZ")==null?"": Gzsbs.get(i).get("PNAME_FZ").toString(), wcf));
            ws.addCell(new Label(3, i + 1, Gzsbs.get(i).get("NAME_FZ")==null?"": Gzsbs.get(i).get("NAME_FZ").toString(), wcf));
            ws.addCell(new Label(4, i + 1, Gzsbs.get(i).get("BELONG_FZ")==null?"": Gzsbs.get(i).get("BELONG_FZ").toString(), wcf));
            ws.addCell(new Label(5, i + 1, Gzsbs.get(i).get("PAK_FZ")==null?"": Gzsbs.get(i).get("PAK_FZ").toString(), wcf));
            ws.addCell(new Label(6, i + 1, Gzsbs.get(i).get("ZCOUNT_FZ")==null?"": Gzsbs.get(i).get("ZCOUNT_FZ").toString(), wcf));
            ws.addCell(new Label(7, i + 1, Gzsbs.get(i).get("NET_WORK_FZ")==null?"": Gzsbs.get(i).get("NET_WORK_FZ").toString(), wcf));
            ws.addCell(new Label(8, i + 1, Gzsbs.get(i).get("LEASING_FZ")==null?"": Gzsbs.get(i).get("LEASING_FZ").toString(), wcf));
            ws.addCell(new Label(9, i + 1, Gzsbs.get(i).get("COST_FZ")==null?"": Gzsbs.get(i).get("COST_FZ").toString(), wcf));
            ws.addCell(new Label(10, i + 1, Gzsbs.get(i).get("PHONE_FZ")==null?"": Gzsbs.get(i).get("PHONE_FZ").toString(), wcf));

            ws.addCell(new Label(11, i + 1, Gzsbs.get(i).get("TYPE_FZ")==null?"": Gzsbs.get(i).get("TYPE_FZ").toString(), wcf));
            ws.addCell(new Label(12, i + 1, Gzsbs.get(i).get("CHECK_FZ")==null?"": Gzsbs.get(i).get("CHECK_FZ").toString(), wcf));
            ws.addCell(new Label(13, i + 1, Gzsbs.get(i).get("MERCHANT_FZ")==null?"": Gzsbs.get(i).get("MERCHANT_FZ").toString(), wcf));
            ws.addCell(new Label(14, i + 1, Gzsbs.get(i).get("TRADITIONAL_FZ")==null?"": Gzsbs.get(i).get("TRADITIONAL_FZ").toString(), wcf));
            ws.addCell(new Label(15, i + 1, Gzsbs.get(i).get("CITIC_FZ") == null ? "" : Gzsbs.get(i).get("CITIC_FZ").toString(), wcf));

//市场
            ws.addCell(new Label(16, i + 1, Gzsbs.get(i).get("c_name") == null ? "" : Gzsbs.get(i).get("c_name").toString(), wcf));
            ws.addCell(new Label(17, i + 1, Gzsbs.get(i).get("c_time") == null ? "" : Gzsbs.get(i).get("c_time").toString(), wcf));
            ws.addCell(new Label(18, i + 1, Gzsbs.get(i).get("c_model") == null ? "" : Gzsbs.get(i).get("c_model").toString(), wcf));
            if (Integer.parseInt(Gzsbs.get(i).get("c_bill_type") == null ? "0" : Gzsbs.get(i).get("c_bill_type").toString()) == 1) {
                ws.addCell(new Label(19, i + 1, "收据", wcf));
            } else if (Integer.parseInt(Gzsbs.get(i).get("c_bill_type") == null ? "0" : Gzsbs.get(i).get("c_bill_type").toString()) == 2) {
                ws.addCell(new Label(19, i + 1, "普票", wcf));
            } else if (Integer.parseInt(Gzsbs.get(i).get("c_bill_type") == null ? "0" : Gzsbs.get(i).get("c_bill_type").toString()) == 3) {
                ws.addCell(new Label(19, i + 1, "专票", wcf));
            } else {
                ws.addCell(new Label(19, i + 1, "--", wcf));
            }

            ws.addCell(new Label(20, i + 1, Gzsbs.get(i).get("c_duty_no") == null ? "" : Gzsbs.get(i).get("c_duty_no").toString(), wcf));
            ws.addCell(new Label(21, i + 1, Gzsbs.get(i).get("c_no") == null ? "" : Gzsbs.get(i).get("c_no").toString(), wcf));
            ws.addCell(new Label(22, i + 1, Gzsbs.get(i).get("c_addre") == null ? "" : Gzsbs.get(i).get("c_addre").toString(), wcf));
            ws.addCell(new Label(23, i + 1, Gzsbs.get(i).get("c_phone") == null ? "" : Gzsbs.get(i).get("c_phone").toString(), wcf));
            ws.addCell(new Label(24, i + 1, Gzsbs.get(i).get("c_bank") == null ? "" : Gzsbs.get(i).get("c_bank").toString(), wcf));


            //财务fname_no jkno
            ws.addCell(new Label(25, i + 1, Gzsbs.get(i).get("fname_no") == null ? "" : Gzsbs.get(i).get("fname_no").toString(), wcf));
            ws.addCell(new Label(26, i + 1, Gzsbs.get(i).get("jkno") == null ? "" : Gzsbs.get(i).get("jkno").toString(), wcf));
            ws.addCell(new Label(27, i + 1, Gzsbs.get(i).get("fmarket_money") == null ? "0" : Gzsbs.get(i).get("fmarket_money").toString(), wcf));
            ws.addCell(new Label(28, i + 1, Gzsbs.get(i).get("fno") == null ? "" : Gzsbs.get(i).get("fno").toString(), wcf));
            ws.addCell(new Label(29, i + 1, Gzsbs.get(i).get("fmoney") == null ? "" : Gzsbs.get(i).get("fmoney").toString(), wcf));
            ws.addCell(new Label(30, i + 1, Gzsbs.get(i).get("ftime") == null ? "" : Gzsbs.get(i).get("ftime").toString(), wcf));
            ws.addCell(new Label(31, i + 1, Gzsbs.get(i).get("ftype") == null ? "" : Gzsbs.get(i).get("ftype").toString(), wcf));
            ws.addCell(new Label(32, i + 1, Gzsbs.get(i).get("fphone_money") == null ? "" : Gzsbs.get(i).get("fphone_money").toString(), wcf));
            if (Gzsbs.get(i).get("ismake") != null && Integer.parseInt(Gzsbs.get(i).get("ismake").toString()) == 1) {
                ws.addCell(new Label(33, i + 1, "是", wcf));
            } else {
                ws.addCell(new Label(33, i + 1, "否", wcf));
            }
            if (Gzsbs.get(i).get("isbill") != null && Integer.parseInt(Gzsbs.get(i).get("isbill").toString()) == 1) {
                ws.addCell(new Label(34, i + 1, "是", wcf));
            } else {
                ws.addCell(new Label(34, i + 1, "否", wcf));
            }

        }

        wwb.write();
        wwb.close();
        outputStream.flush();
        outputStream.close();

    }


}
