package com30.i14;

import org.junit.Test;

public class ReqresRequest {

    @Test
    public void test() {
        ReqresToken reqresToken = new ReqresToken();
        System.out.println("token = " + reqresToken.generateToken());
    }
}
