package edu.job4j.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.job4j.dao.mapper.RowMapper;
import edu.job4j.exception.DaoException;

public abstract class GenericDao<T> {
    
    protected ConnectionPool connectionPool;
    
    GenericDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
    
    protected int addNew(T entity, String query) {
        int entityID = 0;
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            mapFromEntity(statement, entity, connection);
            if (statement.executeUpdate() > 0) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    entityID = keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("entity hasn't been added", e);
        }
        return entityID;
    }
    
    protected List<Integer> addAllEntities(List<T> entities, String query) {
        try (Connection connection = connectionPool.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (T entity : entities) {
                mapFromEntity(statement, entity, connection);
                statement.addBatch();
            }
            statement.executeBatch();
            ResultSet resultKeys = statement.getGeneratedKeys();
            List<Integer> keys = new ArrayList<>();
            while (resultKeys.next()) {
                keys.add(resultKeys.getInt(1));
            }
            return keys;
        } catch (Exception e) {
            throw new DaoException("entities have't been added", e);
        }
    }
    
    protected List<T> findAllEntities(String query, RowMapper<T> rowMapper) {
        List<T> entities = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entities.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("entity hasn't been found", e);
        }
        return entities;
    }
    
    protected abstract void mapFromEntity(PreparedStatement statement, T entity, Connection connection) throws SQLException;
}
