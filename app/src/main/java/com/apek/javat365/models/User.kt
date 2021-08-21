package com.apek.javat365.models

import java.io.Serializable

data class User(
        val user: UserData,
        val token: String?
) : Serializable

data class UserData(
//        var id: String?,
//        val active: Boolean,
//        val external: Boolean,
//        val passwordSetToken: String?,
//        val expires: String?,
//        val roles: List<String>?,
//        val userGroup: String?,
//        val memberOf: String?,
        // val siteGroups: List<*>,
        val email: String?
//        val firstName: String?,
//        val lastName: String?,
//        val phoneNumber: String?,
//        val superAdmin: Boolean,
//        val devices: List<Device>,
//        val createdAt: String?,
//        val updatedAt: String?,
//        val mailboxId: String?
) : Serializable

data class Device(
        val fcmToken: String?,
        val type: String?
) : Serializable




//data class UserData(
//    var id: String?,
//    val active: Boolean?,
//    val external: Boolean?,
//    val passwordSetToken: String?,
//    val expires: String?,
//    val roles: List<String>?,
//    val userGroup: String?,
//    val memberOf: String?,
//    // val siteGroups: List<*>,
//    var email: String?,
//    var firstName: String?,
//    var lastName: String?,
//    val phoneNumber: String?,
//    val superAdmin: Boolean?,
//    val devices: List<Device>,
//    val createdAt: String?,
//    val updatedAt: String?,
//    val mailboxId: String?
//) : Serializable {
//    operator fun invoke(id: String?, email: String?, name: String?): User {
//        this.id = id
//        this.email = email
//        if (name != null) {
//            this.firstName = name.split(" ")[0].trim { it <= ' '}
//            this.lastName = name.split(" ")[1].trim { it <= ' '}
//        }
//    }
//}