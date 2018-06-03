package com.zz;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

/**
 * 通过反射获取类的完整结构
 * @author lenovo
 *
 */
public class TestGetClassAllConstruct {
	
	//获取对应运行时类的属性
	@Test
	public void testField()
	{
		Class<Person1> clazz = Person1.class;
		
		//1. getFields():只能获取到运行时类中及其父类中声明为public的属性
		Field [] fields = clazz.getFields();
		for(int i = 0;i < fields.length; i++)
		{
			System.out.println(fields[i]);
		}
		
		System.out.println();
		
		//2. getDeclaredFields():只能获取到运行时类本身声明的所有的属性
		Field [] fields1 = clazz.getDeclaredFields();
		for(int i = 0 ;i < fields1.length; i++)
		{
			System.out.println(fields1[i]);
		}
		
/*		public java.lang.String com.zz.Person1.name
		public double com.zz.Creature.weight

		public java.lang.String com.zz.Person1.name
		private int com.zz.Person1.age
		int com.zz.Person1.id*/
	}
	
	//权限修饰符,变量类型,变量名
	//获取属性各个部分的内容
	@Test
	public void testFieldAllAttr()
	{
		Class<Person1> clazz = Person1.class;

		Field [] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			//1.获取每个属性的权限修饰符
			int i = field.getModifiers();
			String f = Modifier.toString(i);
			System.out.print("修饰符: "+f);
			
			//2.获取属性的变量类型
			Class type = field.getType();
			System.out.print(" 类型: " + type);
			
			//3.获取属性名
			System.out.println(" 属性名:" + field.getName());
			
/*			修饰符: public 类型: class java.lang.String 属性名:name
			修饰符: private 类型: int 属性名:age
			修饰符:  类型: int 属性名:id*/
		}
	}
	
	//获取运行时类的方法
	@Test
	public void testMethod()
	{
		Class clazz = Person1.class;
		
		//1. getMethods():获取运行时类及其父类中所有声明为public的方法
		Method [] m1 = clazz.getMethods();
		for (Method method : m1) {
			System.out.println(method);
		}
		
		System.out.println();
		
		//2.getDeclaredMethods():获取运行时类本身声明的所有方法
		Method [] m2 = clazz.getDeclaredMethods();
		for (Method method : m2) {
			System.out.println(method);
		}
		
/*		public java.lang.String com.zz.Person1.toString()
		public int com.zz.Person1.compareTo(java.lang.Object)
		public java.lang.String com.zz.Person1.getName()
		public void com.zz.Person1.setName(java.lang.String)
		public void com.zz.Person1.setInt(int)
		public void com.zz.Person1.show()
		public int com.zz.Person1.getAge()
		public static void com.zz.Person1.staticMethod()
		public void com.zz.Creature.sing()
		public final void java.lang.Object.wait() throws java.lang.InterruptedException
		public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
		public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
		public boolean java.lang.Object.equals(java.lang.Object)
		public native int java.lang.Object.hashCode()
		public final native java.lang.Class java.lang.Object.getClass()
		public final native void java.lang.Object.notify()
		public final native void java.lang.Object.notifyAll()

		public java.lang.String com.zz.Person1.toString()
		public int com.zz.Person1.compareTo(java.lang.Object)
		public java.lang.String com.zz.Person1.getName()
		public void com.zz.Person1.setName(java.lang.String)
		public void com.zz.Person1.setInt(int)
		private void com.zz.Person1.display(java.lang.String) throws java.lang.Exception
		public void com.zz.Person1.show()
		public int com.zz.Person1.getAge()
		public static void com.zz.Person1.staticMethod()*/
	}
	
	//注解 权限修饰符  返回值类型 方法名  形参列表 异常
	@Test
	public void testMethodAllAttr(){
		Class clazz = Person1.class;

		Method [] m2 = clazz.getDeclaredMethods();
		for (Method method : m2) {
			//1. 注解
			Annotation [] annotation = method.getAnnotations();
			for (Annotation annotation2 : annotation) {
				System.out.println("注解: "+annotation2);
			}
			
			//2.权限修饰符
			int i = method.getModifiers();
			String j = Modifier.toString(i);
			System.out.print(" 权限修饰符: "+j);
			
			//3.返回值类型
			Class returnType = method.getReturnType();
			System.out.print(" 返回值类型: "+returnType.getName());
			
			//4.方法名
			System.out.print(" 方法名是: "+method.getName());
			
			//5.形参列表
			Class[] paras = method.getParameterTypes();
			System.out.print("(");
			for (Class class1 : paras) {
				System.out.print(" "+ class1.getName());
			}
			System.out.println(")");
			//6.异常
			Class[] exceps = method.getExceptionTypes();
			if(exceps.length != 0)
			{
				System.out.print("异常: throws ");
			}
			for (Class class1 : exceps) {
				System.out.print(" "+class1.getName());
			}
			System.out.println();
			System.out.println("==============================");
			
			
/*			 权限修饰符: public 返回值类型: java.lang.String 方法名是: toString()

			 ==============================
			  权限修饰符: public 返回值类型: int 方法名是: compareTo( java.lang.Object)

			 ==============================
			  权限修饰符: public 返回值类型: java.lang.String 方法名是: getName()

			 ==============================
			  权限修饰符: public 返回值类型: void 方法名是: setName( java.lang.String)

			 ==============================
			  权限修饰符: public 返回值类型: void 方法名是: setInt( int)

			 ==============================
			  权限修饰符: public 返回值类型: int 方法名是: getAge()

			 ==============================
			 注解: @com.zz.MyAnnotation(value=I_am_a_man)
			  权限修饰符: public 返回值类型: void 方法名是: show()

			 ==============================
			  权限修饰符: private 返回值类型: void 方法名是: display( java.lang.String)
			 异常: throws  java.lang.Exception
			 ==============================
			  权限修饰符: public static 返回值类型: void 方法名是: staticMethod()

			 ==============================*/

		}
	}
	
	//获取构造器
	@Test
	public void testConstructor(){
		Class clazz = Person1.class;
		Constructor [] c = clazz.getDeclaredConstructors();
		for (Constructor constructor : c) {
			System.out.println(constructor);
		}
		
/*		public com.zz.Person1(java.lang.String,int)
		public com.zz.Person1(java.lang.String)
		public com.zz.Person1()*/
	}
	
	//获取父类
	@Test
	public void testSuperClass(){
		Class clazz = Person1.class;
		
		//1.获取运行时类的父类
		Class superClass = clazz.getSuperclass();
		System.out.println(superClass);
		
		//2.获取带泛型的父类
		Type type = clazz.getGenericSuperclass();
		System.out.println(type);
		
		//3.获取父类的泛型
		ParameterizedType param = (ParameterizedType)type;
		Type[]  args = param.getActualTypeArguments();
		System.out.println(((Class)args[0]).getName());
		
/*		class com.zz.Creature
		com.zz.Creature<java.lang.String>
		java.lang.String*/
	}
	
	//获取实现的接口
	@Test
	public void testImpInterface()
	{
		Class clazz = Person1.class;
		
		Class[] interfaces = clazz.getInterfaces();
		for (Class c : interfaces) {
			System.out.println(c);
		}
		
/*		interface java.lang.Comparable
		interface com.zz.MyInterface*/
	}
	
	//获取注解
	@Test
	public void testAnnotation()
	{
		Class clazz = Person1.class;
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println(annotation);
		}
		
//		@com.zz.MyAnnotation(value=my_annotation)
	}
}
