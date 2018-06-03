package com.zz.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Cloth
{
	void product();
}

//被代理类
class Anta implements Cloth
{

	@Override
	public void product() {
		System.out.println("ANTA生产的衣服");
		
	}	
}

//生成被代理类的代理对象
class MyInvocation implements InvocationHandler
{
	//实现了接口的被代理类的对象的声明
	Object obj;
	//①给被代理的对象实例化②返回一个代理类的对象
	public Object blind(Object obj)
	{
		this.obj = obj;	
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), 
				obj.getClass().getInterfaces(), this);
	}
		
	//当通过代理类的对象发起对被重写的方法的调用时,都会转换为对如下invoke方法的调用
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object returnValue = method.invoke(obj, args);
		return returnValue;
	}
	
}

public class TestDynamicProxy
{
	public static void main(String[] args) 
	{
		//1.被代理类的对象
		Anta anta = new Anta();
		//2.创建一个实现了InvacationHandler接口的类的对象
		MyInvocation mi = new MyInvocation();
		//3.调用blind()方法,动态的返回一个同样实现了anta所在类实现的接口Cloth的代理类的对象
		Object obj = mi.blind(anta);
		//此时Cloth就是代理类对象
		Cloth a = (Cloth) obj;
		//转到对InvacationHandler接口的实现类的invoke()方法的调用
		a.product();
	}
}
