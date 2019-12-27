<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      
<%@page import="java.util.HashMap"%> 
<%@page import="java.util.Map"%> 
<%@ page import="com.google.gson.*" %>
<%@ page import="com.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SendSms</title>
</head>
<body>
<%
// 您的短信账号
String Account = "17755986729";
// 您的短信账号密码
String Password = "TAOgu123";
// 是否需要状态报告
String NeedStatus = "true";
String code = request.getParameter("code");
String message = "感谢您注册基因重组科技，您的验证码是"+code+"。";//短信内容

String mobile = request.getParameter("mobile");//要发送的手机号,多个手机号用,隔开
String ts =  Utility.getNowDateStr();//时间戳
 
 
Password = Utility.getMD5(Account + Password + ts);// Md5签名(账号+密码+时间戳)
HashMap params = new HashMap(); 
//请求参数 
params.put("account",Account);
params.put("pswd",Password);
params.put("mobile",mobile);
params.put("msg",message);
params.put("ts",ts);
params.put("needstatus",NeedStatus);
String rep = ZxHttpUtil.sendPostMessage("http://139.196.108.241:8080/Api/HttpSendSMYzm.ashx", params, "UTF-8");

 JsonParser parser = new JsonParser();
  
JsonObject json = (JsonObject)parser.parse(rep);           
        
out.print(json.get("result_msg"));
%>
</body>
</html>
