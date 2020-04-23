import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingletonPool {
	
	private static Connection[] pool;
	
	private static int index;
	private static int length = 5;
	
	private SingletonPool() {
	}
	
	public static Connection getConnection() throws Exception {
		if(pool == null) {
			synchronized (SingletonPool.class) {
				if(pool == null)
					pooll();
			}
			
		}
		
		index = (index + 1) % length;
		return pool[index];
		
	}
	
	private static void pooll() throws Exception {
		Properties props = loadProperties();
		String url = props.getProperty("dburl");
		
		pool = new Connection[length];
		
		for(int i = 0; i < length; i++) {
			try {
				pool[i] = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
			
		}
		
	}

	private static Properties loadProperties() throws Exception {
		try(FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch(IOException e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
}
