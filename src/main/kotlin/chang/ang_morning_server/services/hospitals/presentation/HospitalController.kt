package chang.ang_morning_server.services.hospitals.presentation

import chang.ang_morning_server.services.hospitals.application.HospitalService
import chang.ang_morning_server.services.hospitals.command.RegisterHospitalCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hospitals")
class HospitalController(private val hospitalService: HospitalService) {

    @PostMapping
    fun registerHospital(@RequestBody command: RegisterHospitalCommand) {
        hospitalService.register(command)
    }
}