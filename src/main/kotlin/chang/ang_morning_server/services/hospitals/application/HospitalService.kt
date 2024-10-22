package chang.ang_morning_server.services.hospitals.application

import chang.ang_morning_server.services.hospitals.command.RegisterHospitalCommand
import chang.ang_morning_server.services.hospitals.domain.Hospital
import chang.ang_morning_server.services.hospitals.domain.HospitalRepository
import chang.ang_morning_server.services.hospitals.queries.HospitalOutput
import chang.ang_morning_server.services.hospitals.queries.HospitalQueryResponse
import chang.ang_morning_server.services.maps.application.MapService
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service

@Service
class HospitalService(private val hospitalRepository: HospitalRepository, private val mapService: MapService) {
    fun register(command: RegisterHospitalCommand) {
        val address = this.mapService.searchAddress(command.mainAddress)?.with(null, null, command.subAddress)
            ?: throw BadRequestException("Invalid Address")

        val hospital = Hospital.of(command.name, address)

        this.hospitalRepository.save(hospital)
    }

    fun list(): HospitalQueryResponse {
        val hospitals = this.hospitalRepository.findAll()
        val count = this.hospitalRepository.count()

        return HospitalQueryResponse(hospitals.map { it -> HospitalOutput(it.id, it.name, it.address) }, count)
    }
}