package com.xz.config;

import com.xz.entity.TestBean;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author xz
 * @since 2024/9/24 09:13
 */
@Configuration
public class ConditionConfig {
    
    @Bean("testB")
    @Conditional(TestAnyNestCondition.class)
    public String test() {
        return new String("test");
    }
    
    /**
     * AnyNestedCondition 嵌套条件, 其中任何一个条件满足则通过
     * AllNestedCondition 所有条件都满足才会创建
     * NoneNestedConditions 所有条件都不满足才会创建
     */
    private static class TestAnyNestCondition extends AnyNestedCondition {
        
        public TestAnyNestCondition() {
            // 条件只会影响注册bean, 不会影响@Configration
            super(ConfigurationPhase.REGISTER_BEAN);
        }
        
        @ConditionalOnProperty("test.enabled")
        static class PropertiesExists {}
        
        @ConditionalOnBean({TestBean.class})
        static class BeanExists {}
    }
    
}
