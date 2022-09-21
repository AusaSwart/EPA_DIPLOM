package sql.logic.models.entities;

import sql.logic.models.util.DataTransferObject;
import java.sql.Date;

public class MainInfo implements DataTransferObject {
    private long id_main_info;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String cabinet_office;
    private Date birth_d;
    private Date entry_d;

    public long getId() { return id_main_info; }

    public void setId(long id_main_info) { this.id_main_info = id_main_info; }
    public String getFirstName() { return first_name;}
    public void setFirstName(String first_name) { this.first_name = first_name; }
    public String getMiddleName() {return middle_name;}
    public void setMiddleName(String middle_name) { this.middle_name = middle_name; }
    public String getLastName() { return last_name;}
    public void setLastName(String last_name) { this.last_name = last_name; }
    public String getCabinetOffice() {return cabinet_office;}
    public void setCabinetOffice(String cabinet_office) { this.cabinet_office = cabinet_office; }
    public Date getBirthD() { return birth_d;}
    public void setBirthD(Date birth_d) { this.birth_d = birth_d; }
    public Date getEntryD() { return entry_d; }
    public void setEntryD(Date entry_d) { this.entry_d = entry_d; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("main_info{");
        sb.append("id_main_info=").append(id_main_info);
        sb.append(", fist_name='").append(first_name).append('\'');
        sb.append(", middle_name='").append(middle_name).append('\'');
        sb.append(", last_name='").append(last_name).append('\'');
        sb.append(", cabinet_office='").append(cabinet_office).append('\'');
        sb.append(", birth_d='").append(birth_d).append('\'');
        sb.append(", entry_d='").append(entry_d).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
