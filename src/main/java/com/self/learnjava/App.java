package com.self.learnjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 点击@SpringBootApplication查看源码发现这个是组合注解,里面有一个@SpringBootConfiguration，
 * @SpringBootConfiguration标注在某个类上，说明这是一个SpringBoot的配置类;
 * @SpringBootConfiguration这个注解上面又有@Configuration这个注解，表示这是一个配置类
 * 配置类等于之前的配置文件，
 * Configuration这个注解上面又有@Component,说明配置类也是一个组件
 * @EnableAutoConfiguration 开启自动配置功能,@EnableAutoConfiguration这个注解上面有 @AutoConfigurationPackage 和 @Import(AutoConfigurationImportSelector.class)
 * @Import的意思是给容器中导入一个组件
 * 而AutoConfigurationImportSelector 这个东西的作用就是将配置类(@SpringBootApplication标志的类)的所在包及下面所有子包里面的所有组件扫描到Spring容器
 * 
 * resources文件夹中目录结构
 *  static:保存所有的的静态资源:js css images
 *  templates:保存所有的模板页面;SpringBoot默认打成jar包使用嵌入式的tomcat,默认不支持JSP页面;但是可以使用模板引擎(freemarker,thymeleaf)
 *  application.properties:SpringBoot应用的配置文件;可以修改一些SpringBoot的默认设置
 * SpringBoot默认使用以下俩个配置文件,配置文件的名称是固定的:必须叫application
 * application.properties
 * application.yml
 * 配置文件的的作用:修改SpringBoot自动配置的默认值
 * YAML(YAML Ain't Markup Language):是一个标记语言
 * YAML isn't Markup Language:不是一个标记语言
 * YAML:以数据为中心，比json，xml等更适合做配置文件
 * YAML语法:k:空格v 表示一对键值对(冒号后面的空格必须有),以空格的缩进来控制层级关系,多少个空格无所谓,只要是左对齐的一列数据都是同一层级的，属性和值也是大小写敏感的。
 * YAML值的写法：
 * 1、字面量:普通的值(数字,字符串,布尔)
 * k: v:字面直接来写,注意字符串默认不用加上单引号或者双引号;"":双引号不会转义字符串里面的特殊字符,比如\n 就是换行的意思;''单引号:会转义特殊字符,特殊字符最终只是一个普通的字符串数据,比如\n就是字符串\n。
 * 2、对象,Map(属性和值)(键值对):
 * 		对象还是k: v的方式,在下一行来写对象的属性和值的关系,注意缩进就行
 * 		obj:
 * 		  name: zhangsan
 * 		  age: 20
 * 		对象行内写法：obj: {name: zhangsan, age: 20}
 * 3,数组(List, Set):用-值表示数组中的一个元素
 * pets: 表示pets这个数组里面有三个元素
 * 	- cat
 *  - dog
 *  - pig
 * 行内写法:pets: [cat, dog, pig]
 * http://nodeca.github.io/js-yaml/ yaml语法校验
 */
//@ImportResource(locations={"classpath:beans.xml"})//导入Spring的xml配置文件,让其生效
@SpringBootApplication
public class App 
{
	static Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
    	logger.info("日志打印");
    	SpringApplication.run(App.class, args);
    }
}
