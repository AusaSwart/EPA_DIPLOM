package java.sql.logic.models.DAO;

import java.sql.logic.models.DAO.entities.Event;
import java.sql.logic.models.DAO.utilDAO.DataAccessObject;

import java.sql.*;
import java.util.List;

public class EventDAO extends DataAccessObject<Event> {

    public EventDAO(Connection connection) {
        super(connection);
    }
    private static final String GET_ONE = "SELECT id, type_of_event, comment_fe, date_of_event" +
            " FROM event WHERE id=?";
    private static final String DELETE = "DELETE FROM event WHERE id = ?";
    private static final String UPDATE = "UPDATE event SET type_of_event = ?," +
            " comment_fe = ?, date_of_event = ? WHERE id = ?";
    private static final String INSERT = "INSERT INTO event (type_of_event, comment_fe, date_of_event)" +
            " VALUES (?, ?, ?)";
    private static final String GET_LAST_VALUE = "SELECT MAX(id) FROM event";
    public Event findMaxId(Event event) {
        try(PreparedStatement statement = this.connection.prepareStatement(GET_LAST_VALUE);){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                event.setId(rs.getLong("max"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return event;
    }
    @Override
    public Event findById(long id) {
        Event event = new Event();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                event.setId(rs.getLong("id"));
                event.setTypeOfEvent(rs.getString("type_of_event"));
                event.setCommentFE(rs.getString("comment_ls"));
                event.setDateOfEvent(rs.getDate("date_of_event"));

            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return event;
    }

    @Override
    public List<Event> findAll() {
        return null;
    }

    @Override
    public Event update(Event dto) {
        Event event = null;
        try{
            this.connection.setAutoCommit(false);
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
            statement.setString(1, dto.getTypeOfEvent());
            statement.setString(2, dto.getCommentFE());
            statement.setDate(3, (Date) dto.getDateOfEvent());
            statement.setLong(4, dto.getId());
            statement.execute();
            this.connection.commit();
            event = this.findById(dto.getId());
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
        return event;
    }

    @Override
    public Event create(Event dto) {
        Event event = new Event();
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statement.setString(1, dto.getTypeOfEvent());
            statement.setString(2, dto.getCommentFE());
            statement.setDate(3, (Date) dto.getDateOfEvent());
            statement.execute();
            this.connection.commit();
            event = this.findById(dto.getId());
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return event;
    }

    @Override
    public void delete(long id) {
        try{
            this.connection.setAutoCommit(false);
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try(PreparedStatement statement = this.connection.prepareStatement(DELETE);){
            statement.setLong(1, id);
            statement.executeUpdate();
            this.connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
