//package com.example.bank;
//
//import com.example.bank.api.DrawMoneyApi;
//import com.example.bank.request.DrawMoneyRequest;
//import com.example.bank.response.DrawMoneyResponse;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
//import static org.hibernate.validator.internal.util.Contracts.assertTrue;
//
//@SpringBootTest
//@Transactional
//@Rollback(value = false)
//public class TestDrawMoney {
//
//    @Autowired
//    private DrawMoneyApi drawMoneyApi;
//
//    @Test
//    public void testToDrawMoney(){
//        DrawMoneyRequest drawMoneyRequest = new DrawMoneyRequest("232",21f);
//
//        DrawMoneyResponse drawMoneyResponse = drawMoneyApi.execute(drawMoneyRequest);
//
//        assertNotNull(drawMoneyResponse);
//    }
//}
