package heyalex.com.miet_schedule.model.schedule;

/**
 * Created by alexf on 22.09.2016.
 */
public class SemestrData {
    private Data[] Data;

    private String Semestr;

    private Times[] Times;

    public Data[] getData() {
        return Data;
    }

    public void setData(Data[] Data) {
        this.Data = Data;
    }

    public String getSemestr() {
        return Semestr;
    }

    public void setSemestr(String Semestr) {
        this.Semestr = Semestr;
    }

    public Times[] getTimes() {
        return Times;
    }

    public void setTimes(Times[] Times) {
        this.Times = Times;
    }


}
