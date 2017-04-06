package models

import play.api.data.Forms._
import play.api.data._

/**
  * Created by Nick Karaolis on 15/11/16.
  */

case class Trader(traderID: Int = 0, firstName: String, lastName: String, email: String,
                  userName: String, password: String,
                  currencies: Seq[Balance] = Seq.empty,
                  resources: Seq[Resources] = Seq.empty)

case class Balance(currency: Resource, amount: BigDecimal)

case class Resources(resource: Resource, amount: Int)

//TODO: Add verification checks for existing username/email and password strength
object TraderDetailsForm {
  val traderDetailsForm = Form(
    tuple(
      "First Name" -> text,
      "Last Name" -> text,
      "Email" -> text,
      "Username" -> text,
      "Password" -> text
    )
  )
}

//TODO: Add verification checks to see if username exists and whether password matches
object LoginForm {
  val loginForm = Form(
    tuple(
      "Username" -> text.verifying("username.required", _.nonEmpty),
      "Password" -> text.verifying("password.required", _.nonEmpty)
    )
  )
}