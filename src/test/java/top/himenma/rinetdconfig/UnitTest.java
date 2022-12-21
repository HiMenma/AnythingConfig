package top.himenma.rinetdconfig;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.lang.Console;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.HashMap;
import java.util.List;


// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations={"classpath:xmls/spring-context.xml"})
public class UnitTest {
    @Test
    public void t1() {
        FileReader reader = new FileReader("C:\\Users\\Menma\\Desktop\\hosts");
        FileReader reader2 = new FileReader("C:\\Users\\Menma\\Desktop\\rinetd.conf");
        List<String> lines = reader.readLines();
        List<String> lines2 = reader2.readLines();
        Console.log(lines);
        Console.log(lines2);

        FileWriter writer = new FileWriter("C:\\Users\\Menma\\Desktop\\hosts");
        File file = writer.append("127.0.0.1 test test\n");
        Console.log("file: " + file);
        Console.log(new FileReader("C:\\Users\\Menma\\Desktop\\hosts").readLines());

    }

    @Test
    public void t2() {
        System.out.println(System.getProperty("user.id"));
    }

    @Test
    public void t3() {
        FileWriter writer = new FileWriter("C:\\Users\\Menma\\Desktop\\hosts");
        writer.write("1 2");
        writer.write("3 4");
        writer.write("5 6");
    }
}
