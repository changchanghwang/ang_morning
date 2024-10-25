package chang.ang_morning_server.services.auth.command

import chang.ang_morning_server.services.members.domain.ProviderType

data class OAuthCommand(
    val provider: ProviderType,
    val code: String,
)
