package chang.ang_morning_server.services.hospitals.infrastructure

import chang.ang_morning_server.services.hospitals.domain.Hospital
import chang.ang_morning_server.services.hospitals.domain.HospitalRepository
import org.springframework.stereotype.Repository

@Repository
class HospitalRepositoryImpl(private val hospitalJpaRepository: HospitalJpaRepository) : HospitalRepository {
    override fun findAll(): List<Hospital> {
        return this.hospitalJpaRepository.findAll()
    }

    override fun save(hospital: Hospital): Hospital {
        return this.hospitalJpaRepository.save(hospital)
    }

    override fun count(): Long {
        return this.hospitalJpaRepository.count()
    }
}