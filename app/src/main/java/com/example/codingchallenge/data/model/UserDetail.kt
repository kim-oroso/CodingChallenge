package com.example.codingchallenge.data.model

import java.util.Date

data class UserDetail(
    val id: Int,
    val login: String,
    val node_id: String,
    val avatar_url: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean,

    val name: String,
    val company: String,
    val blog: String?,
    val location: String,
    val email: String?,
    val hireable: String?,
    val bio: String?,
    val twitter_username: String?,
    val public_repos: Int,
    val public_gists: Int,
    val followers: Int,
    val following: Int,
    val created_at: Date,
    val updated_at: Date,

    )