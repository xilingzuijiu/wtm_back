package com.weitaomi.systemconfig.wechat;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2016/8/30.
 */
@XStreamAlias("xml")
public class WechatBatchPayParams {
    /**
     * 公众账号appid
     */
    private String mch_appid;
    /**
     * 商户号
     */
    private String mchid;
    /**
     * 设备号
     */
    private String nonce_str;
    /**
     * 签名
     */
    private String sign;
    /**
     * 商户订单号
     */
    private String partner_trade_no;
    /**
     * 用户openid
     */
    private String openid;
    /**
     * 校验用户姓名选项
     */
    private String check_name="NO_CHECK";
    /**
     * 金额
     */
    private String amount;
    /**
     * 企业付款描述信息
     */
    private String desc;
    /**
     * Ip地址
     */
    private String spbill_create_ip;

    /**
     * 获取公众账号appid
     * @return mch_appid 公众账号appid
     */
    public String getMch_appid() {
        return this.mch_appid;
    }

    /**
     * 设置公众账号appid
     * @param mch_appid 公众账号appid
     */
    public void setMch_appid(String mch_appid) {
        this.mch_appid = mch_appid;
    }

    /**
     * 获取商户号
     * @return mchid 商户号
     */
    public String getMchid() {
        return this.mchid;
    }

    /**
     * 设置商户号
     * @param mchid 商户号
     */
    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    /**
     * 获取设备号
     * @return nonce_str 设备号
     */
    public String getNonce_str() {
        return this.nonce_str;
    }

    /**
     * 设置设备号
     * @param nonce_str 设备号
     */
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    /**
     * 获取签名
     * @return sign 签名
     */
    public String getSign() {
        return this.sign;
    }

    /**
     * 设置签名
     * @param sign 签名
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取商户订单号
     * @return partner_trade_no 商户订单号
     */
    public String getPartner_trade_no() {
        return this.partner_trade_no;
    }

    /**
     * 设置商户订单号
     * @param partner_trade_no 商户订单号
     */
    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    /**
     * 获取用户openid
     * @return openid 用户openid
     */
    public String getOpenid() {
        return this.openid;
    }

    /**
     * 设置用户openid
     * @param openid 用户openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }


    /**
     * 获取金额
     * @return amount 金额
     */
    public String getAmount() {
        return this.amount;
    }

    /**
     * 设置金额
     * @param amount 金额
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * 获取企业付款描述信息
     * @return desc 企业付款描述信息
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * 设置企业付款描述信息
     * @param desc 企业付款描述信息
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取Ip地址
     * @return spbill_create_ip Ip地址
     */
    public String getSpbill_create_ip() {
        return this.spbill_create_ip;
    }

    /**
     * 设置Ip地址
     * @param spbill_create_ip Ip地址
     */
    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    /**
     * 获取校验用户姓名选项
     * @return check_name 校验用户姓名选项
     */
    public String getCheck_name() {
        return this.check_name;
    }

    /**
     * 设置校验用户姓名选项
     * @param check_name 校验用户姓名选项
     */
    public void setCheck_name(String check_name) {
        this.check_name = check_name;
    }
}
