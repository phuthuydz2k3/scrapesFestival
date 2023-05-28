import java.util.*;

public class LeHoi
{
    private int id;

    private String ten;
    private String thoiGian;
    private String diaDiem;
    private String nhanVatLichSuLienKet;
    private String lanDauToChuc;
    private ArrayList<String> others;
    private static String nullRepresentation = "không rõ";

    public LeHoi(String ten, String thoiGian, String diaDiem,
                 String nhanVatLichSuLienKet, String lanDauToChuc, ArrayList<String> others)
    {
        setTen(ten);
        setThoiGian(thoiGian);
        setDiaDiem(diaDiem);
        setNhanVatLichSuLienKet(nhanVatLichSuLienKet);
        setLanDauToChuc(lanDauToChuc);
        setOther(others);
    }

    public ArrayList<String> getOther()
    {
        return others;
    }

    public void setOther(ArrayList<String> others)
    {
        this.others = others;
    }

    public String getLanDauToChuc()
    {
        return lanDauToChuc;
    }

    public void setLanDauToChuc(String lanDauToChuc)
    {
        this.lanDauToChuc = lanDauToChuc == "" ? nullRepresentation : lanDauToChuc;
    }

    public String getNhanVatLichSuLienKet()
    {
        return nhanVatLichSuLienKet;
    }

    public void setNhanVatLichSuLienKet(String nhanVatLichSuLienKet)
    {
        this.nhanVatLichSuLienKet = nhanVatLichSuLienKet == "" ? nullRepresentation : nhanVatLichSuLienKet;
    }

    public String getDiaDiem()
    {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem)
    {
        this.diaDiem = diaDiem == "" ? nullRepresentation : diaDiem;
    }

    public String getThoiGian()
    {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian)
    {
        this.thoiGian = thoiGian == "" ? nullRepresentation : thoiGian;
    }

    public String getTen()
    {
        return ten;
    }

    public void setTen(String ten)
    {
        this.ten = ten == "" ? nullRepresentation : ten;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return  "{ \t\"Id\": \"" + this.id + "\", \n\t"
                + "\"Tên\": \"" + this.ten + "\",\n\t"
                + "\"Thời Gian\": \"" + this.thoiGian + "\",\n\t"
                + "\"Địa điểm\": \"" + this.diaDiem + "\",\n\t"
                + "\"Nhân Vật Lịch Sử Liên Kết\": \"" + this.nhanVatLichSuLienKet + "\",\n\t"
                + "\"Lần Đầu Tổ Chức\": \"" + this.lanDauToChuc + "\",\n\t"
                + "\"Thông Tin Khác\": \"" + this.others.toString() + "\" }\n";
    }
}
