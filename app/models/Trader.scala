package models

import play.api.data._
import play.api.data.Forms._
import play.api.i18n.Messages

/**
  * Created by Nick Karaolis on 15/11/16.
  */

case class Trader(firstName: String, lastName: String, userName: String, balances: Seq[Balance], resources: Seq[Resources])

case class Balance(currency: Resource, amount: BigDecimal)

case class Resources(resource: Resource, amount: Int)

object TraderDetailsForm {
  val traderDetailsForm = Form(
    tuple(
      "firstName" -> text,
      "lastName" -> text,
      "userName" -> text
    )
  )
}

object LoginForm {
  val loginForm = Form(
    tuple(
      "Username" -> text.verifying("username.required", _.nonEmpty),
      "Password" -> text.verifying("password.required", _.nonEmpty)
    )
  )
}