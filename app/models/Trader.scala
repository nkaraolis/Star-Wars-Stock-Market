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
