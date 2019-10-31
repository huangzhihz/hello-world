package honstat.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
// RetentionPolicy.SOUCE java源文件编译 用于检查  RetentionPolicy.ClASS class文件中jvm加载时候去除 用于生成辅助代码  
//一般如果需要在运行时去动态获取注解信息，那只能用 RUNTIME 注解，比如@Deprecated使用RUNTIME注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JCAutoWirted {
}
