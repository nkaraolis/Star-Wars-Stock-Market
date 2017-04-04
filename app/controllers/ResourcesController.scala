package controllers

import javax.inject.Inject

import models._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json._
import play.api.mvc.{Action, Controller, Result}
import services.MongoConnection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Nick Karaolis on 30/03/17.
  */
class ResourcesController @Inject()(val messagesApi: MessagesApi, val mongoConnection: MongoConnection)
  extends Controller with I18nSupport {

  def displayCurrencies() = Action.async { implicit request =>
    mongoConnection.retrieveAllResources("currencies").map {
      currenciesColl => Ok(views.html.resources(currenciesColl))
    }
  }

  def displayResources() = Action.async { implicit request =>
    mongoConnection.retrieveAllResources("resources").map {
      resourcesColl => Ok(views.html.resources(resourcesColl))
    }
  }

  def createCollection(collectionName: String) = Action.async {
    val collectionFile = loadJsonFile(s"/$collectionName.json")
    val resources = Json.fromJson[Seq[Resource]](collectionFile).get
    mongoConnection.createCollection(collectionName, resources).flatMap[Result] {
      case (true, result) => Future.successful(Ok(result))
      case (_, result) => Future.successful(BadRequest(result))
    }
  }

  def removeCollection(collectionName: String) = Action.async {
    mongoConnection.dropCollection(collectionName).flatMap[Result] {
      case (true, result) => Future.successful(Ok(result))
      case (_, result) => Future.successful(BadRequest(result))
    }
  }

}
