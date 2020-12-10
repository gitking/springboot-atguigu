package com.self.learnjava.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.self.learnjava.bean.Department;

/*
 *  SpringBoot使用MyBatis非常简单,引入jar包,然后编写这个Mapper,然后就可以在DeptController里面直接使用了
 * MyBatis的自定义配置
 * 当com.self.learnjava.mapper这个包下面的类越来越多,需要给每一个类都添加一个@Mapper注解,这太麻烦了,
 * 可以使用@MapperScan这个注解,让他去扫描com.self.learnjava.mapper下面的所有接口,这样就不用给每一个类添加@Mapper了
 * @MapperScan这个注解可以添加在MyBatisConfig这个MyBatis的配置类上面,也可以添加在SpringBootApplication的主类上面
 */
//@Mapper//指定这是一个操作数据库的mappper,有了@MapperScan这个注解,这里的类就不用添加@Mapper了
public interface DepartmentMapper {
	
	@Select("select * from department where id=#{id}")
	public Department getDeptById(Integer id);
	
	@Delete("delete from department where id=#{id}")
	public int deleteDeptById(Integer id);
	
	//department的id是自增的,不用插入
	@Options(useGeneratedKeys=true,keyProperty="id")//使用自增主键
	@Insert("insert into department(departmentName) values(#{departmentName})")
	public int insertDept(Department department);
	
	@Update("update department set departmentName=#{departmentName} where id=#{id}")
	public int updateDept(Department department);
}
