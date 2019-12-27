package com.yidu.daima;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.yidu.mall.comment.biz.CommentBiz;
import com.yidu.mall.comment.model.CommentImagesJson;
import com.yidu.mall.comment.model.MallComment;
import com.yidu.mall.user.model.MallUser;

import sun.misc.BASE64Decoder;
/**
 * 评论servlet
 * @author 小恶魔
 *
 */
public class CommentServlet extends HttpServlet {

	/**
	 * get请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	//创建评论业务逻辑层
	CommentBiz commentBiz = new CommentBiz();
	/**
	 * post请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");   //请求转码
		
		response.setCharacterEncoding("utf-8");   //响应转码
		
		String mod = request.getParameter("mod");   //获取请求参数  mod
		
		if ("addComment".equals(mod)) {  //添加商品评论  addComment
			addComment(request,response);
		}else if ("getCommentByProductId".equals(mod)) {   //根据商品获取评论   getCommentByProductId
			getCommentByProductId(request,response);
		}else if ("userComment".equals(mod)) {   //判断登录的用户是否可评论此商品   userComment
			userComment(request,response);
		}
	}
	/**
	 * 判断登录的用户是否可评论此商品
	 * @param request  请求
	 * @param response   响应
	 * @throws IOException 
	 */
	private void userComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//获取响应输出对象
		PrintWriter out = response.getWriter();
		//从session获取用户对象
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
		//获取请求中的参数值  商品id  produId
		int productId = Integer.parseInt(request.getParameter("produId"));
		//如果用户对象等于空
		if (user == null ) {
			//响应输出 0 
			out.println(0);
			
		}else{
			//调用评论业务逻辑层方法  判断用户是否可评论
			int count = commentBiz.UserComment(user.getId(), productId);
			//这里说明购买了商品可以评价
			if (count > 0) {
				//再进一步查询  调用业务逻辑层是否已对此商品评价状态方法
				int count2 = commentBiz.productCommentStatus(user.getId(), productId);
				
				if (count2 > 0) {  //count2大于0说明已经评价过不可评价
					 //count 就等于0
					count = 0;
					
				}else{ //否则
					//等于1
					count = 1;
				}
			}
			//响应输出 count
			out.println(count);
		}
		//关闭流
		out.close();
	}
	/**
	 * 根据商品获取评论
	 * @param request  请求
	 * @param response   响应
	 * @throws IOException  IO异常
	 */
	private void getCommentByProductId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//获取请求中的参数值  商品id proidd
		int productId = Integer.parseInt(request.getParameter("proIdd"));
		//调用业务逻辑层方法 根据商品id获取评论
		List<MallComment> comments = commentBiz.getComments(productId);
		//把评论集合转成json
		String commentJson = JSONArray.fromObject(comments).toString();
		//响应输出json
		out.println(commentJson);
		//关闭流
		out.close();
	}
	/**
	 * 添加商品评论
	 * @param request   请求
	 * @param response   响应
	 * @throws IOException 异常
	 */
	private void addComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//从session获取用户信息
		HttpSession session = request.getSession();
		MallUser user = (MallUser)session.getAttribute("LOGIN_USER");
			//获取请求中的参数值
			int productId = Integer.parseInt(request.getParameter("product_id"));  //获取商品评价id
			
			String content = request.getParameter("content");    //获取内容文本
			//获取请求中的参数值(数组)  contentBase64
			String[] base64 = request.getParameterValues("contentBase64");
			//获取请求中的参数值(数组)  imageName
			String[] imagesName = request.getParameterValues("imageName");
			//评论图片集合
			List<CommentImagesJson> imagesJsons = new ArrayList<CommentImagesJson>();
			//把base64转成图片
			for (int i =0; i < base64.length;i++) {
				//把base64转成图片
				GenerateImage(base64[i], imagesName[i]);
				//评论图片json实体类 为空
				CommentImagesJson imagesJson = null;
				// 如果 i 等于 0
				if (i == 0) {
					//创建评论图片json对象把内容和评价图片封装
					imagesJson = new CommentImagesJson(content, "static/comment_img/"+imagesName[i]);
					
					//否则
				}else{  //第二次创建评论图片json对象不需要内容
					imagesJson = new CommentImagesJson("", "static/comment_img/"+imagesName[i]);
				}
				//放入评论图片集合
				imagesJsons.add(imagesJson);
			}
			//评论图片集合转json格式
			String imgJson = JSONArray.fromObject(imagesJsons).toString();
			//调用业务逻辑层  新增评论
			int count = commentBiz.addComment(imgJson, user.getId(), productId);
			//响应 count
			out.println(count);
			//关闭流
			out.close();
	}
	/**
	 * base64字符串转化成图片  
	 * @param imgStr   图片路径
	 * @param imgName     图片名称
	 * @return
	 */
    public static boolean GenerateImage(String imgStr,String imgName)  {   //对字节数组字符串进行Base64解码并生成图片  
    	//判断图片的后缀名
    	int index = imgName.indexOf(".");
    	String newImgStr = imgName.substring(index+1);
    	
    	imgStr = imgStr.replaceAll("data:image/"+newImgStr+";base64,", "");
        if (imgStr == null) //图像数据为空  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            
            String imgFilePath = "E:\\Java\\原创项目\\project\\HuaweiMall\\HuaweiMall\\WebRoot\\static\\comment_img\\"+imgName;//新生成的图片  
            //输出流
            OutputStream out = new FileOutputStream(imgFilePath);      
            
            out.write(b);   //响应 字节类型  b
            
            out.flush();  //刷新响应
            
            out.close();   //关闭输出
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }

}
