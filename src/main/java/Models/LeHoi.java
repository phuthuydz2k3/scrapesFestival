package Models;

import java.util.*;

public class LeHoi extends Model
{
    private String thoiGian;
    private String diaDiem;
    private String nhanVatLichSuLienKet;
    private String lanDauToChuc;
    private String diaDiemCode;

    public LeHoi(String ten, String thoiGian, String diaDiem,
                 String nhanVatLichSuLienKet, String lanDauToChuc, List<String> others, String diaDiemCode)
    {
        super(ten, others);
        setThoiGian(thoiGian);
        setDiaDiem(diaDiem);
        setNhanVatLichSuLienKet(nhanVatLichSuLienKet);
        setLanDauToChuc(lanDauToChuc);
        setDiaDiemCode(diaDiemCode);
    }

    public void setLanDauToChuc(String lanDauToChuc)
    {
        this.lanDauToChuc = lanDauToChuc.equals("") ? nullRepresentation : lanDauToChuc;
    }

    public void setNhanVatLichSuLienKet(String nhanVatLichSuLienKet)
    {
        this.nhanVatLichSuLienKet = nhanVatLichSuLienKet.equals("") ? nullRepresentation : nhanVatLichSuLienKet;
    }

    public void setDiaDiem(String diaDiem)
    {
        this.diaDiem = diaDiem.equals("") ? nullRepresentation : diaDiem;
    }

    public void setThoiGian(String thoiGian)
    {
        this.thoiGian = thoiGian.equals("") ? nullRepresentation : thoiGian;
    }

    public void setDiaDiemCode(String diaDiemCode)
    {
        this.diaDiemCode = diaDiemCode;
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
                + "\"Thông Tin Khác\": \"" + this.others.toString() + "\",\n\t"
                + "\"Địa điểm code\": \"" + this.diaDiemCode + "\" }\n";
    }
}
