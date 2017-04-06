package services

import javax.inject.{Inject, Singleton}

import models._
import play.api.libs.json.{JsArray, JsObject, Json}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.ReadPreference
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection
import reactivemongo.play.json.collection.JsCursor._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Nick Karaolis on 30/03/17.
  */
@Singleton
class MongoConnection @Inject()(val reactiveMongoApi: ReactiveMongoApi) {

  /**
    * Creates the next ID for the given collection to be used when inserting a new record
    */
  def incrementID(collectionName: String): Future[Int] = countCollection(collectionName).map(count => count + 1)

  def findCollection(collectionName: String): Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection](collectionName))

  /**
    * Returns all the documents within a collection of resources
    */
  def retrieveAllResources(collectionName: String): Future[Seq[Resource]] = for {
    collection <- findCollection(collectionName)
    documents <- getAllDocuments(collection)
  } yield Json.fromJson[Seq[Resource]](documents).get

  def createResourceCollection(collectionName: String, documents: Seq[Resource]): Future[(Boolean, String)] = {
    findCollection(collectionName).flatMap {
      collection =>
        val bulkDocs = documents.map(implicitly[collection.ImplicitlyDocumentProducer](_))
        collection.bulkInsert(ordered = false)(bulkDocs: _*).flatMap {
          result =>
            if (result.ok) {
              Future.successful(true, s"Execution of bulk insert successful")
            } else {
              Future.successful(false, result.errmsg.get)
            }
        }
    }
  }

  def dropCollection(collectionName: String): Future[(Boolean, String)] = {
    findCollection(collectionName).flatMap {
      collection =>
        collection.drop(failIfNotFound = true).map {
          case result@true => (result, s"Execution of dropping $collectionName successful")
          case result => (result, s"Execution of dropping $collectionName unsuccessful")
        }
    }
  }

  /**
    * Counts the number of documents within a given collection
    */
  def countCollection(collectionName: String): Future[Int] = findCollection(collectionName).flatMap(_.count())

  def insertDocument(collectionName: String, document: JsObject): Future[(Boolean, String)] = {
    findCollection(collectionName: String).flatMap {
      collection =>
        collection.insert(document).map {
          resultCheck(_, s"inserting $collectionName")
        }
    }
  }

  def updateDocument(collectionName: String, selector: JsObject, document: JsObject): Future[(Boolean, String)] = {
    findCollection(collectionName).flatMap {
      collection =>
        collection.update(selector, document, upsert = true).map {
          resultCheck(_, s"updating $collectionName")
        }
    }
  }

  /**
    * Useful method to collect all the documents within a collection for easy conversion
    */
  private def getAllDocuments(collection: JSONCollection): Future[JsArray] = {
    collection.find(Json.obj()).cursor[JsObject](ReadPreference.primary).jsArray()
  }

  /**
    * Useful method which aids in having consistent responses for mongo methods
    */
  private def resultCheck(result: WriteResult, method: String): (Boolean, String) = {
    if (result.ok) {
      (result.ok, s"Execution of $method successful")
    } else {
      (result.ok, result.errmsg.get)
    }
  }

}