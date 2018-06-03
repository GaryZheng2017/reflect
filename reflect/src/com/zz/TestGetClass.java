package com.zz;

import org.junit.Test;

/**
 * 获取Class的四种方式
 * @author lenovo
 *
 */
public class TestGetClass {
	
	/**
	 * java.lang.Class:是反射的源头。
	 * 我们创建了一个类，通过编译（javac.exe）,生成对应的.class文件。之后我们使用java.exe加载（JVM类加载器完成）
	 * 此.class文件，此.class文件加载到内存以后，就是一个运行时类，存放在缓存区。那么这运行时类本身就是一个
	 * class的实例。
	 * 	1.每一个运行时类只加载一次
	 * 	2.有了Class实例以后,我们才可以进行如下操作
	 * 		1)*创建对应运行时类的对象
	 * 		2)获取对应运行时类的完整结构(属性,方法,构造器,内部类,父类,所在的包,异常,注解....)
	 * 		3)*调用对应的运行时类的指定的结构(属性、方法、构造器)
	 * 		4)反射的应用,动态代理
	 */
	
	
	
	//方式一:调用运行时类本身的.class属性
	@Test
	public void test1() throws Exception{
		Class clazz = Person.class;
		Object object = clazz.newInstance();
		Person p = (Person)object;
		p.setName("zz");
		p.setSalary("13K");
		System.out.println(p);
		/*运行结果:
		 * Person [name=zz, age=0, gender=null, address=null, salary=13K]
		 * */
	}
	
	//方式二:通过运行时类的对象获取
	@Test
	public void test2(){
		Person p = new Person();
		Class clazz = p.getClass();
		System.out.println(clazz);
	}
	
	//方式三:通过Class的静态方法获取
	@Test
	public void test3() throws Exception
	{
		String className = "com.zz.Person";
		Class clazz = Class.forName(className);
		Object object = clazz.newInstance();
		Person p = (Person)object;
		p.setName("zz");
		p.setAge(35);
		p.setSalary("2000K");
		System.out.println(p);
		
	}
	
	//方式四:通过ClassLoader加载器获取
	@Test
	public void test4() throws Exception{
		String className = "com.zz.Person";
		ClassLoader cl = this.getClass().getClassLoader();
		Class clazz = cl.loadClass(className);
		Person p = (Person)clazz.newInstance();
		p.setName("zz");
		p.setAge(40);
		p.setGender("男");
		p.setAddress("nanjing");
		p.setSalary("10000K");
		System.out.println(p);
	}
}
