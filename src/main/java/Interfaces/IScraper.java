package Interfaces;

import java.util.List;
import Models.Model;

public interface IScraper
{
    public List<Model> scrapePage(String page);
    public void writeModel(String fileName, List<Model> models);
}
