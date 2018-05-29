/**
 * 分账管理管理初始化
 */
var Fz = {
    id: "FzTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Fz.initColumn = function () {
    return [
            {field: 'selectItem', radio: true},
            {title: 'id', field: 'ID_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '编号', field: 'NO_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '商户名称', field: 'PNAME_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '发展人', field: 'NAME_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '所属', field: 'BELONG_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '商户简称', field: 'PAK_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '终端数量', field: 'ZCOUNT_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '入网模式', field: 'NET_WORK_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '租赁金额', field: 'LEASING_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '终端购买费', field: 'COST_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '通讯费', field: 'PHONE_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '机型', field: 'TYPE_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '提交审核时间', field: 'CHECK_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '商户编号', field: 'MERCHANT_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '传统终端号', field: 'TRADITIONAL_FZ', visible: true, align: 'center', valign: 'middle'},
            {title: '中信终端号', field: 'CITIC_FZ', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Fz.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Fz.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加分账管理
 */
Fz.openAddFz = function () {
    var index = layer.open({
        type: 2,
        title: '添加分账管理',
        area: ['860px', '620px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/fz/fz_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看分账管理详情
 */
Fz.openFzDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '分账管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/fz/fz_update/' + Fz.seItem.ID_FZ
        });
        this.layerIndex = index;
    }
};

/**
 * 删除分账管理
 */
Fz.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/fz/delete", function (data) {
            Feng.success("删除成功!");
            Fz.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("fzId",this.seItem.ID_FZ);
        ajax.start();
    }
};


/**
 * 财务添加信息
 */
Fz.finance = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '添加分账管理',
            area: ['800px', '620px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/fz/fz_finance/' + Fz.seItem.ID_FZ
        });
        this.layerIndex = index;
    }
};





/**finance market
 * 导入分账管理
 */
Fz.fileIn = function () {
    var index1 = layer.open({
        type: 2,
        title: '添加分账管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/fz/fz_fileIn'
    });
    this.layerIndex = index1;


};







/**
 * 导出分账管理
 */
Fz.fileOut = function () {
window.location.href=Feng.ctxPath + "/fz/fileOut?pnameFz="+ $("#pnameFz").val()+"&traditionalFz="+$("#traditionalFz").val()+"&nameFz="+$("#nameFz").val()+"&pakFz="+$("#pakFz").val()+"&beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val();

};


/**
 * 批量删除
 */

Fz.deleteAll=function () {

    swal({
        title: "操作提示",      //弹出框的title
        text: "确定删除吗？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，确定删除！",//确定按钮上面的文档
        closeOnConfirm: true
    }, function () {
        $.ajax({
            type: "post",
            url: Feng.ctxPath +"/fz/deleteAll",
            data: { pnameFz:$("#pnameFz").val(),traditionalFz:$("#traditionalFz").val(), nameFz:$("#nameFz").val(),pakFz:$("#pakFz").val(),beginTime:$("#beginTime").val(),endTime:$("#endTime").val()},
            success: function (data, status) {
                if (status == "success") {
                    toastr.success('删除数据成功');
                    $("#FzTable").bootstrapTable('refresh');
                }
            },
            error: function () {
                toastr.error('Error');
            },
            complete: function () {

            }

        });

    });


}



/**
 * 批量修改
 */

Fz.updateAll=function () {
    var index1 = layer.open({
        type: 2,
        title: '添加分账管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/fz/fz_fileIn_updateAll'
    });
    this.layerIndex = index1;



}




/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Fz.formParams = function() {
    var queryData = {};
    queryData['pnameFz'] = $("#pnameFz").val();
    queryData['traditionalFz'] = $("#traditionalFz").val();
    queryData['nameFz'] = $("#nameFz").val();
    queryData['pakFz'] = $("#pakFz").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    return queryData;
}

/**
 * 查询日志列表
 */
Fz.search = function () {

    Fz.table.refresh({query: Fz.formParams()});
};


$(function () {
    var defaultColunms = Fz.initColumn();
    var table = new BSTable(Fz.id, "/fz/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Fz.formParams());
    Fz.table = table.init();
});



/**
 * 市场添加信息
 */
Fz.market = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '添加分账管理',
            area: ['800px', '520px'], //宽高ck
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/fz/fz_market/'+ Fz.seItem.ID_FZ
        });
        this.layerIndex = index;
    }
};