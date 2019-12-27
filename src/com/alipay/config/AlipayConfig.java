package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101600699521";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCauUuhvRzG7eogupD3hbxVEWIL9sVf5iXHnfuzVZngsALL2iM8GSFTT/xHjS/Rel/ie/2FfcZbXEwpmec94M0DOQBaPz6RIw3/i/kxA9Mbicy+b66u7jaK/iLGg9EpW4X97ZkKWbYD6V07VJ3Y3X+rzqcZX7CElNT6wj2qfuEl8905m6y2qZlNMLSrtego8IZ548zDnaWR0rPUH+KnmXUMhoAoJwgHNDhXyOfoNd903lXiNdy5SwlcE/9JOreYoJ5E0PdaSnbuKaN3CjMIGWzVpKcjsF5K8jmQtnyZKJglVV/nhQCmYK77WsCdXJmciNF07L5o1o66xEbo1z/IlbkxAgMBAAECggEABWgIHoSvJrxVfANI4X1I0WgkFuruhqVSGKGKk/+futwrnNfod0chhPm8hPuH+jfeddmDSFDWx4Z4GtQH4n+iakRpoL2pAAVLjexkLrRFgibUgqsAXZhxS3YJ3QnqGxnZmr/coyHzXDuhSFtykItc1LIJ4LcZQei5cHGtGfIJB/xpRQSsqfjwvtpU4x02GIkcxWKzf7eUHgxpu0spDh/jdxsrpR3uewjqWvQ5VhEdSTqQalmH0bh+D++T+kunwB5f95SqzEvUm5bfP2fJB/PLbtbKDzTfCQg1XoI/xn9ZcaqA1Zzs+bR0MCp1pIyWwi9hXdeGnoppa0dWkdvYDh32iQKBgQDy5QMv7JjOSCpQt0vvfDwY58w2yOWn9w1PgEKY3K+9lJf3XACsRQzVvOBTkpL3vLDsoyZzXzdBbzQ1wXpgLzP/1EmW6zIL0PmCLUXGwYuj5ozyQv+wLPSS/kq0sh0BmrYj3yAryxVkDEjej3ELH11btSXbdrDp6/4qiI1BdGZUHwKBgQCjEmwHyMSyAG7U7M0bImWU3X9zuYlH4AWVjFSkMv8AsRjuRWDCDOW4eYe8i+qF+e1IgFoRkX2PFoAatEjxtTREV2q3TJf7Y3NVkzW6yCNt3aDifRe5TpenKZqzeSQwQGYqW16BY/c9AKkLhxM1MZV8xF9RKe1OUQls9HoSrdbIrwKBgQDrzQYOFWu2/voNiBSw9vPE3esz2E6ThFSotIRyr/PwYPwyyF9i3ln6ebVxjJ8e4FEGOdEPOgWDgbi24xCxpcGef/G9ELFyJTrEdL0Dmku9Hwn/+qkM2GfK+BGAYFWmhBKEuo0Z1rr6eK6ZWbnNqXJRoIeTCiPvxAnoMs6nF6Br0wKBgH+hxYf6LWkcSaLBjG72ZwcyrbSHvNjSj8xSv1SczVVfRyIGjwqSSaIvDHwYDoC58mvSeaxScTFy7OT5wjXcG4J54P6CJloNppGN3HnKd2BgHLHoMo9I4g1l1L0vzZEudkgkpz0iXroSMDzjhbcWjefPUsMVy7SGXJg4aOsBwJ/lAoGAGiMzELMq9lAWoXxcWxnSfYXMNNnFbSBs3HBSR0HprgReUsliSfu2jIwTi5P1Hbr9rGEGbIMAh6WkQPPnxRXBvg5K2YfFOAZsltd0zOY5VjqbjN2hugcjA0z3Gq3uISGsLNF93Qcnfx9eMcgDvUsNtZcqzU5xj9OGVRTsggCN3/w=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkWy8cd2QeFOL+rs6aAV9M+V4mwxjCwd/lOvMuF79lnTMt1JQLdZhCLupuADgxvwUHbruBLBO2DdUxbi7hw0FEU6xswKVqoBcwMc4HABRFlhsspTmE9JWshc8mfPP/kxJIt5oziH3Ndzi0E9uqngSl01PLOT584hgxyIsjx80f4AHgFz8LihOxWfWtqXHQBkU6/8wb07/gtMyRrXjm+XymnaueuFNqTGwjv9mYhW6Fps0wLdIuhma7QJ8HmXrrIzIKkHiiQXrSpWVHBPON9ChSgUlbJ1wfiuJcAtTGCBBQgyahHLR3ohkz5jjtSmE7LlxX+wReDwraijmQmV0lEsO2QIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/HuaweiMall/pay/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/HuaweiMall/payServlet";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

