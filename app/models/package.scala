import play.api.libs.json.Json

/**
  * Created by Nick Karaolis on 30/03/17.
  */
package object models {

  implicit val resourceFormatter = Json.format[Resource]
  implicit val balanceFormatter = Json.format[Balance]
  implicit val resourcesFormatter = Json.format[Resources]
  implicit val traderFormatter = Json.format[Trader]

}
