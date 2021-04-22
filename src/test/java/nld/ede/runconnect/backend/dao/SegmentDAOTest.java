package nld.ede.runconnect.backend.dao;

import nld.ede.runconnect.backend.domain.Route;
import nld.ede.runconnect.backend.domain.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class SegmentDAOTest {

    private SegmentDAO sut;

    @BeforeEach
    public void setSut() {
        sut = new SegmentDAO();
    }

    @Test
    public void getAllRoutesTest() {
        int id = 1;
        String sql = getSelectStatement();
        try {
            DataSource dataSource = mock(DataSource.class);
            Connection connection = mock(Connection.class);
            PreparedStatement preparedStatement = mock(PreparedStatement.class);
            ResultSet resultSet = mock(ResultSet.class);

            // instruct Mocks
            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            // setup classes
            sut.setDataSource(dataSource);

            /**** Act ****/
            List<Segment> segment = sut.getSegmentsOfRoute(id);

            /**** Assert ****/
            verify(connection).prepareStatement(sql);
            verify(preparedStatement).setInt(1, id);
            verify(preparedStatement).executeQuery();

            assertEquals(0, segment.size());

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    private String getSelectStatement() {
        return "SELECT s.SEGMENTID, " +
                "s.SEQUENCENR, c2.ALTITUDE AS STARTALTITUDE, c2.LONGITUDE AS STARTLONGITUDE, " +
                "c2.LATITUDE AS STARTLATITUDE, c.ALTITUDE AS ENDALTITUDE, c.LONGITUDE AS ENDLONGITUDE, " +
                "c.LATITUDE AS ENDLATITUDE, p.DESCRIPTION, p.NAME " +
                "FROM segmentinroute s INNER JOIN segment s2 on s.SEGMENTID = s2.SEGMENTID\n" +
                "INNER JOIN coordinates c on s2.ENDCOORD = c.COORDINATESID " +
                "INNER JOIN coordinates c2 on s2.STARTCOORD = c2.COORDINATESID " +
                "LEFT JOIN poi p on s2.SEGMENTID = p.SEGMENTID " +
                "WHERE s.ROUTEID = ?;";
    }

}
