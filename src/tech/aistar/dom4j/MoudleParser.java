package tech.aistar.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/4/22 0022
 */
public class MoudleParser {
    public static void main(String[] args) {
        //1.准备xml流
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("tech/aistar/dom4j/moudles.xml");

        //2.创建一个SAXReader
        SAXReader saxReader = new SAXReader();

        //3. 获取Document对象
        try {
            //dom解析的方式就是将整个xml装到内存中,比较占空间
            //如果xml比较庞大,性能会很低.
            //xml文件在内存中就会形成一颗倒置的"文档树".
            Document document = saxReader.read(in);

            //获取根元素
            Element root = document.getRootElement();

            //获取根元素中的属性的个数
            System.out.println(root.attributeCount());

            //获取moudle元素 - 注意:用moudles的父标签的对象来进行
            Element moudle = root.element("moudle");
            //获取标签中的内容
            System.out.println(moudle.getTextTrim());

            //获取name元素
            System.out.println(root.elementTextTrim("name"));


        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
