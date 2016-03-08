package utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;


public class YAMLSerializer implements ISerializer
{
    public static final String prefix = "JSON";
    private Deque<Object> stack = new ArrayDeque<>();
    private File file;

    public YAMLSerializer(String filename)
    {
        this.file = new File(filename + ".json");
    }

    @Override
    public void push(Object o)
    {
        stack.push(o);
    }

    @Override
    public Object pop()
    {
        return stack.pop();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void read() throws Exception
    {
        Yaml yaml = new Yaml();
        FileReader reader = new FileReader(file);
        stack = ( Deque<Object>) yaml.load(reader);

    }

    public void write() throws Exception
    {
        Yaml yaml = new Yaml();
        FileWriter writer = new FileWriter(file);
        yaml.dump (stack, writer);
        writer.close();
    }
} 