package chang.ang_morning_server.services.hospitals.domain

import chang.ang_morning_server.common.ddd.AggregateRoot
import chang.ang_morning_server.services.valueObjects.Address
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class Hospital(
    @Column(nullable = false)
    val name: String,
    @Embedded
    val address: Address,
    @Id val id: UUID
) : AggregateRoot() {
    companion object {
        fun of(name: String, address: Address): Hospital {
            // TODO: use uuid v7
            return Hospital(name, address, UUID.randomUUID())
        }
    }
}