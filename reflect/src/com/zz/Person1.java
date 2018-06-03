package com.zz;

@MyAnnotation(value = "my_annotation")
public class Person1 extends Creature<String> implements Comparable,MyInterface{
	public String name;
	private int age;
	int id;
	
	//创建类时,尽量保留一个空参构造器
	public Person1()
	{
		super();
	}
	
	public Person1(String name)
	{
		this.name = name;
	}
	
	public Person1(String name,int age)
	{
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public void setInt(int age)
	{
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person1 [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
	
	@MyAnnotation(value = "I_am_a_man")
	public void show()
	{
		System.out.println("我是一个人");
	}
	
	private void display(String address) throws Exception
	{
		System.out.println("我住在: "+address);
	}
	
	class Sister
	{
		
	}
	
	public static void staticMethod()
	{
		System.out.println("这是一个静态方法!!!");
	}
 
}
