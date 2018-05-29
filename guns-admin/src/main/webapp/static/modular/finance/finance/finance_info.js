/**
 * 初始化财务添加详情对话框
 */
var FinanceInfoDlg = {
    financeInfoData : {}
};

/**
 * 清除数据
 */
FinanceInfoDlg.clearData = function() {
    this.financeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FinanceInfoDlg.set = function(key, val) {
    this.financeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FinanceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FinanceInfoDlg.close = function() {
    parent.layer.close(window.parent.Finance.layerIndex);
}

/**
 * 收集数据
 */
FinanceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('fnameNo')
    .set('jkno')
    .set('fmarketMoney')
    .set('fno')
    .set('fmoney')
    .set('ftime')
    .set('ftype')
    .set('fphoneMoney')
    .set('ismake')
    .set('isbill')
    .set('fid');
}

/**
 * 提交添加
 */
FinanceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/finance/add", function(data){
        Feng.success("添加成功!");
        window.parent.Finance.table.refresh();
        FinanceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.financeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FinanceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/finance/update", function(data){
        Feng.success("修改成功!");
        window.parent.Finance.table.refresh();
        FinanceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.financeInfoData);
    ajax.start();
}

$(function() {

});
