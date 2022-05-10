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
    	try {
    		/**
    		 * https://www.heapdump.cn/article/2067679《无异常日志，就不能排查问题了？？？》
    		 * 套路二
    		 * 我们知道idea里面有很多好用的功能，比如肥朝之前的【看源码，我为什么推荐IDEA?】中就提到了条件断点，除此之外，还有一个被大家低估的功能，叫做异常断点。
    		 * 案例二
    		 * 我们先在看之前肥朝粉丝群的提，虑到部分粉丝不在群里，我就简单描述一下这个粉丝的问题，他代码有个异常，然后catch打异常日志，但是日志却没输出。
    		 * 当然你还是不理解也没关系，我根据该粉丝的问题，给你搭建了一个最简模型的demo，模型虽然简单，但是问题是同样的，原汁原味，熟悉的配方，熟悉的味道。
    		 * git地址（https://gitee.com/HelloToby/springboot-run-exception），我们运行起来看一下。
    		 * 你会发现，一运行起来进程就停止，一点日志都没。绝大部分假粉丝遇到这个情况，都是菊花一紧，一点头绪都没，又去群里问”你们有没有遇到过，Springboot一起来进程就没了，但是没有日志的问题？“。正确提问姿势肥朝已经强调过，这里不多说。那么我们用前面学到的排查套路，再来走一波
    		 * 我们从代码中看出两个关键单词【reportFailure】、【context.close()】，经过断点我们发现，确实是会先打印日志，再关掉容器。但是为啥日志先执行，再关掉容器，日志没输出，容器就关掉了呢？因为，这个demo中，日志是全异步日志，异步日志还没执行，容器就关了，导致了日志没有输出。
    		 * 该粉丝遇到的问题是类似的，他是单元测试中，代码中的异步日志还没输出，单元测试执行完进程就停止了。知道了原理解决起来也很简单，比如最简单的，跑单元测试的时候末尾先sleep一下等日志输出。
    		 * 在使用Springboot中，其实经常会遇到这种，启动期间出现异常，但是日志是异步的，日志还没输出就容器停止，导致没有异常日志。知道了原理之后，要彻底解决这类问题，可以增加一个SpringApplicationRunListener。
    		 * 再啰嗦一句，其实日志输出不了，除了这个异步日志的案例外，还有很多情况的，比如日志冲突之类的，排查套路还很多，因此，建议持续关注，每一个套路，都想和你分享！
    		 */
    		logger.info("日志打印");
        	SpringApplication.run(App.class, args);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} catch (Error e) {
    		e.printStackTrace();
    	} catch (Throwable e) {
    		e.printStackTrace();
    		//写到Spring文档里面去，我记得微信之前看过一篇文章，说SpringBoot的日志是异步打印的,报错之后来不及打印JVM就退出了，所以看不到报错日志信息，晚上微信上面找一下。
    	}
    	try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
