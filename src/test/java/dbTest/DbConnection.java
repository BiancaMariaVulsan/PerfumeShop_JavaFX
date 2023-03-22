package dbTest;

import com.example.perfumeshop.model.persistence.ConnectionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DbConnection {
    @Test
    public void testConnection() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        var connection = ConnectionFactory.getConnection();
        assertTrue(connection != null);
    }
}
