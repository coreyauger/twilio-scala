package com.failfast.twilio.resource

import com.failfast.twilio.json.TokenJson
import com.failfast.twilio.response.TwilioResponse

case class Token(username: String, password: String, ttl: Int, accountSid: String, iceServers: Seq[String], dateCreated: Option[String], dateUpdated: Option[String]) extends TwilioResponse

object Token{
  def apply(tokenJson: TokenJson): Token = {
    Token(
      tokenJson.username,
      tokenJson.password,
      tokenJson.ttl,
      tokenJson.account_sid,
      tokenJson.ice_servers,
      tokenJson.date_created,
      tokenJson.date_updated
    )
  }
}




