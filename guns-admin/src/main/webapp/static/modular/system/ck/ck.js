/**
 * 管理初始化
 */
var Ck = {
    id: "CkTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Ck.initColumn = function () {
    return [


        {field: 'selectItem', radio: true},

        {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '传统终端号', field: 'TRADITIONAL_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '中信终端号', field: 'CITIC_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '商户注册名称', field: 'PNAME_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '押金', field: 'LEASING_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '通讯费', field: 'PHONE_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '入网模式', field: 'NET_WORK_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '终端购买费', field: 'COST_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '提交审核时间', field: 'CHECK_FZ', visible: true, align: 'center', valign: 'middle'},
        {title: '出库人', field: 'c_name', visible: true, align: 'center', valign: 'middle'},
        {title: '出库时间', field: 'c_time', visible: true, align: 'center', valign: 'middle'},
        {title: '出库机型', field: 'c_model', visible: true, align: 'center', valign: 'middle'},
        {
            title: '开票类型', visible: true, align: 'center', valign: 'middle', formatter: function (value, row, index) {
                if (row.c_bill_type == 1) {
                    return "收据";

                } else if (row.c_bill_type == 2) {
                    return "普票"
                }else if (row.c_bill_type == 3) {
                    return "专票"
                }  else {
                    return "未知";
                }

            }
        },
        {title: '税号', field: 'c_duty_no', visible: true, align: 'center', valign: 'middle'},
        {title: '账号', field: 'c_no', visible: true, align: 'center', valign: 'middle'},
        {title: '地址', field: 'c_addre', visible: true, align: 'center', valign: 'middle'},
        {title: '电话', field: 'c_phone', visible: true, align: 'center', valign: 'middle'},
        {title: '开户行', field: 'c_bank', visible: true, align: 'center', valign: 'middle'},
        {title: '分账id', field: 'fz_id', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Ck.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Ck.seItem = selected[0];
        return true;
    }
};


/**
 * 打开查看详情
 */
Ck.openCkDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ck/ck_update/' + Ck.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Ck.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/ck/delete", function (data) {
            Feng.success("删除成功!");
            Ck.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id", this.seItem.id);
        ajax.start();
    }
};


/**finance market
 * 导入分账管理
 */
Ck.market_fileIn = function () {
    var index2 = layer.open({
        type: 2,
        title: '导入数据（市场）',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ck/marketFileIn'
    });
    this.layerIndex = index2;


};


Ck.ck_fileOut=function () {

    window.location.href=Feng.ctxPath + "/ck/fileOut?pnameFz="+ $("#pnameFz").val()+"&traditionalFz="+$("#traditionalFz").val()+"&beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val();



}
/**
 * 查询列表
 */
Ck.search = function () {

    Ck.table.refresh({query: Ck.formParams()});
};



Ck.formParams = function() {
    var queryData = {};
    queryData['pnameFz'] = $("#pnameFz").val();
    queryData['citicFz'] = $("#citicFz").val();
    queryData['traditionalFz'] = $("#traditionalFz").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();

    return queryData;
}


//查询数据
$(function () {
    var defaultColunms = Ck.initColumn();
    var table = new BSTable(Ck.id, "/ck/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Ck.formParams());
    Ck.table = table.init();
});



/**
 * 市场添加信息
 */
Ck.market = function () {

        var index3 = layer.open({
            type: 2,
            title: '添加',
            area: ['800px', '520px'], //宽高ck
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ck/ck_market'
        });
        this.layerIndex = index3;

};
