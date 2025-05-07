//package com.xz;
//
//import com.xz.controller.CloudController;
//import jakarta.annotation.Resource;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.mockito.internal.verification.AtLeast;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.junit.jupiter.api.Assertions.assertEquals;
///**
// * @author xz
// * @since 2024/8/14 15:25
// */
//@SpringJUnitConfig(Main.class)
//public class MainTest {
//    @Resource
//    CloudController controller;
//    @Test
//    public void testSpring() {
////        Object res = controller.getProperties();
////        // 校验某个接口返回
////        assertTrue(res == null);
//    }
//
//    @Test
//    public void testMockito() {
////        final List<String> testList = Mockito.mock(List.class);
////        testList.add("");
//////        testList.add("");
////        // 校验调用了一次方法 add("");
////        verify(testList).add("");
////        // 至少调用几次方法
//////        verify(testList, new AtLeast(2)).add("");
////
////        // 设置某个方法的结果(测试对象需要依赖某个对象调用时, 可以mock出来假数据)
////        when(testList.get(0)).thenReturn("123");
////        assertEquals("123", testList.get(0));
//    }
//}
