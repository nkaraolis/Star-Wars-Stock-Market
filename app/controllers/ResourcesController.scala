package controllers

import javax.inject.Inject

import models._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import services.MongoConnection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Nick Karaolis on 30/03/17.
  */
class ResourcesController @Inject()(val messagesApi: MessagesApi, val mongoConnection: MongoConnection)
  extends Controller with I18nSupport {

  def retrieveResources(resources: Seq[Resource]): Seq[Future[Option[Resource]]] =
    resources.map { resource => mongoConnection.findByID("resourceID", resource.resourceID, resource.resourceType) }

  def displayCurrencies() = Action.async { request =>
    mongoConnection.findCollection("currencies").map {
      currenciesColl =>
        Json.fromJson[Seq[Resource]](Json.parse(currenciesColl.toString))
        Ok("Currencies page")
    }
  }

  def displayResources() = Action.async {
    mongoConnection.findCollection("resources").map {
      resourcesColl =>
        Json.fromJson[Seq[Resource]](Json.parse(resourcesColl.toString))
        Ok("Resources page")
    }
  }

}
