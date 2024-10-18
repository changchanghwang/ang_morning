package chang.ang_morning_server.services.hospitals.application

import chang.ang_morning_server.services.hospitals.command.RegisterHospitalCommand
import chang.ang_morning_server.services.hospitals.domain.Hospital
import chang.ang_morning_server.services.hospitals.domain.HospitalRepository
import chang.ang_morning_server.services.valueObjects.Address
import org.springframework.stereotype.Service

@Service
class HospitalService(private val hospitalRepository: HospitalRepository) {
    fun register(command: RegisterHospitalCommand) {
        // TODO: get lat,lng from map api
        val address = Address(command.mainAddress, command.subAddress, 0.0, 0.0)

        val hospital = Hospital.of(command.name, address)

        hospitalRepository.save(hospital)
    }
}