package java.sql.logic.models.DAO;

import java.sql.logic.models.DAO.entities.Contact;
import java.sql.logic.models.DAO.utilDAO.DataAccessObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO extends DataAccessObject<Contact> {
    public ContactDAO(Connection connection) {
        super(connection);
    }
    private static final String GET_ONE = "SELECT id_main_info_contact, location_street, work_number, personal_number, " +
            "mail FROM contact WHERE id_main_info_contact=?";
    private static final String DELETE = "DELETE FROM contact WHERE id_main_info_contact = ?";
    private static final String UPDATE = "UPDATE contact SET location_street = ?, work_number = ?," +
            " personal_number = ?, mail = ?  WHERE id_main_info_contact = ?";
    private static final String INSERT = "INSERT INTO contact (location_street, work_number, personal_number, " +
            "mail, id_main_info_contact) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_ONE_BY_ONE = "SELECT * FROM contact ORDER BY id_main_info_contact";
    private static final String GET_MAIL = "SELECT mail FROM contact WHERE mail = ?";
    public Contact checkMail(String mail) {
        Contact contact = new Contact();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_MAIL);){
            statement.setString(1, mail);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                contact.setMail(rs.getString("mail"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return contact;
    }
    public List<Contact> findAllInList(){
        List<Contact> contacts = new ArrayList<>();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE_BY_ONE);){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Contact contact = new Contact();
                contact.setLocationStreet(rs.getString("location_street"));
                contact.setWorkNumber(rs.getLong("work_number"));
                contact.setPersonalNumber(rs.getLong("personal_number"));
                contact.setMail(rs.getString("mail"));
                contacts.add(contact);
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return contacts;
    }

    @Override
    public Contact findById(long id) {
        Contact contact = new Contact();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                contact.setId(rs.getLong("id_main_info_contact"));
                contact.setLocationStreet(rs.getString("location_street"));
                contact.setWorkNumber(rs.getLong("work_number"));
                contact.setPersonalNumber(rs.getLong("personal_number"));
                contact.setMail(rs.getString("mail"));

            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return contact;
    }

    @Override
    public List<Contact> findAll() {
        return null;
    }

    @Override
    public Contact update(Contact dto) {
        Contact contact = null;
        try{
            this.connection.setAutoCommit(false);
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
            statement.setString(1, dto.getLocationStreet());
            statement.setLong(2, dto.getWorkNumber());
            statement.setLong(3, dto.getPersonalNumber());
            statement.setString(4, dto.getMail());
            statement.setLong(5, dto.getId());
            statement.execute();
            this.connection.commit();
            contact = this.findById(dto.getId());
        }catch(SQLException e){
            try{
                this.connection.rollback();
            }catch (SQLException sqle){
                e.printStackTrace();
                throw new RuntimeException(sqle);
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return contact;
    }

    @Override
    public Contact create(Contact dto) {
        Contact contact = new Contact();
        try{
            this.connection.setAutoCommit(false);
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statement.setString(1, dto.getLocationStreet());
            statement.setLong(2, dto.getWorkNumber());
            statement.setLong(3, dto.getPersonalNumber());
            statement.setString(4, dto.getMail());
            statement.setLong(5, dto.getId());
            statement.execute();
            this.connection.commit();
            contact = this.findById(dto.getId());
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return contact;
    }

    @Override
    public void delete(long id) {
        try(PreparedStatement statement = this.connection.prepareStatement(DELETE);){
            statement.setLong(1, id);
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
