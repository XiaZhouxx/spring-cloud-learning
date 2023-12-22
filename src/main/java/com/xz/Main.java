import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author xz
 * @since 2023/12/21 11:02
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // 类型推断
        var str = "abc";
        // 多行字符格式
        var str2 = """
                abc
                123
                4556
                """;
        System.out.println(str2);
        // switch表达式
        var str3 = switch (str) {
            case "abc", "efd", "123" -> "abc";
            default -> "default";
        };
        // instanceof模式匹配
        Object obj = Integer.valueOf(1);
        if (obj instanceof Integer s) {
            System.out.println(s);
        }

        ConfigurableApplicationContext run = new SpringApplication(Main.class).run(args);
    }
}