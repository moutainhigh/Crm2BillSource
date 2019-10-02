package com.al.nppm.business.syntomq.tool;

/**
 * @author WangBaoQiang
 * @ClassName: Test
 * @description: TODO
 * @date 2019/9/2517:06
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        try{
            STATUS status=CTGMqTool.send("123","1111","1111");
            System.out.println(status.getMsg());
        }catch (Exception EX)
        {
            EX.printStackTrace();
        }
    }
}
