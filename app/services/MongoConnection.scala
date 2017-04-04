package services

import javax.inject.Inject

import models._
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

  def createCollection(collectionName: String, documents: Seq[Resource]): Future[(Boolean, String)] = {
    findCollection(collectionName).flatMap[(Boolean, String)] {
      collection =>
        val bulkDocs = documents.map(implicitly[collection.ImplicitlyDocumentProducer](_))
        collection.bulkInsert(ordered = false)(bulkDocs: _*).flatMap[(Boolean, String)] {
          result => result.ok match {
            case true => Future.successful(true, s"Insertion of $collectionName successful")
            case _ => Future.successful(false, result.errmsg.get)
          }
        }
    }
  }

  def dropCollection(collectionName: String): Future[(Boolean, String)] = {
    findCollection(collectionName).flatMap {
      coll => coll.drop(failIfNotFound = true).flatMap {
        case result@true => Future.successful(result, s"Dropping of $collectionName successful")
        case result => Future.successful(result, s"Dropping of $collectionName unsuccessful")
      }
    }
  }

}
