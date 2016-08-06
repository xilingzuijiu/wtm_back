package com.weitaomi.systemconfig.alipay;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

	public static String payCode_prefix="wtm";

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088321045613085";
	//账户名
	public static String seller_name = "青岛匠人在线网络科技";

	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = "9492011@qq.com";

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJvwnL53Wzf27eJp" +
			"hcsWxc5zRZ04+MAYbdqpE/zb5MR+ecMP0OoHneRHU8fuM2tAdMeBvcSKc1ue0Yl5" +
			"zZ6pdoD4CE5w7snB3+rKqMsemM0DTFWpbNySo//rT8sqigprVJV2S6No5j0xjheZ" +
			"+iHYnlbFCe3qs2oI4UZNnVxuIbcRAgMBAAECgYASQUCYK3DKRGXks2WuOKQ/Xs8v" +
			"cJPuKO3zQwfL+hH1zriCVT+npeyd5JuyM/hg/dKt6EpGz85hU15EMbYYdM4ibBsU" +
			"jMsY/acXdfjOwUPfXjnxrB5Xyft5svsJYcI6XyUFwEKDEWgTE3E6tNNQI2dNrr3T" +
			"p4IhUgy7ZDrB2ZfmYQJBAM0c0qkfS6tt6oIng/+M7j7+qUlITE2ssW2CV7/0/VIP" +
			"DSy6G/PQdlg+i0LlvDPmw0yu4JMMcBwDK6APScBu18sCQQDCoLeUkaKiUlS8WZy9" +
			"ynKVi1dI6wpAlgQQuyctVNi7U8i2NMnLvLwNwVQUaNpXUl4lfFcbqeKPxNRwfITJ" +
			"IrkTAkAWtl9ZXN7LL9Os7+U88gUbsqVaOQhB/aJ0Bt/ioKVAZexGjKE1wVqpcNuI" +
			"39mUQbRUlHTHs7PiVjOJeMfo6B07AkEAkuLiyiDDokgKpX/oOKCyq8RW4rRor2fI" +
			"t6vXnHG9hFtuTn02kynkr2jqAOFFb8O9RAM8ZqdnUuPVBPQk7vviuwJBAKSD5PEu" +
			"E3zSj1z01242zPKs3aITwvqIRbGVG3F9Qw3+CKqlAuD0gCcxVIlQJpP7oo6lUxVt" +
			"zTcq462o4AF17Yg=";

	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//TODO
	public static String notify_url = "http://211.149.195.220:8001/app/admin/payment/verifyAlipayNotify";
	//批量付款通知页面
	public static String batchPay_notify_url = "http://211.149.195.220:8001/app/admin/payment/verifyAlipayNotify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//TODO
	public static String return_url = "http://商户网址/alipay.wap.create.direct.pay.by.user-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA";

	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "D:\\projectlogs\\weitaomi\\alipay";

	// 字符编码格式 目前支持utf-8
	public static String input_charset = "utf-8";

	// 支付类型 ，无需修改
	public static String payment_type = "1";

	// 调用的接口名，无需修改
	public static String service = "mobile.securitypay.pay";
	// 批量付款接口
	public static String batchPayservice = "batch_trans_notify";

}

