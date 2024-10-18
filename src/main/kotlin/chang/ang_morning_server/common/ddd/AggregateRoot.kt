package chang.ang_morning_server.common.ddd

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class AggregateRoot {

    @Column(name = "createdAt", nullable = false)
    private val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updatedAt", nullable = false)
    private var updatedAt: LocalDateTime = LocalDateTime.now()

    // 업데이트 시 타임스탬프 변경 메서드
    fun updateTimestamps() {
        updatedAt = LocalDateTime.now()
    }
}