package models

import play.api.data._
import play.api.data.Forms._

/**
  * Created by Nick Karaolis on 15/11/16.
  */

case class Trader(firstName: String, lastName: String, userName: String, balances: List[Balance], resources: List[Resources])

case class Balance(currency: Currency, amount: BigDecimal)

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
