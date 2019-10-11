package com.malaga.mapper;

import java.util.List;
import java.util.Map;

public interface Mapper<M, D> { 

	M toModel(D d);

	D toDTO(M m);

	List<D> toDTO(List<M> ms);
	
	List<D> toListDTO(M ms);
	
	List<D> toListDTO(M ms, Map<String, Object> resources);
	
	List<M> toListModel(D ms, Map<String, Object> resources);

}
