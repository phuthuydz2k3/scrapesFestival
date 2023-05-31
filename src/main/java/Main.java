import java.util.*;
import Models.Model;


public class Main
{
    public static void main(String[] args)
    {
        List<Model> cacLeHoi;
        FestivalsScraper scrapeFestival = new FestivalsScraper();
        String page = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t" +
                "_Nam#L%E1%BB%85_h%E1%BB%99i_l%E1%BB%9Bn_c%C3%A1c_t%E1%BB%89nh_th%C3%A0nh";
        String fileName = "CacLeHoi.json";

        cacLeHoi = scrapeFestival.scrapePage(page);
        scrapeFestival.writeModel(fileName, cacLeHoi);
    }
}
