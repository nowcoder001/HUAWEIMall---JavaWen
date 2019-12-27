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
			
	
			//����dao��
			UserDao dao = new UserDao();
			OrderDao orderDao = new OrderDao();
			
			/**
			 * �û�ע��
			 * @return
			 */
			public int UserRegister(MallUser user){
				return dao.UserRegister(user);
			}
	
	
			
			/**
			 * �û���¼��Ϣ
			 * @param  phone
			 * @param  password
			 * @return  count
			 */
	
			public MallUser Usermass(String phone, String password){
				
				return dao.Usermass(phone, password);
			}
			//��������û��������ֺ���ĸ���,  
	    	public String getStringRandom(int length) {  
	         
	         String val = "";  
	         Random random = new Random();  
	           
	         //����length����ʾ���ɼ�λ�����  
	        for(int i = 0; i < length; i++) {  
	               
	             String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
	             //�����ĸ��������  
	             if( "char".equalsIgnoreCase(charOrNum) ) {  
	                 //����Ǵ�д��ĸ����Сд��ĸ  
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
	    	 * ��ѯ�����û���Ϣ
	    	 * @return  count
	    	 */
	    	public List<MallUser> SelectUserMassage(){
	    		
	    		return dao.SelectUserMassage();
	    		
	    	}
	
	    	/**
	    	 * ��ҳ
	    	 * @return
	    	 */
	    	public List<MallUser> getPaging(int rows,int page){
				return dao.getPaging(rows, page);
	    		
	    	}
	    	
	    	/**
	    	 * ��ȡҪ�޸ĵ��û���Ϣ
	    	 * @param  phone
	    	 * @param  password
	    	 * @return  count
	    	 */
	    	public MallUser getupdateUser(int id){
	    		return dao.getupdateUser(id);
	    	}
	    	
	    	
	    	/**
	    	 * �޸Ĺ����Ϣ
	    	 * @param advId
	    	 * @return
	    	 */
	    	public int updateUser(MallUser mallUser){
	    		return dao.updateUser(mallUser);
	    	}
	    	
	    	

	    	/**
	    	 * �������ֻ�ȡ��Ʒ
	    	 * @param proName
	    	 * @return
	    	 */
	    	public List<MallUser> getUsermassageByphone(String phone){
	    		return dao.getUsermassageByphone(phone);
	    	}
	    	
	    	/**
			 * ����IDɾ���û�
			 * @param id
			 * @return
			 */
			public int deleteUser(int id){
				return dao.deleteUser(id);
				
			}
			
			/**
			 * �����û�id ��ȡ������Ϣ
			 * @param userId
			 * @return
			 */
			public List<MallOrder> getOrderByUserId(int userId,int noRedo){
				return orderDao.getOrderByUserId(userId,noRedo);
			}
			
			/**
			 * �޸�ҳ���û���Ϣ
			 * @param password  phone
			 * @return
			 */
			public int updateUserMassage(String Massage,String column,int id){
				return dao.updateUserMassage(Massage, column,id);
			}
			/**
			 * �޸��û�����
			 * @param password  phone
			 * @return
			 */
			public int updatePassword(String password,String phone){
				return dao.updatePassword(password,phone);
			}
			/**
			 * �����û�id��ȡ���ж������(��֧��)
			 * @param userId
			 * @return
			 */
			public double getTotalPrice(int userId){
				return dao.getTotalPrice(userId);
			}
			/**
			 * �޸��û�����
			 * @param userId
			 * @param Integral
			 * @return
			 */
			public int updateUserIntegral(int userId,double Integral){
				return dao.updateUserIntegral(userId, Integral);
			}
}
