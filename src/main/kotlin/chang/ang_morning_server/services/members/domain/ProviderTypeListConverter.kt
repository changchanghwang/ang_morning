package chang.ang_morning_server.services.members.domain

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class ProviderTypeListConverter : AttributeConverter<List<ProviderType>, String> {
    override fun convertToDatabaseColumn(attribute: List<ProviderType>?): String? {
        return attribute?.joinToString(",") { it.name }
    }

    override fun convertToEntityAttribute(dbData: String?): List<ProviderType> {
        return dbData?.split(",")
            ?.map { ProviderType.valueOf(it) }
            ?: emptyList()
    }
}