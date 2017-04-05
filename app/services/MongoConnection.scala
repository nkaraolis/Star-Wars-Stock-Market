package services

import javax.inject.Inject

import models._
import play.api.libs.json.{JsArray, JsObject, Json}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.ReadPreference
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection
import reactivemongo.play.json.collection.JsCursor._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Nick Karaolis on 30/03/17.
  */
class MongoConnection @Inject()(val reactiveMongoApi: ReactiveMongoApi) {

  /**
    * Useful method to collect all the documents within a collection for easy conversion
    */
  private def getAllDocuments(collection: JSONCollection): Future[JsArray] = {
    collection.find(Json.obj()).cursor[JsObject](ReadPreference.primary).jsArray()
  }

  /**
    * Creates the next ID for the given collection to be used when inserting a new record
    */
  def incrementID(collectionName: String): Future[Int] = {
    countCollection(collectionName).map(count => count + 1)
  }

  def findCollection(collectionName: String): Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection](collectionName))

  /**
    * Returns all the documents within a collection of resources
    */
  def retrieveAllResources(collectionName: String): Future[Seq[Resource]] = {
    findCollection(collectionName).flatMap {
      collection =>
        getAllDocuments(collection).flatMap {
          documents => Future.successful(Json.fromJson[Seq[Resource]](documents).get)
        }
    }
  }

  def createResourceCollection(collectionName: String, documents: Seq[Resource]): Future[(Boolean, String)] = {
    findCollection(collectionName).flatMap {
      collection =>
        val bulkDocs = documents.map(implicitly[collection.ImplicitlyDocumentProducer](_))
        collection.bulkInsert(ordered = false)(bulkDocs: _*).flatMap {
          result =>
            if (result.ok) {
              Future.successful(true, s"Insertion of $collectionName successful")
            } else {
              Future.successful(false, result.errmsg.get)
            }
        }
    }
  }

  def dropCollection(collectionName: String): Future[(Boolean, String)] = {
    findCollection(collectionName).flatMap {
      coll =>
        coll.drop(failIfNotFound = true).flatMap {
          case result@true => Future.successful(result, s"Dropping of $collectionName successful")
          case result => Future.successful(result, s"Dropping of $collectionName unsuccessful")
        }
    }
  }

  /**
    * Counts the number of documents within a given collection
    */
  def countCollection(collectionName: String): Future[Int] = findCollection(collectionName).flatMap(_.count())

}