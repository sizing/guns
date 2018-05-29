/**
 * 初始化分账管理详情对话框
 */
var FzInfoDlg = {
    fzInfoData : {}
};

/**
 * 清除数据
 */
FzInfoDlg.clearData = function() {
    this.fzInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FzInfoDlg.set = function(key, val) {
    this.fzInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FzInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FzInfoDlg.close = function() {
    parent.layer.close(window.parent.Fz.layerIndex);
}

/**
 * 收集数据
 */
FzInfoDlg.collectData = function() {
    this
    .set('idFz')
    .set('noFz')
    .set('pnameFz')
    .set('nameFz')
    .set('belongFz')
    .set('pakFz')
    .set('zcountFz')
    .set('netWorkFz')
    .set('leasingFz')
    .set('costFz')
    .set('phoneFz')
    .set('typeFz')
    .set('checkFz')
    .set('merchantFz')
    .set('traditionalFz')
    .set('citicFz');
}

/**
 * 提交添加
 */
FzInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/fz/add", function(data){
        Feng.success("添加成功!");
        window.parent.Fz.table.refresh();
        FzInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.fzInfoData);
    ajax.start();
}


/**
 *表格上传
 */
FzInfoDlg.fileSubmit = function() {

    $.ajax({
        url:Feng.ctxPath + "/fz/fz_fileIn_uplod",
        type: 'POST',
        cache: false,
        data: new FormData($("#uploadForm")[0]),
        processData: false,
        contentType: false,
        success: function (result) {
            Feng.success("添加成功!");
            window.parent.Fz.table.refresh();
            FzInfoDlg.close();
        },
        error: function (err) {
            Feng.error("添加失败!"+err+ "!");
        }
    });



}


/**
 * 提交修改
 */
FzInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/fz/update", function(data){
        Feng.success("修改成功!");
        window.parent.Fz.table.refresh();
        FzInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.fzInfoData);
    ajax.start();
}

$(function() {

});
