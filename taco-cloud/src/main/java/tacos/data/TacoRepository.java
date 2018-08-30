package tacos.data;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.Taco;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long>{

//	public List<Taco> findAll(PageRequest page);
//	public List<Taco> findAll();
}
