package com.StaticProxyTest;

/**
 * @BelongsProhect:静态代理举例
 * @BelongsPackage:com.StaticProxyTest
 * @Auther:Chen
 * @CreateTime:2022--07--13 13--49
 * @Decription:
 */
interface ClothFactory{
    void produceCloth();
}

//代理类
class ProxyClothFactory implements ClothFactory {
    private ClothFactory factory;//用被代理类对象进行实例化

    public ProxyClothFactory(ClothFactory factory){
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做准备工作...");
        factory.produceCloth();
        System.out.println("代理工厂做收尾工作...");
    }
}

//被代理类
class NikeClothFactory implements ClothFactory{
    @Override
    public void produceCloth(){
        System.out.println("Nike工厂生产运行裤...");
    }
}


public class staticProxyTest{
    public static void main(String[] args) {
        //创建被代理类对象
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        //创建代理类对象
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nikeClothFactory);

        proxyClothFactory.produceCloth();

    }
}
