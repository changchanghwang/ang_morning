package chang.ang_morning_server.services.hospitals.presentation

import chang.ang_morning_server.services.hospitals.application.HospitalService
import chang.ang_morning_server.services.hospitals.command.RegisterHospitalCommand
import chang.ang_morning_server.services.hospitals.queries.HospitalQueryResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hospitals")
class HospitalController(private val hospitalService: HospitalService) {

    @PostMapping
    fun registerHospital(@RequestBody command: RegisterHospitalCommand) {
        hospitalService.register(command)
    }

    @GetMapping
    fun list(): HospitalQueryResponse {
        return hospitalService.list()
    }
}