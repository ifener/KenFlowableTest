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
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class MyKeyGenerator2 {
	
	public static void main(String[] args) {
		try {
			String word = "Ҫ���ܵ�";
			String ALGORITHM="AES";
			System.out.println(HexBin.encode(word.getBytes()));
			KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
			keyGenerator.init(128,new SecureRandom());  //Ĭ����128  AESҪ����Կ����Ϊ128,192,256λ   
			SecretKey secretKey = keyGenerator.generateKey(); //������Կ
			byte[] bytes = secretKey.getEncoded();

			String key = HexBin.encode(bytes);
			System.out.println("16���Ƶ���Կ��"+key);
			//String key2 = toHexString(bytes);
			//System.out.println(key2);
			//AES����
			SecretKey secretKey2 = new SecretKeySpec(HexBin.decode(key), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey2);
			byte[] cipherByte = cipher.doFinal(word.getBytes()); //����
			
			String result = HexBin.encode(cipherByte);
			System.out.println("AES���ܽ����"+result);
			
			//AES����
			SecretKey secretKey3 = new SecretKeySpec(HexBin.decode(key), ALGORITHM);//�ָ���Կ
			Cipher cipher2 = Cipher.getInstance(ALGORITHM);//Cipher��ɼ��ܻ���ܹ�����
			cipher2.init(Cipher.DECRYPT_MODE, secretKey3);//��Cipher��ʼ��������ģʽ
			byte[] cipherByte2 = cipher2.doFinal(HexBin.decode(result));//����data
			
			System.out.println("AES���ܽ����"+HexBin.encode(cipherByte2));
			
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
}