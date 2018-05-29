/**
 * 财务添加管理初始化
 */
var Finance = {
    id: "FinanceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Finance.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '终端号', field: 'TRADITIONAL_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '商户注册名称', field: 'PNAME_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '押金', field: 'LEASING_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '通讯费', field: 'PHONE_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '入网模式', field: 'NET_WORK_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '终端购买费', field: 'COST_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '提交审核时间', field: 'CHECK_FZ', visible: true, align: 'center', valign: 'middle'},

        {title: '缴款人员', field: 'fname_no', visible: true, align: 'center', valign: 'middle'},
        {title: '缴款单号', field: 'jkno', visible: true, align: 'center', valign: 'middle'},
        {title: '销售款', field: 'fmarket_money', visible: true, align: 'center', valign: 'middle'},
        {title: '票据编号', field: 'fno', visible: true, align: 'center', valign: 'middle'},
        {title: '押金', field: 'fmoney', visible: true, align: 'center', valign: 'middle'},
        {title: '缴款日期', field: 'ftime', visible: true, align: 'center', valign: 'middle'},
        {
            title: '收款方式', visible: true, align: 'center', valign: 'middle', formatter: function (value, row, index) {
                if (row.ftype == 1) {
                    return "pos机";

                } else if (row.ftype == 2) {
                    return "支付宝"
                } else if (row.ftype == 3) {
                    return "微信";
                } else {
                    return "--";
                }

            }
        },
        {title: '通讯费', field: 'fphone_money', visible: true, align: 'center', valign: 'middle'},
        {
            title: '是否缴款', visible: true, align: 'center', valign: 'middle', formatter: function (value, row, index) {
                if (row.ismake == 1) {
                    return "是";

                } else {
                    return "否";
                }
            }
        },
        {
            title: '是否开发票', visible: true, align: 'center', valign: 'middle', formatter: function (value, row, index) {
                if (row.isbill == 1) {
                    return "是";

                } else {
                    return "否";
                }
            }
        },
        {title: '分账id', field: 'fid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Finance.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Finance.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加财务添加
 */
Finance.openAddFinance = function () {
    /* var index = layer.open({
         type: 2,
         title: '添加财务添加',
         area: ['800px', '420px'], //宽高
         fix: false, //不固定
         maxmin: true,
         content: Feng.ctxPath + '/finance/finance_add'
     });
     this.layerIndex = index;*/
    alert("分账管理~添加");
};

/**
 * 打开查看财务添加详情
 */
Finance.openFinanceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '财务添加详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/finance/finance_update/' + Finance.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除财务添加
 */
Finance.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/finance/delete", function (data) {
            Feng.success("删除成功!");
            Finance.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id", this.seItem.id);
        ajax.start();
    }
};


/**
 * 查询列表
 */
Finance.search = function () {

    Finance.table.refresh({query: Finance.formParams()});
};


Finance.formParams = function () {
    var queryData = {};
    queryData['pnameFz'] = $("#pnameFz").val();
    queryData['traditionalFz'] = $("#traditionalFz").val();
    queryData['beginTime1'] = $("#beginTime1").val();
    queryData['endTime'] = $("#endTime").val();
    return queryData;
}


//查询数据
$(function () {
    var defaultColunms = Finance.initColumn();
    var table = new BSTable(Finance.id, "/finance/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Finance.formParams());
    Finance.table = table.init();
});



/**
 * 导出分账管理
 */
Finance.outFinanceDetail= function () {

    window.location.href=Feng.ctxPath + "/finance/file_out?pnameFz="+ $("#pnameFz").val()+"&traditionalFz="+$("#traditionalFz").val()+"&beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val();


};


Finance.openFinanceAll=function () {
    var index1 = layer.open({
        type: 2,
        title: '导入',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/finance/updateHml'
    });
    this.layerIndex = index1;


}

/**finance market
 * 导入分账管理
 */
Finance.inAddFinance= function () {
    var index1 = layer.open({
        type: 2,
        title: '导入',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/finance/file_In'
    });
    this.layerIndex = index1;


};


