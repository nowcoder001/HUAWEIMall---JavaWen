package com.huawei.model;
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  
public class Demo {
	
	public static void main(String[] args) {
		System.out.println(123);
	}
	
	//base64�ַ���ת����ͼƬ  
    public static boolean GenerateImage(String imgStr)  {   //���ֽ������ַ�������Base64���벢����ͼƬ  
        if (imgStr == null) //ͼ������Ϊ��  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64����  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//�����쳣����  
                    b[i]+=256;  
                }  
            }  
            //����jpegͼƬ  
            String imgFilePath = "D:\\tupian\\new.jpg";//�����ɵ�ͼƬ  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }
}
