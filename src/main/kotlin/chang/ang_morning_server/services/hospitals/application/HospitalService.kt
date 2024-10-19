package chang.ang_morning_server.services.hospitals.application

import chang.ang_morning_server.services.hospitals.command.RegisterHospitalCommand
import chang.ang_morning_server.services.hospitals.domain.Hospital
import chang.ang_morning_server.services.hospitals.domain.HospitalRepository
import chang.ang_morning_server.services.maps.application.MapService
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service

@Service
class HospitalService(private val hospitalRepository: HospitalRepository, private val mapService: MapService) {
    fun register(command: RegisterHospitalCommand) {
        val address = mapService.searchAddress(command.mainAddress)?.with(null, command.subAddress)
            ?: throw BadRequestException("Invalid Address")

        val hospital = Hospital.of(command.name, address)

        hospitalRepository.save(hospital)
    }
}