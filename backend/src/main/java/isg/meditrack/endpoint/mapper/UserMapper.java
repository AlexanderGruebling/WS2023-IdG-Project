package isg.meditrack.endpoint.mapper;

import isg.meditrack.endpoint.dto.RegistrationDto;
import isg.meditrack.entity.ApplicationUser;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    ApplicationUser registrationDtoToApplicationUser(RegistrationDto registrationDto);

    RegistrationDto applicationUserToRegistrationDto(ApplicationUser applicationUser);
}
