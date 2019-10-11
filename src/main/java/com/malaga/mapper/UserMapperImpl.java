package com.malaga.mapper;

import org.springframework.stereotype.Component;

import com.malaga.dto.UserDTO;
import com.malaga.entity.User;

/**
 * User Mapper
 * 
 * @author Daniel González Ortegón
 * @date 11/10/2019
 */


@Component
public class UserMapperImpl extends MapperImpl<User, UserDTO> implements UserMapper {



}
