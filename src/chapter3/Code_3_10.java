package chapter3;

/**
 * Created by nanca on 11/4/2017.
 */
public class Code_3_10 {

    private static ThreadLocal<Connection> connectionHolder =
        new ThreadLocal<Connection>() {
            public Connection initialValue() {
                return DriverManager.getConnection(DB_URL);
            }
        };

    public static Connection getConnection() {
        return connectionHolder.get();
    }
}
