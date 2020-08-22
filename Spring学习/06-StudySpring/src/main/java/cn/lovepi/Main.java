package cn.lovepi;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class Main {
    public static void main( String[] args ) {

        //使用FileSystemResource来读取配置文件
        Resource r=new FileSystemResource("helloMessage.xml");
        //使用XmlBeanFactory加载配置信息，启动IOC容器
        //Spring3.1之后已经废弃XmlBeanFactory
        //BeanFactory b=new XmlBeanFactory(r);
        DefaultListableBeanFactory factory=new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(r);
        //从IOC容器中获取Person类的实例
        //Person person=(Person)b.getBean("Person");
        Person person= (Person) factory.getBean("Person");
        //使用person类示例来输出问候信息
        String s=person.sayHello();
        System.out.println("The Person is currently saying "+s);
    }
}