import play.api.libs.json.{Json, OFormat}
import reactivemongo.bson.BSON
import reactivemongo.play.json.Writers

/**
  * Created by Nick Karaolis on 30/03/17.
  */
package object models {

  implicit val resourceFormatter = Json.format[Resource]
  implicit val balanceFormatter = Json.format[Balance]
  implicit val resourcesFormatter = Json.format[Resources]
  implicit val traderFormatter = Json.format[Trader]

  def loadJsonFile(fileName: String) = Json.parse(getClass.getResourceAsStream(fileName))

}
