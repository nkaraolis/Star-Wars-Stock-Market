package controllers

import javax.inject.Inject

import models._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json._
import play.api.mvc.{Action, AnyContent, Controller}
import services.MongoConnection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Nick Karaolis on 30/03/17.
  */
class ResourcesController @Inject()(val messagesApi: MessagesApi, val mongoConnection: MongoConnection)
  extends Controller with I18nSupport {

  def displayCurrencies(): Action[AnyContent] = Action.async { implicit request =>
    mongoConnection.retrieveAllResources("currencies").map {
      currenciesColl => Ok(views.html.currencies(currenciesColl))
    }
  }

  def displayResources(): Action[AnyContent] = Action.async { implicit request =>
    mongoConnection.retrieveAllResources("resources").map {
      resourcesColl => Ok(views.html.resources(resourcesColl))
    }
  }

  def createCollection(collectionName: String): Action[AnyContent] = Action.async {
    val collectionFile = loadJsonFile(s"/$collectionName.json")
    val resources = Json.fromJson[Seq[Resource]](collectionFile).get
    mongoConnection.bulkInsertResources(collectionName, resources).flatMap {
      case (true, result) => Future.successful(Ok(result))
      case (_, result) => Future.successful(BadRequest(result))
    }
  }

  def removeCollection(collectionName: String): Action[AnyContent] = Action.async {
    mongoConnection.dropCollection(collectionName).flatMap {
      case (true, result) => Future.successful(Ok(result))
      case (_, result) => Future.successful(BadRequest(result))
    }
  }

  def countColl(collectionName: String): Action[AnyContent] = Action.async {
    mongoConnection.countCollection(collectionName).flatMap {
      count => Future.successful(Ok(s"Count of documents for $collectionName is: $count"))
    }
  }

}
