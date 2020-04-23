import java.sql.Connection;

public class SingletonPoolTeste {

	public static void main(String[] args) throws Exception {
		
		Connection pool = SingletonPool.getConnection();
	
		System.out.println(pool);
	
	}
}
