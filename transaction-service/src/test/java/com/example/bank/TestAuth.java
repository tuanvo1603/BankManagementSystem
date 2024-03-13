package com.example.bank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class TestAuth {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Test
    public void test() {
        System.out.println(jwtDecoder.decode("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiaXNzIjoiaHR0cHM6Ly9hdXRob3JpemF0aW9uLXNlcnZlci5jb20iLCJhdWQiOiJodHRwczovL3Jlc291cmNlLXNlcnZlci5jb20iLCJleHAiOjE3NDYyMTEyMDAsImlhdCI6MTY3MjcyNDQwMCwic2NvcGUiOiJyZWFkIHdyaXRlIiwicmVzb3VyY2VfYWNjZXNzIjp7InVzZXIiOnsicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdfX0sImN1c3RvbV9jbGFpbSI6InNvbWVfdmFsdWUifQ.nwdgnquKlKq-ontyO2rFZGLnvs2F-AeuucoXBaQt2r9OwDTC75lgOwlKYynefrxUL-l0W50SFum0Fnkm3Szk6ip4RiPmiYiSo1LOvwvB78IwXyuQbIvsrYgLRjlCUhie6c9qDba6WbOjjbCvWOF9Pdp3M4J8GNiNW4K_SMinbXT9Ez8LpV6b-sdDSAftDrO1iOm422uW_-HLd-mQW7JuhbO3y2t3eel-RIilMbTmnb-oAZVumvvA7a9nnUoVe6HGeP1CTmDoWmAG42d0-sdE96KXYJBRbHhtdLDewT_7xV5H5L6vysTiLg8uSLUhdcKvHqxxZVac7qDelalmCpPvcQ").toString());
    }
}
