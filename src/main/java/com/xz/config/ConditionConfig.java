package com.xz.config;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.xz.entity.TestBean;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.crypto.spec.IvParameterSpec;

/**
 * @author xz
 * @since 2024/9/24 09:13
 */
@Configuration
public class ConditionConfig {
    public static void main(String[] args) {
        // 密钥
        String key = "zogeTdF2g5nWS4xPlObe7A==";
        System.out.println(key.getBytes().length);
        // 偏移量
        String offsetKey = "8CpLfxzXCRNIkEZRRePqOg==";
        SymmetricCrypto s = new SymmetricCrypto("SM4", SecureUtil.generateKey("SM4", key.getBytes()), new IvParameterSpec(offsetKey.getBytes()));
        String data = "GwwfTFGLdM6DEShOEODM0r6hITwmSpjaLiCCf18LCoBpKAYIJ443W/u5oKO+VmyrpmMdCe6373JKABSkenOl9l+IP8Lm/TmpyzRSbJXz1pLYrN8kNa62kwqaD2vYvI/A8YGFtQXt3WKoAhV10iAZ6k4Og3tqPtPT0DmAVRsQr193ixapnsdQjjeW7UtBYyT1WXmGe1KghGE4Ht3q6dANX/0Qqxmpzm8FBKQEVXYRpT7y2tutsHZCjw9RYxbyPpAGT/kwfgj2Wonmakh0Tj1nOBJeZfnRfHP1xQ50L5LbR/nH4acsvHKzkJHRrrSrouK/0FmK8lhgpfYiotEgei1HGb/pk+5rudcHjc1wD09sWRZsjPj7gOygR4YqMo9gI+QnNcuExTYC6X/lV4HLQreh1ftaSKp1T9OzHQ/BQgybFChRn9nf8g3dvKj6GVUib/F1eNT/QQC+y7LzHQgeB35UsKU16S61HE9Ysdf6KDen+FvG26qb+xZwJ95RDoYbEQovZQjt4OrxbKYLc4mGEmlKrOxpzXCfL+ERDxEgXgMp0g3eoLH/ct0Kt9Bh2xwqfetj1J1XOO09P1GOtzuxyGhAtnFrmpLtfzt5YWH4wU9ZaOGOS1C6nOjArW5Ed8OokdWGbtCEWOxcLAHiZ8aEU1DkOn2FsMwR15ynOqalJ4uiEevVv/KgTOZKEn16B/q6Z8ui1iQxWAYb8ho/iLRB1NtsxwEJLlebrhSzplu6oP4oxU74bLdzx6xMyQ7tKVAl1z8otkDfJtqj23E2+AlluVEmCR8FxQDEh3xP8WJfVrb+pUtA5p00FIC0UFWw//69zVPpmE2ct1Sn6PeyH8Z40Dbuvg8Q/dQIvqBJ21x+HSmP5qPdAYRLp/bo3mecYgDZa/55G7Q+3fNpZfnrw04RQ6+eLE4E0Ox8IRZeTdy/FPZ6OnjUfWiSDBgMqaoYzhcVHoUZgF4t6vJ2OrqW6Tgg77eVCS4Aqy1Ev9hvzjD5oX3X5DS24W54BXZxgjUfkhrAztttykzDnTSvW+OuXif4WGoJdC9+RGMsMIUqfmlXjWcBWD3G1ktKnEAZ+EPipqHGBDOkJINAoUCF9j2+O0UPJqrIFeH7i/GLUz5cJeAk0gLk5mN0aCw3IKzJrOgG3Wkufau+6rAbtTIOkzBobZwFkRNZj/2dBsjWUwJWnZU4ZCcpeY2aZczNjUrDl9Vk1lhRca9c3/6/7auPNpwW11NrM2yuklz8p6nsoL+4ublESCX8IDdkXg/8wntrIGFNy+F9JspDNJIOq6KV5jA8XLD0t7+xz5P1ciQdmiC40z4XcgLpQGkX6CzA9hnO8ztqJ9bdsTmRFAadgWsvr6zc7XOVHtYGQ10tl9NhiYURVaGur8n7ihPTUiMhWdqYrAob1XheMuTI49KiIp8IIH3Ebo1pU5Wq0XRLEG+e1X8Gzh6WI3vN2TtpL1K8kH4E2CrKb3m5x0yWHRbsyJciqZgzJg5VWc+k5HfOnZaPblLs0s2r78ili8da71/wKbXWGiupNRoajHJoFPs32AP+9fqnoUQR4qVFxF/BcH+ePwIZL6cC0cVGKrqPy2ZDCXLErGMz6n/fYA+x2LWbBOdSqH01kYd8Dznr4h2iANhwWbmgXHFWrIkyViw9GVOdANhpRtAS4pXO+qyHfpP34i63CJt6QvXCDGKaIJAYBzwdsyDUQr39xlKNj7PPcOms4EXk1e+ec3YKHYV3giwX9nsa0j+TrUOC6wqERa9CoPEgsU1n7HiKVFC/nsiuIHjNXk8Em0S3oUzCkbwPyBxoFsV9yN8vpoWoFgMsh4sQPmxEh3rprGRjnYDVcBkrvvI85a03X53spDyQK8PO/lHLB06lDj32Cc5Ss4X8L/YtLHrRkyTXF9UQEBAYNimskOLGwnGKezVt6L5/1u0SrRIcZUfGS+IZ8QKDVH6/5g==";
        System.out.println(s.decrypt(data));
    }
    
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
