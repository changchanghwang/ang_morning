package chang.ang_morning_server.services.hospitals.domain

interface HospitalRepository {
    fun save(hospital: Hospital): Hospital
    fun findAll(): List<Hospital>
}