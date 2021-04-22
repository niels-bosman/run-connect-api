package nld.ede.runconnect.backend.dao;

import nld.ede.runconnect.backend.domain.Coordinate;
import nld.ede.runconnect.backend.domain.Route;
import nld.ede.runconnect.backend.domain.Segment;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO implements IRouteDAO {

    @Resource(name = "jdbc/Run_Connect")
    private DataSource dataSource;

    @Override
    public List<Route> findAllRoutes() {
        String sql = "SELECT * FROM ROUTE";
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<Route> routeList = new ArrayList<>();
            while (resultSet.next()) {
                Route route = new Route();
                route.setRouteId(resultSet.getInt(1));
                route.setName(resultSet.getString(2));
                route.setDistance(resultSet.getInt(3));
                routeList.add(route);
            }
            return routeList;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;

    }
}
