package services

import javax.inject.Inject

import models._
import play.api.http.Status._
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Nick Karaolis on 30/03/17.
  */
class MongoConnection @Inject()(val reactiveMongoApi: ReactiveMongoApi) {

  def findCollection(collectionName: String): Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection](collectionName))

  def findByID(idField: String, idValue: Int, collectionName: String): Future[Option[Resource]] = for {
    resources <- findCollection(collectionName)
    resource <- resources.find(Json.obj(idField -> idValue)).one[Resource]
  } yield resource

  def createCollection(collectionName: String, documents: Seq[Resource]): Future[(Int, String)] = {
    findCollection(collectionName).flatMap[(Int, String)] {
      collection =>
        val bulkDocs = documents.map(implicitly[collection.ImplicitlyDocumentProducer](_))
        collection.bulkInsert(ordered = false)(bulkDocs: _*).flatMap[(Int, String)] {
          result => result.ok match {
            case true => Future.successful(OK, "Insert successful")
            case _ => Future.successful(BAD_REQUEST, result.errmsg.get)
          }
        }
    }
  }

}
