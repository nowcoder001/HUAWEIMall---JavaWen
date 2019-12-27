package com.yidu.mall.user.biz;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import com.yidu.mall.order.dao.OrderDao;
import com.yidu.mall.order.model.MallOrder;
import com.yidu.mall.user.dao.UserDao;
import com.yidu.mall.user.model.MallUser;

public class UserBiz {
			
	
			//调用dao层
			UserDao dao = new UserDao();
			OrderDao orderDao = new OrderDao();
			
			/**
			 * 用户注册
			 * @return
			 */
			public int UserRegister(MallUser user){
				return dao.UserRegister(user);
			}
	
	
			
			/**
			 * 用户登录信息
			 * @param  phone
			 * @param  password
			 * @return  count
			 */
	
			public MallUser Usermass(String phone, String password){
				
				return dao.Usermass(phone, password);
			}
			//生成随机用户名，数字和字母组成,  
	    	public String getStringRandom(int length) {  
	         
	         String val = "";  
	         Random random = new Random();  
	           
	         //参数length，表示生成几位随机数  
	        for(int i = 0; i < length; i++) {  
	               
	             String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
	             //输出字母还是数字  
	             if( "char".equalsIgnoreCase(charOrNum) ) {  
	                 //输出是大写字母还是小写字母  
	                 int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
	                 val += (char)(random.nextInt(26) + temp);  
	            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
	                val += String.valueOf(random.nextInt(10));  
	            }  
	        }  
	       return val;  
	    }      
	
	    	
	    	public  String getMD5Str(String str) {  
	    	        MessageDigest messageDigest = null;  

	    	        try {  
	    	            messageDigest = MessageDigest.getInstance("MD5");  

	    	            messageDigest.reset();  

	    	            messageDigest.update(str.getBytes("UTF-8"));  
	    	        } catch (NoSuchAlgorithmException e) {  
	    	            System.out.println("NoSuchAlgorithmException caught!");  
	    	            System.exit(-1);  
	    	        } catch (UnsupportedEncodingException e) {  
	    	            e.printStackTrace();  
	    	        }  

	    	        byte[] byteArray = messageDigest.digest();  

	    	        StringBuffer md5StrBuff = new StringBuffer();  

	    	        for (int i = 0; i < byteArray.length; i++) {              
	    	            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
	    	                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
	    	            else  
	    	                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
	    	        }  

	    	        return md5StrBuff.toString();  
	    	    }  	
	    	
	    	/**
	    	 * 查询所有用户信息
	    	 * @return  count
	    	 */
	    	public List<MallUser> SelectUserMassage(){
	    		
	    		return dao.SelectUserMassage();
	    		
	    	}
	
	    	/**
	    	 * 分页
	    	 * @return
	    	 */
	    	public List<MallUser> getPaging(int rows,int page){
				return dao.getPaging(rows, page);
	    		
	    	}
	    	
	    	/**
	    	 * 获取要修改的用户信息
	    	 * @param  phone
	    	 * @param  password
	    	 * @return  count
	    	 */
	    	public MallUser getupdateUser(int id){
	    		return dao.getupdateUser(id);
	    	}
	    	
	    	
	    	/**
	    	 * 修改广告信息
	    	 * @param advId
	    	 * @return
	    	 */
	    	public int updateUser(MallUser mallUser){
	    		return dao.updateUser(mallUser);
	    	}
	    	
	    	

	    	/**
	    	 * 根据名字获取商品
	    	 * @param proName
	    	 * @return
	    	 */
	    	public List<MallUser> getUsermassageByphone(String phone){
	    		return dao.getUsermassageByphone(phone);
	    	}
	    	
	    	/**
			 * 根据ID删除用户
			 * @param id
			 * @return
			 */
			public int deleteUser(int id){
				return dao.deleteUser(id);
				
			}
			
			/**
			 * 根据用户id 获取订单信息
			 * @param userId
			 * @return
			 */
			public List<MallOrder> getOrderByUserId(int userId,int noRedo){
				return orderDao.getOrderByUserId(userId,noRedo);
			}
			
			/**
			 * 修改页面用户信息
			 * @param password  phone
			 * @return
			 */
			public int updateUserMassage(String Massage,String column,int id){
				return dao.updateUserMassage(Massage, column,id);
			}
			/**
			 * 修改用户密码
			 * @param password  phone
			 * @return
			 */
			public int updatePassword(String password,String phone){
				return dao.updatePassword(password,phone);
			}
			/**
			 * 根据用户id获取所有订单金额(已支付)
			 * @param userId
			 * @return
			 */
			public double getTotalPrice(int userId){
				return dao.getTotalPrice(userId);
			}
			/**
			 * 修改用户积分
			 * @param userId
			 * @param Integral
			 * @return
			 */
			public int updateUserIntegral(int userId,double Integral){
				return dao.updateUserIntegral(userId, Integral);
			}
}
