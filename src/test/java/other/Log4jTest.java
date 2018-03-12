package other;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

/**
 * Java 应用之日志框架 log4j
 * Apache的开源项目log4j是一个功能强大的日志组件,提供方便的日志记录。
 * 参考链接：
 * http://how2j.cn/k/log4j/log4j-tutorial/1081.html
 * Log4j详细使用教程:http://blog.csdn.net/evankaka/article/details/45815047
 * http://wiki.jikexueyuan.com/project/log4j/configuration.html
 * Created by Qian on 2018/3/11 0011.
 */
public class Log4jTest {

    /**
     * 写代码的过程中，免不了要输出各种调试信息。
     * 在没有使用任何日志工具之前，都会使用 System.out.println 来做到。
     * 缺点：
     *  1. 不知道这句话是在哪个类，哪个线程里出来的
     *  2. 不知道什么时候前后两句输出间隔了多少时间
     *  3. 无法关闭调试信息，一旦System.out.println多了之后，到处都是输出，增加定位自己需要信息的难度等等
     */
    @Test
    public void test(){
        System.out.println("跟踪信息");
        System.out.println("调试信息");
        System.out.println("输出信息");
        System.out.println("警告信息");
        System.out.println("错误信息");
        System.out.println("致命信息");
    }

    //1、基于类的名称获取日志对象
    static Logger logger = Logger.getLogger(Log4jTest.class);//ps：getRootLogger()返回应用实例没有名字的根日志
    /**
     * 使用Log4j来进行日志输出
     * BasicConfigurator.configure()： 自动快速地使用缺省Log4j环境。
     * PropertyConfigurator.configure(String configFilename) ：读取使用 Java的特性文件编写的配置文件。(properties)
     * DOMConfigurator.configure(String filename)：读取XML形式的配置文件。
     *
     * 输出结果有几个改观：
     * 1. 知道是other.Log4jTest这个类里的日志
     * 2. 是在[main]线程里的日志
     * 3. 日志级别可观察，一共有6个级别 TRACE DEBUG INFO WARN ERROR FATAL
     * 4. 日志输出级别范围可控制， 如代码所示，只输出高于DEBUG级别的，那么TRACE级别的日志自动不输出
     * 5. 每句日志消耗的毫秒数(最前面的数字)，可观察，这样就可以进行性能计算
     */
    @Test
    public void testLog4j() throws InterruptedException {
        //2、 进行默认配置
        BasicConfigurator.configure();
        //3、设置日志输出级别
        logger.setLevel(Level.DEBUG);
        //4、 进行不同级别的日志输出
        logger.trace("跟踪信息");
        logger.debug("调试信息");
        logger.info("输出信息");
        Thread.sleep(1000);//为了便于观察前后日志输出的时间差
        logger.warn("警告信息");
        logger.error("错误信息");
        logger.fatal("致命信息");
    }

    /**
     * log4j配置
     * 在src目录下添加log4j.properties文件
     */
    @Test
    public void testLog4jConfig(){
        //2、采用指定配置文件
        PropertyConfigurator.configure(".\\src\\test\\resources\\log4j.properties");//Log4j的配置方式按照log4j.properties中的设置进行
        for (int i = 0; i < 5000; i++) {
            logger.trace(i+"跟踪信息");
            logger.debug(i+"调试信息");
            logger.info(i+"输出信息");
            logger.warn(i+"警告信息");
            logger.error(i+"错误信息");
            logger.fatal(i+"致命信息");
        }
    }

    /**
     * log4j配置
     * 在src目录下添加log4j.properties文件
     */
    @Test
    public void testLog4jConfig2(){
        //2、采用指定配置文件
        //PropertyConfigurator.configure(".\\src\\test\\resources\\log4j.xml");
        DOMConfigurator.configure(".\\src\\test\\resources\\log4j.xml");
        for (int i = 0; i < 5000; i++) {
            logger.trace(i+"跟踪信息");
            logger.debug(i+"调试信息");
            logger.info(i+"输出信息");
            logger.warn(i+"警告信息");
            logger.error(i+"错误信息");
            logger.fatal(i+"致命信息");
        }
    }
}
