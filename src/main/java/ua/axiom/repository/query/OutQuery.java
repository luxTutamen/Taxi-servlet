package ua.axiom.repository.query;

import ua.axiom.repository.Fabricable;
import ua.axiom.repository.database.DBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class OutQuery<K, T> extends Query<K, T>  {
    protected Fabricable<T> objectFactory;

    public OutQuery(Fabricable<T> factory, String table, DBConnectionProvider provider) {
        super(table, provider);
        objectFactory = factory;

    }

    public List<T> execute(K key) {
        List<T> result;
        try {
            Connection connection = provider.getConnection().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(constructQueryString(key));
            result = new ArrayList<>();

            int columnCount = resultSet.getMetaData().getColumnCount() + 1;

            while (resultSet.next()) {
                String[] row = new String[columnCount];
                for (int i = 1; i < columnCount; i++) {
                    row[i] = resultSet.getString(i);
                }
                result.add(objectFactory.fabricate(row));
            }
        } catch (SQLException  exception) {
            throw new RuntimeException(exception.getMessage());
        }

        return result;
    }

    //  todo autowire - singe responsibility violation
    protected abstract String constructQueryString(K key);

    /**
     * DOESNT WORK
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private int getResultSetSize(ResultSet resultSet) throws SQLException {
        int size = 0;
        if(resultSet != null) {
            resultSet.last();
            size = resultSet.getRow();
            resultSet.first();
        }
        return size;
    }
}
