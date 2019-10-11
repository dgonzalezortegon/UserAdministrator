package com.malaga.mapper;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class MapperImpl<M, D> implements Mapper<M, D> {

	private Class<M> modelClass;
	private Class<D> dtoClass;

	@Autowired
	protected DozerBeanMapper mapper;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		this.modelClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.dtoClass = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	@Override
	public M toModel(D d) {
		if (d == null)
			return null;
		return mapper.map(d, modelClass);
	}

	@Override
	public D toDTO(M m) {
		if (m == null)
			return null;
		return mapper.map(m, dtoClass);
	}

	@Override
	public List<D> toDTO(List<M> ms) {

		List<D> r = new ArrayList<>();
		for (M m : ms) {
			r.add(toDTO(m));
		}

		return r;
	}

	@Override
	public List<D> toListDTO(M ms) {
		return null;
	}

	@Override
	public List<D> toListDTO(M ms, Map<String, Object> resources) {
		return null;
	}

	@Override
	public List<M> toListModel(D ms, Map<String, Object> resources) {
		return null;
	}

}
