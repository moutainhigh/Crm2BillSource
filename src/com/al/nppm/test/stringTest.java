package com.al.nppm.test;

/**
 * @author WangBaoQiang
 * @ClassName: stringTest
 * @description: TODO
 * @date 2019/7/1115:19
 * @Version 1.0
 */
public class stringTest {
    public static void main(String[] args) {
        String serv_offer_id = "4070701003";
        String offerIdTemp = serv_offer_id.substring(0, 7);
        if ("4070701".equals(offerIdTemp)) {
            System.out.println("123");
        }
    }
}
