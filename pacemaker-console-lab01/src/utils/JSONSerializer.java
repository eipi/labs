package utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Deque;
import java.util.ArrayDeque;


public class JSONSerializer implements ISerializer
{
    public static final String prefix = "JSON";
    private Deque<Object> stack = new ArrayDeque<>();
    private File file;

    public JSONSerializer (String filename)
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
        ObjectInputStream is = null;

        try
        {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            is = xstream.createObjectInputStream(new FileReader(file));
            stack = ( Deque<Object>) is.readObject();
        }
        finally
        {
            if (is != null)
            {
                is.close();
            }
        }
    }

    public void write() throws Exception
    {
        ObjectOutputStream os = null;

        try
        {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            os = xstream.createObjectOutputStream(new FileWriter(file));
            os.writeObject(stack);
        }
        finally
        {
            if (os != null)
            {
                os.close();
            }
        }
    }
} 