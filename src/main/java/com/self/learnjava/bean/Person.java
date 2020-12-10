package com.self.learnjava.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Email;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/*
 *将配置文件中配置的每一个属性的值,映射到这个组件当中
 * @ConfigurationProperties告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定,
 * prefix="person"的意思是指定跟配置文件中的person下面的所有属性进行一一映射
 * 除了使用@ConfigurationProperties读取配置文件的内容,还可以使用@Value
 * ConfigurationProperties还支持JSR303数据校验(需要添加注解@Validated),@Value不支持JSR303数据校验
 * @ImportResource:导入Spring的配置文件,让配置文件里面的内容生效
 * SpringBoot推荐使用这种方式给容器添加组件,全部使用@Bean注解来添加,不要再编写xml配置文件了。比如MyAppConfig
 * Profile
 * 我们在配置文件编写的时候,文件名可以是application-{profile}.properties/yml,默认是用application.properties的配置
 * YML文档块模式,YML文件里面可以使用---三个横杠来区分不同环境的变量
 * 
 */
@Component//只有容器中的组件,才能使用容器提供的功能,所以这里需要加@component
@ConfigurationProperties(prefix="person")//默认从全局配置文件application.yml或者application.properties中获取值
@Validated
//@PropertySource(value={"classpath:person.properties"})//@PropertySource可以加载指定路径的配置文件,需要跟@ConfigurationProperties搭配使用
public class Person {
	
	/*
	 * 我们最开始使用xml配置Spring的时候是这样写代码的
	 * <bean class="Person">这里的bean就相当于@Component，value相当于SpringBoot的@Value
	 * 		<property name="lastName" value="1，字面量2，/${key}从环境变量或者配置文件中获取值,3./#{SPEL}spring的spel表达式"/>
	 * </bean>
	 *@Value 
	 *lastName等价于last-name或者last_name也可以,-代表后面的字母大写,这个叫松散绑定,@ConfigurationProperties支持松散绑定,@Value不支持
	 *@Value${必须跟配置文件里面的定义的名字一样,不支持松散绑定}
	 */
	//@Value("${person.last-name}")
	//@Email//配合@Validated可以对lastName的值进行校验,lastName必须填成邮箱的格式.
	private String lastName;
	//@Value("#{11*2}")//SpringBoot可以自己计算出来,把22写进去,@Value支持SpEL表达式,@ConfigurationProperties不支持SpEL表达式
	private Integer age;
	//@Value("true")
	private Boolean boss;
	private Date birth;
	//@Value("person.maps")//@Value不支持复杂类型封装,比如这个map属性@Value就获取不到
	private Map<String, Object> maps;
	private List<Object> lists;
	private Dog dog;
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Boolean getBoss() {
		return boss;
	}
	public void setBoss(Boolean boss) {
		this.boss = boss;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Map<String, Object> getMaps() {
		return maps;
	}
	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}
	public List<Object> getLists() {
		return lists;
	}
	public void setLists(List<Object> lists) {
		this.lists = lists;
	}
	public Dog getDog() {
		return dog;
	}
	public void setDog(Dog dog) {
		this.dog = dog;
	}
	@Override
	public String toString() {
		return "Person [lastName=" + lastName + ", age=" + age + ", boss=" + boss + ", birth=" + birth + ", maps="
				+ maps + ", lists=" + lists + ", dog=" + dog + "]";
	}
}
