package net.openid.appauthdemo

import io.github.cdimascio.dotenv.dotenv

class ClientSecretProvider {

    companion object {

        fun getClientSecret(): String {
            val dotenv = dotenv {
                directory = "/assets"
                filename = "env"
            }

            return dotenv["CLIENT_SECRET"] ?: ""
        }

    }

}