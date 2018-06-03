package com.zz.proxy;

//静态代理模式
//接口
interface ClothFactory
{
	void productCloth();
}

//被代理类
class NikeClothFactory implements ClothFactory
{
	@Override
	public void productCloth() {
		System.out.println("NIKE工厂生产的衣服");
	}
}

//代理类
class ProxyFactory implements ClothFactory
{
	ClothFactory cf;
	
	//创建代理类的对象时,实际传入一个被代理类的对象
	public ProxyFactory(ClothFactory cf)
	{
		this.cf = cf ;
	}
	
	@Override
	public void productCloth()
	{
		System.out.println("代理类开始执行");
		cf.productCloth();
	}
}

//静态代理
public class TestStaticProxy {
	public static void main(String[] args) {
		NikeClothFactory nike = new NikeClothFactory();
		ProxyFactory pf = new ProxyFactory(nike);
		pf.productCloth();
	}

}
