package Models;

import java.util.*;

public class Model
{
    protected int id;
    protected String ten;
    protected List<String> others;
    protected static String nullRepresentation = "Không rõ";


    public Model(String ten, List<String> others)
    {
        setTen(ten);
        setOthers(others);
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setTen(String ten)
    {
        this.ten = ten.equals("") ? nullRepresentation : ten;
    }

    public void setOthers(List<String> others)
    {
        if (others == null)
        {
            others = new ArrayList<>();
            others.add(nullRepresentation);
        }
        this.others = others;
    }
}
