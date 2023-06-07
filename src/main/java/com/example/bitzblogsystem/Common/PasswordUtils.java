package com.example.bitzblogsystem.Common;

import com.example.bitzblogsystem.Entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 加盐算法的实现
 *
 * 加盐算法的最终约定格式: 【32位盐值+$+32位加盐之后的密码】
 * 每个密码最终长度为 : 65 , 单位: byte
 */

public class PasswordUtils {

    //生成加盐密码
    public static String encrypt(String pwd){
        //生成32位byte的盐值
        String salt = UUID.randomUUID().toString().replace("-" , "");
        //生成加盐之后的密码
        String saltPwd = DigestUtils.md5DigestAsHex((salt+pwd).getBytes());
        //生成最终密码（保存到数据库中的密码）
        String finalPwd = salt + "$" + saltPwd;

        return finalPwd;
    }

    /**\
     * 生成加盐的密码（方法1 encrypt 的重载）
     *
     * @param pwd 我们收到的密码明文
     * @param salt 固定的盐值
     * @return  最终密码
     */
    public static String encrypt(String pwd , String salt){
        // 生成一个加盐之后的密码
        String saltPwd = DigestUtils.md5DigestAsHex((salt + pwd).getBytes());
        // 生成最终的密码
        String finalPwd = salt + "$" +saltPwd;

        return finalPwd;
    }

    /**
     * 验证密码
     *
     * @param inputPwd 用户输入的密码
     * @param finalPwd 数据库存放的加盐密码
     */
    public static boolean check(String inputPwd , String finalPwd){
        if(StringUtils.hasLength(inputPwd) && StringUtils.hasLength(finalPwd)
                && finalPwd.length() == 65){
            // 得到盐值
            String salt = finalPwd.split("\\$")[0];
            // 使用之前加密的步骤，将明文密码和已经得到的盐值进行加密，生成最终的密码
            String confirmPwd = PasswordUtils.encrypt(inputPwd ,salt);
            // 对比最终密码是否相同
            return confirmPwd.equals(finalPwd);
        }
        return false;
    }
}
