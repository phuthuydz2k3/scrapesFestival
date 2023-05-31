import java.util.*;
import Models.Model;
import Scrapers.FestivalsScraper;
import utilities.Config;

public class Main
{
    public static void main(String[] args)
    {
        FestivalsScraper scrapeFestival = new FestivalsScraper();
        String page = Config.LE_HOI_WEBPAGE;
        String leHoiFileName = Config.LE_HOI_FILENAME;
        List<Model> cacLeHoi;

        cacLeHoi = scrapeFestival.scrapePage(page);
        scrapeFestival.writeModel(leHoiFileName, cacLeHoi);
    }
}
