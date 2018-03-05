package org.flowable;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyKeyGenerator {
	
	public static void main(String[] args) {
		try {
			String word = "Ҫ���ܵ�";
			String ALGORITHM="AES";
			System.out.println(byteToHexString(word.getBytes()));
			KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
			keyGenerator.init(128,new SecureRandom());  //Ĭ����128  AESҪ����Կ����Ϊ128,192,256λ   
			SecretKey secretKey = keyGenerator.generateKey(); //������Կ
			byte[] bytes = secretKey.getEncoded();

			String key = byteToHexString(bytes);
			System.out.println("16���Ƶ���Կ��"+key);
			//String key2 = toHexString(bytes);
			//System.out.println(key2);
			//AES����
			SecretKey secretKey2 = new SecretKeySpec(hexStringToBytes(key), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey2);
			byte[] cipherByte = cipher.doFinal(word.getBytes()); //����
			
			String result = byteToHexString(cipherByte);
			System.out.println("AES���ܽ����"+result);
			
			//AES����
			SecretKey secretKey3 = new SecretKeySpec(hexStringToBytes(key), ALGORITHM);//�ָ���Կ
			Cipher cipher2 = Cipher.getInstance(ALGORITHM);//Cipher��ɼ��ܻ���ܹ�����
			cipher2.init(Cipher.DECRYPT_MODE, secretKey3);//��Cipher��ʼ��������ģʽ
			byte[] cipherByte2 = cipher2.doFinal(hexStringToBytes(result));//����data
			
			System.out.println("AES���ܽ����"+byteToHexString(cipherByte2));
			
			System.out.println(new String(cipherByte2));
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}
	
	 /**
	  * byte����ת��Ϊ16�����ַ���
	  * @param bytes
	  * @return
	  */
	  public static String byteToHexString(byte[] bytes) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < bytes.length; i++) {
	      String strHex=Integer.toHexString(bytes[i]);
	      if(strHex.length() > 3) {
	        sb.append(strHex.substring(6));
	      } else {
	        if(strHex.length() < 2) {
	          sb.append("0" + strHex);
	        } else {
	          sb.append(strHex);
	        }
	      }
	    }
	    return sb.toString();
	  }
	  
	  /**
	   * 16�����ַ���תΪbyte����
	   * @param hexString
	   * @return
	   */
	  public static byte[] hexStringToBytes(String hexString) {   
		    if (hexString == null || hexString.equals("")) {   
		        return null;   
		    }   
		    hexString = hexString.toUpperCase();   
		    int length = hexString.length() / 2;   
		    char[] hexChars = hexString.toCharArray();   
		    byte[] d = new byte[length];   
		    for (int i = 0; i < length; i++) {   
		        int pos = i * 2;   
		        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
		    }   
		    return d;   
		}
	  
	  private static byte charToByte(char c) {   
		    return (byte) "0123456789ABCDEF".indexOf(c);   
	  } 

}