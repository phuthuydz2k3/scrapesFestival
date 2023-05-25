import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;


public class Main
{
    private static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    public static void main(String[] args)
    {
        ArrayList<LeHoi> cacLeHoi = new ArrayList<>();
        String page = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t" +
                "_Nam#L%E1%BB%85_h%E1%BB%99i_l%E1%BB%9Bn_c%C3%A1c_t%E1%BB%89nh_th%C3%A0nh";

        cacLeHoi = scrapeTalbe(page);
        cacLeHoi.addAll(scrapePage(page));

        try (FileWriter writer = new FileWriter("CacLeHoi.json")) {
            writer.write("[");
            for (LeHoi leHoi : cacLeHoi)
            {
                if (cacLeHoi.indexOf(leHoi) == cacLeHoi.size() - 1)
                {
                    writer.write(leHoi.toString());
                }
                else
                {
                    writer.write(leHoi.toString() + ",\n");
                }
            }
            writer.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<LeHoi> scrapePage(String page)
    {
        ArrayList<LeHoi> cacLeHoi = new ArrayList<>();
        Document doc;

        try
        {
            doc = Jsoup.connect(page)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36" +
                            " (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .get();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }

        Element div = doc.selectFirst("div.mw-parser-output");
        Element ul = div.select("ul").get(10);
        Elements lis = ul.select("li");

        for (Element li : lis)
        {
            String text = li.text();
            String[] texts = text.split(":", 2);
            String diaDiem = texts[0];
            String[] leHois;
            if (diaDiem.equals("Hà Tĩnh"))
            {
                leHois = texts[1].split("\\b((?<!khai\\s)(?<!kết\\s)(?<!chính\\s))(hội đền|đền|hội)\\b");
            }
            else if (diaDiem.equals("Hưng Yên"))
            {
                leHois = texts[1].split("(?<!khai\\s)(lễ hội đình - đền - chùa và hội|Hội|đền|hội|lễ hội)");
            }
            else if (diaDiem.equals("Lâm Đồng"))
            {
                leHois = new String[1];
                leHois[0] = texts[1].replaceFirst("lễ", "").trim();
            }
            else if (texts[1].contains("khai hội") || texts[1].contains("chính hội")
                    || texts[1].contains("kết hội"))
            {
                leHois = texts[1].split("\\b((?<!khai\\s)(?<!kết\\s)(?<!chính\\s))(lễ hội|Hội|hội)\\b");
            }
            else
            {
                leHois = texts[1].split("(Lễ hội|lễ hội|hội|Hội|lễ|Lễ|kỷ niệm|Kỷ niệm|giỗ|Giỗ|ngày giỗ|tết|Tết|ngày|lê hội)");
            }
            for (String leHoi : leHois)
            {
                leHoi = leHoi.trim();
                if (leHoi.equals(""))
                {
                    continue;
                }
                leHoi = leHoi.replaceAll("[,;]$", "");
                String[] parts;
                parts = leHoi.split("\\s+(?=năm|tháng|\\d)", 2);
                String ten = parts[0];
                if (map.containsKey(diaDiem))
                {
                    if (map.get(diaDiem).contains(ten))
                    {
                        continue;
                    }
                }
                String thoiGian = "không rõ";
                if (parts.length == 2)
                {
                    thoiGian = parts[1];
                }
                ArrayList<String> others = new ArrayList<String>();
                others.add("không rõ");
                LeHoi motLeHoi = new LeHoi(ten, thoiGian, diaDiem, "", "", others);
                cacLeHoi.add(motLeHoi);
            }
        }

        return cacLeHoi;
    }

    private static ArrayList<LeHoi> scrapeTalbe(String page)
    {
        ArrayList<LeHoi> cacLeHoi = new ArrayList<>();
        Document doc;

        try
        {
            doc = Jsoup.connect(page)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36" +
                            " (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .get();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }

        Elements trs = doc.select("table.prettytable tbody tr");
        trs.remove(0);

        for (Element tr : trs)
        {
            Elements tds = tr.select("td");

            String thoiGian = tds.get(0).text();
            String diaDiem = tds.get(1).text();

            String ten = tds.get(2).text();
            ten = ten.replaceAll("(Lễ hội|Hội|Tết|Đại hội)", "");
            ten = ten.trim();

            if (!diaDiem.equals("Nhiều quốc gia"))
            {
                String parts[] = diaDiem.split(",", 2);
                String tinh = parts[0];
                if (tinh.equals(""))
                {
                    break;
                }
                else
                {
                    if (!map.containsKey(tinh))
                    {
                        ArrayList<String> newA = new ArrayList<>();
                        newA.add(ten);
                        map.put(tinh, newA);
                    }
                    else
                    {
                        map.get(tinh).add(ten);
                    }
                }
            }
            String lanDauToChuc = tds.get(3).text();
            String nhanVatLichSuLienKet = tds.get(4).text();
            String other = tds.get(5).text().trim();
            ArrayList<String> others = new ArrayList<>();
            if (!other.equals(""))
            {
                others.add(other);
            }

            String href = "https://vi.wikipedia.org/" + tds.get(2).selectFirst("a").attr("href");
            Document doc2;

            try
            {
                doc2 = Jsoup.connect(href)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36" +
                                " (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                        .get();
            }
            catch(IOException e)
            {
                throw new RuntimeException(e);
            }

            Element div = doc2.selectFirst("div.mw-parser-output");
            if (div != null)
            {
                Element p = div.selectFirst("p");
                if (p != null)
                {
                    Elements sups = p.select("sup");
                    for (Element sup : sups)
                    {
                        sup.remove();
                    }
                    others.add(p.text().replaceAll("\"", "'"));
                }
            }

            LeHoi motLeHoi = new LeHoi(ten, thoiGian, diaDiem, nhanVatLichSuLienKet, lanDauToChuc, others);
            cacLeHoi.add(motLeHoi);
        }
        return cacLeHoi;
    }
}
