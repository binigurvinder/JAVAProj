package Backend;

import java.sql.SQLException;

public interface IBaseDTO<T> {
	
	  int create(T t) throws SQLException;
	    int update(T t) throws SQLException ;
	    T find(Object id) throws SQLException ;
	    int delete(T t) throws SQLException ;

}
