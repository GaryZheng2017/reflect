package com.zz;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 通过反射调用类的属性和方法
 * @author lenovo
 *
 */
public class TestReflectUseAttrAndMethodAndConstrut {
	//调用运行时类的指定属性
	@Test
	public void testAttr() throws Exception
	{
		Class clazz = Person1.class;
		//1.getField():获取指定的属性(public修饰的)
		Field name = clazz.getField("name");
		//2.创建运行时类对象
		Person1 p = (Person1) clazz.newInstance();
		System.out.println(p);
		//3.将运行时类的指定的属性赋值
		name.set(p, "slam");
		System.out.println(p);
		
		//4.getDeclaredField():获取指定的属性(private修饰的)
		Field age = clazz.getDeclaredField("age");
		age.setAccessible(true);//private要声明此
		age.set(p, 29);
		System.out.println(p);
		
		/*	运行结果:
				Person1 [name=null, age=0]
				Person1 [name=slam, age=0]
				Person1 [name=slam, age=29]
				*/
	}
	
	//调用运行时类指定的方法
	@Test
	public void testMethod() throws Exception{
		Class clazz = Person1.class;
		//1.getMethod():获取运行时类中声明为public
		Method m1 = clazz.getMethod("show");
		Person1 p = (Person1) clazz.newInstance();
		//2.调用指定的方法,Object invoke(Object obj,Object ... obj)
		m1.invoke(p);
		
		//有返回值
		Method m2 = clazz.getMethod("toString");
		Object returnValue = m2.invoke(p);
		System.out.println(returnValue);
		
		//3.调用静态的方法
		Method m3 = clazz.getMethod("staticMethod");
		m3.invoke(p);
		
		//4.getDeclaredMethod():调用运行时类声明的指定方法
		Method m4 = clazz.getDeclaredMethod("display",String.class);
		m4.setAccessible(true);
		m4.invoke(p,"nanjing");
		
/*	运行结果:
		我是一个人
		Person1 [name=null, age=0]
		这是一个静态方法!!!
		我住在: nanjing*/
		
	}
	
	//调用指定的构造器
	@Test
	public void testConstruct() throws Exception
	{
		Class clazz = Person1.class;
		Constructor c = clazz.getDeclaredConstructor(String.class,int.class);
		Person1 p = (Person1) c.newInstance("slam",29);
		System.out.println(p);
//		Person1 [name=slam, age=29]
	}
}
