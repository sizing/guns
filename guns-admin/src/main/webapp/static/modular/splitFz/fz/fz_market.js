/**
 * 初始化详情对话框
 */
var CkInfoDlg = {
    ckInfoData : {}
};

/**
 * 清除数据
 */
CkInfoDlg.clearData = function() {
    this.ckInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CkInfoDlg.set = function(key, val) {
    this.ckInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CkInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CkInfoDlg.close = function() {
    parent.layer.close(window.parent.Ck.layerIndex);
}

/**
 * 收集数据
 */
CkInfoDlg.collectData = function() {
    this
        .set('cName')
        .set('cTime')
        .set('cModel')
        .set('cBillType')
        .set('cDutyNo')
        .set('cNo')
        .set('cAddre')
        .set('cPhone')
        .set('cBank')
        .set('fzId');
}

/**
 * 提交添加
 */
CkInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ck/add", function(data){
        Feng.success("添加成功!");


        window.parent.Ck.table.refresh();
        CkInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ckInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CkInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ck/update", function(data){
        Feng.success("修改成功!");
        window.parent.Ck.table.refresh();
        CkInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ckInfoData);
    ajax.start();
}

$(function() {

});
